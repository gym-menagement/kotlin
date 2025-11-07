package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
import com.gowoobro.gymspring.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<User> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return userRepository.findAll(pageable)
    }

    fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return userRepository.count()
    }

    fun findByLoginid(loginid: String): User? {
        return userRepository.findByLoginid(loginid)
    }

    fun create(request: UserCreateRequest): User {
        val entity = User(
            loginid = request.loginid,
            passwd = request.passwd,
            email = request.email,
            name = request.name,
            tel = request.tel,
            address = request.address,
            image = request.image,
            sex = request.sex,
            birth = request.birth,
            type = request.type,
            connectid = request.connectid,
            level = request.level,
            role = request.role,
            use = request.use,
            logindate = request.logindate,
            lastchangepasswddate = request.lastchangepasswddate,
            date = request.date ?: LocalDateTime.now()
        )
        return userRepository.save(entity)
    }

    fun createBatch(requests: List<UserCreateRequest>): List<User> {
        val entities = requests.map { request ->
            User(
                loginid = request.loginid,
                passwd = request.passwd,
                email = request.email,
                name = request.name,
                tel = request.tel,
                address = request.address,
                image = request.image,
                sex = request.sex,
                birth = request.birth,
                type = request.type,
                connectid = request.connectid,
                level = request.level,
                role = request.role,
                use = request.use,
                logindate = request.logindate,
                lastchangepasswddate = request.lastchangepasswddate,
                date = request.date ?: LocalDateTime.now()
            )
        }
        return userRepository.saveAll(entities)
    }

    fun update(request: UserUpdateRequest): User? {
        val existing = userRepository.findById(request.id).orElse(null) ?: return null
        val updated = existing.copy(
            loginid = request.loginid,
            passwd = request.passwd,
            email = request.email,
            name = request.name,
            tel = request.tel,
            address = request.address,
            image = request.image,
            sex = request.sex,
            birth = request.birth,
            type = request.type,
            connectid = request.connectid,
            level = request.level,
            role = request.role,
            use = request.use,
            logindate = request.logindate,
            lastchangepasswddate = request.lastchangepasswddate,
            date = request.date
        )
        return userRepository.save(updated)
    }

    fun delete(entity: User): Boolean {
        return try {
            userRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            userRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<User>): Boolean {
        return try {
            userRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}