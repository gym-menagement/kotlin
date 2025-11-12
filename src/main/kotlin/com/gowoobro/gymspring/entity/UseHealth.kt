package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "usehealth_tb")
data class Usehealth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uh_id")
    val id: Long = 0,
    @Column(name = "uh_order")
    val order: Long = 0L,
    @Column(name = "uh_health")
    val health: Long = 0L,
    @Column(name = "uh_user")
    val user: Long = 0L,
    @Column(name = "uh_rocker")
    val rocker: Long = 0L,
    @Column(name = "uh_term")
    val term: Long = 0L,
    @Column(name = "uh_discount")
    val discount: Long = 0L,
    @Column(name = "uh_startday")
    val startday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uh_endday")
    val endday: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "uh_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthCreateRequest(
    val order: Long = 0L,
    val health: Long = 0L,
    val user: Long = 0L,
    val rocker: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UsehealthUpdateRequest(
    val id: Long = 0,
    val order: Long = 0L,
    val health: Long = 0L,
    val user: Long = 0L,
    val rocker: Long = 0L,
    val term: Long = 0L,
    val discount: Long = 0L,
    val startday: LocalDateTime? = LocalDateTime.now(),
    val endday: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class UsehealthResponse(
    val id: Long,
    val order: Long,
    val health: Long,
    val user: Long,
    val rocker: Long,
    val term: Long,
    val discount: Long,
    val startday: String?,
    val endday: String?,
    val date: String?,

){
    companion object {
        fun from(usehealth: Usehealth): UsehealthResponse {
            return UsehealthResponse(
                id = usehealth.id,
                order = usehealth.order,
                health = usehealth.health,
                user = usehealth.user,
                rocker = usehealth.rocker,
                term = usehealth.term,
                discount = usehealth.discount,
                startday = usehealth.startday?.toString()?.replace("T", " ") ?: "",
                endday = usehealth.endday?.toString()?.replace("T", " ") ?: "",
                date = usehealth.date?.toString()?.replace("T", " ") ?: "",
            )
        }
    }
}