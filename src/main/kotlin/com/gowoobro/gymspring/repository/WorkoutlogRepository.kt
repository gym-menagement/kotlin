package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Workoutlog
import com.gowoobro.gymspring.entity.WorkoutlogCreateRequest
import com.gowoobro.gymspring.entity.WorkoutlogUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.math.BigDecimal


@Repository
interface WorkoutlogRepository : JpaRepository<Workoutlog, Long> {
    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    override fun findAll(pageable: Pageable): Page<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    override fun findById(id: Long): java.util.Optional<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByuserId(user: Long): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByattendanceId(attendance: Long): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByhealthId(health: Long): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByExercisename(exercisename: String): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findBySets(sets: Int): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByReps(reps: Int): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByWeight(weight: BigDecimal): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByDuration(duration: Int): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByCalories(calories: Int): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByNote(note: String): List<Workoutlog>

    @EntityGraph(attributePaths = ["user", "attendance", "health"])
    fun findByDate(date: LocalDateTime): List<Workoutlog>
}
