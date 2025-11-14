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


    fun findByUser(user: Long): List<Workoutlog> {
        return workoutlogRepository.findByUserWithJoin(user)
    }

    fun findByAttendance(attendance: Long): List<Workoutlog> {
        return workoutlogRepository.findByAttendanceWithJoin(attendance)
    }

    fun findByHealth(health: Long): List<Workoutlog> {
        return workoutlogRepository.findByHealthWithJoin(health)
    }

    fun findByExercisename(exercisename: String): List<Workoutlog> {
        return workoutlogRepository.findByExercisenameWithJoin(exercisename)
    }

    fun findBySets(sets: Int): List<Workoutlog> {
        return workoutlogRepository.findBySetsWithJoin(sets)
    }

    fun findByReps(reps: Int): List<Workoutlog> {
        return workoutlogRepository.findByRepsWithJoin(reps)
    }

    fun findByWeight(weight: BigDecimal): List<Workoutlog> {
        return workoutlogRepository.findByWeightWithJoin(weight)
    }

    fun findByDuration(duration: Int): List<Workoutlog> {
        return workoutlogRepository.findByDurationWithJoin(duration)
    }

    fun findByCalories(calories: Int): List<Workoutlog> {
        return workoutlogRepository.findByCaloriesWithJoin(calories)
    }

    fun findByNote(note: String): List<Workoutlog> {
        return workoutlogRepository.findByNoteWithJoin(note)
    }

    fun findByDate(date: LocalDateTime): List<Workoutlog> {
        return workoutlogRepository.findByDateWithJoin(date)
    }


    fun create(request: WorkoutlogCreateRequest): Workoutlog {
        val entity = Workoutlog(
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