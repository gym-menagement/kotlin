package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status
import com.gowoobro.gymspring.entity.Level
import com.gowoobro.gymspring.entity.UserRole
import com.gowoobro.gymspring.entity.Use

@Repository
interface UserRepository : JpaRepository<User, Long> {
    
    fun findByLoginid(loginid: String): User?
    
    fun findByPasswd(passwd: String): List<User>
    
    fun findByEmail(email: String): List<User>
    
    fun findByName(name: String): List<User>
    
    fun findByTel(tel: String): List<User>
    
    fun findByAddress(address: String): List<User>
    
    fun findByImage(image: String): List<User>
    
    fun findBySex(sex: Int): List<User>
    
    fun findByType(type: Type): List<User>
    
    fun findByConnectid(connectid: String): List<User>
    
    fun findByLevel(level: Level): List<User>
    
    fun findByRole(role: UserRole): List<User>
    
    fun findByUse(use: Use): List<User>
    
    override fun findAll(pageable: Pageable): Page<User>
}