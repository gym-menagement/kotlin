package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.user.Level
import com.gowoobro.gymspring.enums.user.Use
import com.gowoobro.gymspring.enums.user.Type
import com.gowoobro.gymspring.enums.user.Role


@Repository
interface UserRepository : JpaRepository<User, Long> {
    override fun findAll(pageable: Pageable): Page<User>


    fun findByLoginid(loginid: String): List<User>

    fun findByPasswd(passwd: String): List<User>

    fun findByEmail(email: String): List<User>

    fun findByName(name: String): List<User>

    fun findByTel(tel: String): List<User>

    fun findByAddress(address: String): List<User>

    fun findByImage(image: String): List<User>

    fun findBySex(sex: Int): List<User>

    fun findByBirth(birth: LocalDateTime): List<User>

    fun findByType(type: Type): List<User>

    fun findByConnectid(connectid: String): List<User>

    fun findByLevel(level: Level): List<User>

    fun findByRole(role: Role): List<User>

    fun findByUse(use: Use): List<User>

    fun findByLogindate(logindate: LocalDateTime): List<User>

    fun findByLastchangepasswddate(lastchangepasswddate: LocalDateTime): List<User>

    fun findByDate(date: LocalDateTime): List<User>
}