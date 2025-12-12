package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Workoutlog
import com.gowoobro.gymspring.entity.WorkoutlogCreateRequest
import com.gowoobro.gymspring.entity.WorkoutlogUpdateRequest
import com.gowoobro.gymspring.entity.WorkoutlogPatchRequest
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
        return workoutlogRepository.findByGym(gym)
    }

    fun findByUser(user: Long): List<Workoutlog> {
        return workoutlogRepository.findByUser(user)
    }

    fun findByAttendance(attendance: Long): List<Workoutlog> {
        return workoutlogRepository.findByAttendance(attendance)
    }

    fun findByHealth(health: Long): List<Workoutlog> {
        return workoutlogRepository.findByHealth(health)
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
            gym = request.gym,
            user = request.user,
            attendance = request.attendance,
            health = request.health,
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
                gym = request.gym,
                user = request.user,
                attendance = request.attendance,
                health = request.health,
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
            gym = request.gym,
            user = request.user,
            attendance = request.attendance,
            health = request.health,
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

    fun patch(request: WorkoutlogPatchRequest): Workoutlog? {
        val existing = workoutlogRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gym = request.gym ?: existing.gym,
            user = request.user ?: existing.user,
            attendance = request.attendance ?: existing.attendance,
            health = request.health ?: existing.health,
            exercisename = request.exercisename ?: existing.exercisename,
            sets = request.sets ?: existing.sets,
            reps = request.reps ?: existing.reps,
            weight = request.weight ?: existing.weight,
            duration = request.duration ?: existing.duration,
            calories = request.calories ?: existing.calories,
            note = request.note ?: existing.note,
            date = request.date ?: existing.date,
        )
        return workoutlogRepository.save(updated)
    }
}