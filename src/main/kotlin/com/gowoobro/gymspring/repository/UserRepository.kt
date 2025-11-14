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
import com.gowoobro.gymspring.enums.user.Sex


@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query("SELECT m FROM User m")
    override fun findAll(pageable: Pageable): Page<User>

    @Query("SELECT m FROM User m WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<User>

    @Query("SELECT m FROM User m WHERE m.loginid = :loginid")
    fun findByLoginidWithJoin(loginid: String): List<User>

    @Query("SELECT m FROM User m WHERE m.passwd = :passwd")
    fun findByPasswdWithJoin(passwd: String): List<User>

    @Query("SELECT m FROM User m WHERE m.email = :email")
    fun findByEmailWithJoin(email: String): List<User>

    @Query("SELECT m FROM User m WHERE m.name = :name")
    fun findByNameWithJoin(name: String): List<User>

    @Query("SELECT m FROM User m WHERE m.tel = :tel")
    fun findByTelWithJoin(tel: String): List<User>

    @Query("SELECT m FROM User m WHERE m.address = :address")
    fun findByAddressWithJoin(address: String): List<User>

    @Query("SELECT m FROM User m WHERE m.image = :image")
    fun findByImageWithJoin(image: String): List<User>

    @Query("SELECT m FROM User m WHERE m.sex = :sex")
    fun findBySexWithJoin(sex: Sex): List<User>

    @Query("SELECT m FROM User m WHERE m.birth = :birth")
    fun findByBirthWithJoin(birth: LocalDateTime): List<User>

    @Query("SELECT m FROM User m WHERE m.type = :type")
    fun findByTypeWithJoin(type: Type): List<User>

    @Query("SELECT m FROM User m WHERE m.connectid = :connectid")
    fun findByConnectidWithJoin(connectid: String): List<User>

    @Query("SELECT m FROM User m WHERE m.level = :level")
    fun findByLevelWithJoin(level: Level): List<User>

    @Query("SELECT m FROM User m WHERE m.role = :role")
    fun findByRoleWithJoin(role: Role): List<User>

    @Query("SELECT m FROM User m WHERE m.use = :use")
    fun findByUseWithJoin(use: Use): List<User>

    @Query("SELECT m FROM User m WHERE m.logindate = :logindate")
    fun findByLogindateWithJoin(logindate: LocalDateTime): List<User>

    @Query("SELECT m FROM User m WHERE m.lastchangepasswddate = :lastchangepasswddate")
    fun findByLastchangepasswddateWithJoin(lastchangepasswddate: LocalDateTime): List<User>

    @Query("SELECT m FROM User m WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<User>
}