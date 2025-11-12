package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "stop_tb")
data class Stop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    val id: Long = 0,
    @Column(name = "s_usehelth")
    val usehelth: Long = 0L,
    @Column(name = "s_startday")
    val startday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "s_endday")
    val endday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "s_count")
    val count: Int = 0,
    @Column(name = "s_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class StopCreateRequest(
    val usehelth: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val count: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class StopUpdateRequest(
    val id: Long = 0,
    val usehelth: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val count: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class StopResponse(
    val id: Long,
    val usehelth: Long,
    val startday: String?,
    val endday: String?,
    val count: Int,
    val date: String?,

){
    companion object {
        fun from(stop: Stop): StopResponse {
            return StopResponse(
                id = stop.id,
                usehelth = stop.usehelth,
                startday = stop.startday?.toString()?.replace("T", " ") ?: "",
                endday = stop.endday?.toString()?.replace("T", " ") ?: "",
                count = stop.count,
                date = stop.date?.toString()?.replace("T", " ") ?: "",
            )
        }
    }
}