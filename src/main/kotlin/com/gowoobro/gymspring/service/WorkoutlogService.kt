package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Workoutlog
import com.gowoobro.gymspring.entity.WorkoutlogCreateRequest
import com.gowoobro.gymspring.entity.WorkoutlogUpdateRequest
import com.gowoobro.gymspring.repository.WorkoutlogRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.math.BigDecimal


@Service
@Transactional
class WorkoutlogService(private val workoutlogRepository: WorkoutlogRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Workoutlog> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return workoutlogRepository.findAll(pageable)
    }

    fun findById(id: Long): Workoutlog? {
        return workoutlogRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return workoutlogRepository.count()
    }


    fun findByGym(gym: Long): List<Workoutlog> {
        return workoutlogRepository.findBygymId(gym)
    }

    fun findByUser(user: Long): List<Workoutlog> {
        return workoutlogRepository.findByuserId(user)
    }

    fun findByAttendance(attendance: Long): List<Workoutlog> {
        return workoutlogRepository.findByattendanceId(attendance)
    }

    fun findByHealth(health: Long): List<Workoutlog> {
        return workoutlogRepository.findByhealthId(health)
    }

    fun findByExercisename(exercisename: String): List<Workoutlog> {
        return workoutlogRepository.findByExercisename(exercisename)
    }

    fun findBySets(sets: Int): List<Workoutlog> {
        return workoutlogRepository.findBySets(sets)
    }

    fun findByReps(reps: Int): List<Workoutlog> {
        return workoutlogRepository.findByReps(reps)
    }

    fun findByWeight(weight: BigDecimal): List<Workoutlog> {
        return workoutlogRepository.findByWeight(weight)
    }

    fun findByDuration(duration: Int): List<Workoutlog> {
        return workoutlogRepository.findByDuration(duration)
    }

    fun findByCalories(calories: Int): List<Workoutlog> {
        return workoutlogRepository.findByCalories(calories)
    }

    fun findByNote(note: String): List<Workoutlog> {
        return workoutlogRepository.findByNote(note)
    }

    fun findByDate(date: LocalDateTime): List<Workoutlog> {
        return workoutlogRepository.findByDate(date)
    }


    fun create(request: WorkoutlogCreateRequest): Workoutlog {
        val entity = Workoutlog(
            gymId = request.gym,
            userId = request.user,
            attendanceId = request.attendance,
            healthId = request.health,
            exercisename = request.exercisename,
            sets = request.sets,
            reps = request.reps,
            weight = request.weight,
            duration = request.duration,
            calories = request.calories,
            note = request.note,
            date = request.date,
        )
        return workoutlogRepository.save(entity)
    }

    fun createBatch(requests: List<WorkoutlogCreateRequest>): List<Workoutlog> {
        val entities = requests.map { request ->
            Workoutlog(
                gymId = request.gym,
                userId = request.user,
                attendanceId = request.attendance,
                healthId = request.health,
                exercisename = request.exercisename,
                sets = request.sets,
                reps = request.reps,
                weight = request.weight,
                duration = request.duration,
                calories = request.calories,
                note = request.note,
                date = request.date,
            )
        }
        return workoutlogRepository.saveAll(entities)
    }

    fun update(request: WorkoutlogUpdateRequest): Workoutlog? {
        val existing = workoutlogRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            userId = request.user,
            attendanceId = request.attendance,
            healthId = request.health,
            exercisename = request.exercisename,
            sets = request.sets,
            reps = request.reps,
            weight = request.weight,
            duration = request.duration,
            calories = request.calories,
            note = request.note,
            date = request.date,
        )
        return workoutlogRepository.save(updated)
    }

    fun delete(entity: Workoutlog): Boolean {
        return try {
            workoutlogRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            workoutlogRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Workoutlog>): Boolean {
        return try {
            workoutlogRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}