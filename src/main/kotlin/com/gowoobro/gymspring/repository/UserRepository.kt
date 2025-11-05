package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    override fun findAll(pageable: Pageable): Page<User>

    override fun findById(id: String): List<User>

    override fun findByLoginid(loginid: String): List<User>

    override fun findByPasswd(passwd: String): List<User>

    override fun findByEmail(email: String): List<User>

    override fun findByName(name: String): List<User>

    override fun findByTel(tel: String): List<User>

    override fun findByAddress(address: String): List<User>

    override fun findByImage(image: String): List<User>

    override fun findBySex(sex: String): List<User>

    override fun findByBirth(birth: String): List<User>

    override fun findByType(type: String): List<User>

    override fun findByConnectid(connectid: String): List<User>

    override fun findByLevel(level: String): List<User>

    override fun findByRole(role: String): List<User>

    override fun findByUse(use: String): List<User>

    override fun findByLogindate(logindate: String): List<User>

    override fun findByLastchangepasswddate(lastchangepasswddate: String): List<User>

    override fun findByDate(date: String): List<User>
}