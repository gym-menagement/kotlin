package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Workoutlog
import com.gowoobro.gymspring.entity.WorkoutlogCreateRequest
import com.gowoobro.gymspring.entity.WorkoutlogUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.math.BigDecimal


@Repository
interface WorkoutlogRepository : JpaRepository<Workoutlog, Long> {
    override fun findAll(pageable: Pageable): Page<Workoutlog>


    fun findByUser(user: Long): List<Workoutlog>

    fun findByAttendance(attendance: Long): List<Workoutlog>

    fun findByHealth(health: Long): List<Workoutlog>

    fun findByExercisename(exercisename: String): List<Workoutlog>

    fun findBySets(sets: Int): List<Workoutlog>

    fun findByReps(reps: Int): List<Workoutlog>

    fun findByWeight(weight: BigDecimal): List<Workoutlog>

    fun findByDuration(duration: Int): List<Workoutlog>

    fun findByCalories(calories: Int): List<Workoutlog>

    fun findByNote(note: String): List<Workoutlog>

    fun findByDate(date: LocalDateTime): List<Workoutlog>
}