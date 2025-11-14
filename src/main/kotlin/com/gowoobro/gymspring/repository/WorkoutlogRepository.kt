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
    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health")
    override fun findAll(pageable: Pageable): Page<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.userId = :user")
    fun findByUserWithJoin(user: Long): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.attendanceId = :attendance")
    fun findByAttendanceWithJoin(attendance: Long): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.healthId = :health")
    fun findByHealthWithJoin(health: Long): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.exercisename = :exercisename")
    fun findByExercisenameWithJoin(exercisename: String): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.sets = :sets")
    fun findBySetsWithJoin(sets: Int): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.reps = :reps")
    fun findByRepsWithJoin(reps: Int): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.weight = :weight")
    fun findByWeightWithJoin(weight: BigDecimal): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.duration = :duration")
    fun findByDurationWithJoin(duration: Int): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.calories = :calories")
    fun findByCaloriesWithJoin(calories: Int): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.note = :note")
    fun findByNoteWithJoin(note: String): List<Workoutlog>

    @Query("SELECT m FROM Workoutlog m LEFT JOIN FETCH m.user LEFT JOIN FETCH m.attendance LEFT JOIN FETCH m.health WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Workoutlog>
}