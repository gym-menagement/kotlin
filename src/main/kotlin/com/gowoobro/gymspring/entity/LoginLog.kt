package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "loginlog_tb")
data class Loginlog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ll_id")
    val id: Long = 0,
    @Column(name = "ll_ip")
    val ip: String = "",
    @Column(name = "ll_ipvalue")
    val ipvalue: Long = 0L,
    @Column(name = "ll_user")
    val user: Long = 0L,
    @Column(name = "ll_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class LoginlogCreateRequest(
    val ip: String = "",
    val ipvalue: Long = 0L,
    val user: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class LoginlogUpdateRequest(
    val id: Long = 0,
    val ip: String = "",
    val ipvalue: Long = 0L,
    val user: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class LoginlogResponse(
    val id: Long,
    val ip: String,
    val ipvalue: Long,
    val user: Long,
    val date: String?,

){
    companion object {
        fun from(loginlog: Loginlog): LoginlogResponse {
            return LoginlogResponse(
                id = loginlog.id,
                ip = loginlog.ip,
                ipvalue = loginlog.ipvalue,
                user = loginlog.user,
                date = loginlog.date?.toString()?.replace("T", " ") ?: "",
            )
        }
    }
}