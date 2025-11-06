package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "user_tb")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    val id: Long = 0,
    @Column(name = "u_loginid")
    val loginid: String = "",
    @Column(name = "u_passwd")
    val passwd: String = "",
    @Column(name = "u_email")
    val email: String = "",
    @Column(name = "u_name")
    val name: String = "",
    @Column(name = "u_tel")
    val tel: String = "",
    @Column(name = "u_address")
    val address: String = "",
    @Column(name = "u_image")
    val image: String = "",
    @Column(name = "u_sex")
    val sex: Int = 0,
    @Column(name = "u_birth")
    val birth: LocalDateTime? = null,
    @Column(name = "u_type")
    @Enumerated(EnumType.ORDINAL)
    val type: Type,
    @Column(name = "u_connectid")
    val connectid: String = "",
    @Column(name = "u_level")
    @Enumerated(EnumType.ORDINAL)
    val level: Level,
    @Column(name = "u_role")
    @Enumerated(EnumType.ORDINAL)
    val role: UserRole,
    @Column(name = "u_use")
    @Enumerated(EnumType.ORDINAL)
    val use: Use,
    @Column(name = "u_logindate")
    val logindate: LocalDateTime? = null,
    @Column(name = "u_lastchangepasswddate")
    val lastchangepasswddate: LocalDateTime? = null,
    @Column(name = "u_date")
    val date: LocalDateTime? = null,
)

data class UserCreateRequest(
    val loginid: String = "",
    val passwd: String = "",
    val email: String = "",
    val name: String = "",
    val tel: String = "",
    val address: String = "",
    val image: String = "",
    val sex: Int = 0,
    val birth: LocalDateTime? = null,
    val type: Type,
    val connectid: String = "",
    val level: Level,
    val role: UserRole,
    val use: Use,
    val logindate: LocalDateTime? = null,
    val lastchangepasswddate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)

data class UserUpdateRequest(
    val id: Long = 0,
    val loginid: String = "",
    val passwd: String = "",
    val email: String = "",
    val name: String = "",
    val tel: String = "",
    val address: String = "",
    val image: String = "",
    val sex: Int = 0,
    val birth: LocalDateTime? = null,
    val type: Type,
    val connectid: String = "",
    val level: Level,
    val role: UserRole,
    val use: Use,
    val logindate: LocalDateTime? = null,
    val lastchangepasswddate: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)