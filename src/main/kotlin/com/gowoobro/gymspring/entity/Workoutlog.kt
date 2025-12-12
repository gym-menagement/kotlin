package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "workoutlog_tb")
data class Workoutlog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wl_id")
    val id: Long = 0,
    @Column(name = "wl_gym")
    val gym: Long = 0L,
    @Column(name = "wl_user")
    val user: Long = 0L,
    @Column(name = "wl_attendance")
    val attendance: Long = 0L,
    @Column(name = "wl_health")
    val health: Long = 0L,
    @Column(name = "wl_exercisename")
    val exercisename: String = "",
    @Column(name = "wl_sets")
    val sets: Int = 0,
    @Column(name = "wl_reps")
    val reps: Int = 0,
    @Column(name = "wl_weight")
    val weight: BigDecimal = BigDecimal.ZERO,
    @Column(name = "wl_duration")
    val duration: Int = 0,
    @Column(name = "wl_calories")
    val calories: Int = 0,
    @Column(name = "wl_note")
    val note: String = "",
    @Column(name = "wl_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_user", insertable = false, updatable = false)
    var user: User? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "at_attendance", insertable = false, updatable = false)
    var attendance: Attendance? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_health", insertable = false, updatable = false)
    var health: Health? = null
}

data class WorkoutlogCreateRequest(
    val gym: Long = 0L,
    val user: Long = 0L,
    val attendance: Long = 0L,
    val health: Long = 0L,
    val exercisename: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val weight: BigDecimal = BigDecimal.ZERO,
    val duration: Int = 0,
    val calories: Int = 0,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class WorkoutlogUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val user: Long = 0L,
    val attendance: Long = 0L,
    val health: Long = 0L,
    val exercisename: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val weight: BigDecimal = BigDecimal.ZERO,
    val duration: Int = 0,
    val calories: Int = 0,
    val note: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class WorkoutlogPatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val user: Long? = null,
    val attendance: Long? = null,
    val health: Long? = null,
    val exercisename: String? = null,
    val sets: Int? = null,
    val reps: Int? = null,
    val weight: BigDecimal? = null,
    val duration: Int? = null,
    val calories: Int? = null,
    val note: String? = null,
    val date: LocalDateTime? = null,
)

data class WorkoutlogExtraInfo(

    val gym: GymResponse? = null,
    val user: UserResponse? = null,
    val attendance: AttendanceResponse? = null,
    val health: HealthResponse? = null,
)


data class WorkoutlogResponse(
    val id: Long,
    val gym: Long,
    val user: Long,
    val attendance: Long,
    val health: Long,
    val exercisename: String,
    val sets: Int,
    val reps: Int,
    val weight: BigDecimal,
    val duration: Int,
    val calories: Int,
    val note: String,
    val date: String?,

    val extra: WorkoutlogExtraInfo
){
    companion object {
        fun from(workoutlog: Workoutlog): WorkoutlogResponse {
            val gymResponse = workoutlog.gym?.let { GymResponse.from(it) }
            val userResponse = workoutlog.user?.let { UserResponse.from(it) }
            val attendanceResponse = workoutlog.attendance?.let { AttendanceResponse.from(it) }
            val healthResponse = workoutlog.health?.let { HealthResponse.from(it) }
            return WorkoutlogResponse(
                id = workoutlog.id,
                gym = workoutlog.gym,
                user = workoutlog.user,
                attendance = workoutlog.attendance,
                health = workoutlog.health,
                exercisename = workoutlog.exercisename,
                sets = workoutlog.sets,
                reps = workoutlog.reps,
                weight = workoutlog.weight,
                duration = workoutlog.duration,
                calories = workoutlog.calories,
                note = workoutlog.note,
                date = workoutlog.date?.toString()?.replace("T", " ") ?: "",

                extra = WorkoutlogExtraInfo(
                    gym = gymResponse,user = userResponse,attendance = attendanceResponse,health = healthResponse,)
                
            )
        }
    }
}