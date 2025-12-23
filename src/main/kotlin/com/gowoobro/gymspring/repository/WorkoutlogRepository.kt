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
    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    override fun findAll(pageable: Pageable): Page<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    override fun findById(id: Long): java.util.Optional<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findBygymId(gym: Long): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByuserId(user: Long): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByattendanceId(attendance: Long): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByhealthId(health: Long): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByExercisename(exercisename: String): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findBySets(sets: Int): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByReps(reps: Int): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByWeight(weight: BigDecimal): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByDuration(duration: Int): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByCalories(calories: Int): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByNote(note: String): List<Workoutlog>

    @EntityGraph(attributePaths = [
        "gym",
        "user",
        "attendance",
        "attendance.user",
        "attendance.usehealth",
        "attendance.gym",
        "health",
        "health.healthcategory",
        "health.term",
        "health.discount",
        "health.gym"
    ])
    fun findByDate(date: LocalDateTime): List<Workoutlog>
}
