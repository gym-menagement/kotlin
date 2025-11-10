package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.user.Level
import com.gowoobro.gymspring.enums.user.Use
import com.gowoobro.gymspring.enums.user.Type
import com.gowoobro.gymspring.enums.user.Role
import com.gowoobro.gymspring.enums.user.Sex

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
    val sex: Sex = Sex.0,
    @Column(name = "u_birth")
    val birth: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "u_type")
    val type: Type = Type.NORMAL,
    @Column(name = "u_connectid")
    val connectid: String = "",
    @Column(name = "u_level")
    val level: Level = Level.1,
    @Column(name = "u_role")
    val role: Role = Role.MEMBER,
    @Column(name = "u_use")
    val use: Use = Use.USE,
    @Column(name = "u_logindate")
    val logindate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "u_lastchangepasswddate")
    val lastchangepasswddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "u_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UserCreateRequest(
    val loginid: String = "",
    val passwd: String = "",
    val email: String = "",
    val name: String = "",
    val tel: String = "",
    val address: String = "",
    val image: String = "",
    val sex: Sex = Sex.0,
    val birth: LocalDateTime? = LocalDateTime.now(),
    val type: Type = Type.NORMAL,
    val connectid: String = "",
    val level: Level = Level.1,
    val role: Role = Role.MEMBER,
    val use: Use = Use.USE,
    val logindate: LocalDateTime? = LocalDateTime.now(),
    val lastchangepasswddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
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
    val sex: Sex = Sex.0,
    val birth: LocalDateTime? = LocalDateTime.now(),
    val type: Type = Type.NORMAL,
    val connectid: String = "",
    val level: Level = Level.1,
    val role: Role = Role.MEMBER,
    val use: Use = Use.USE,
    val logindate: LocalDateTime? = LocalDateTime.now(),
    val lastchangepasswddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)