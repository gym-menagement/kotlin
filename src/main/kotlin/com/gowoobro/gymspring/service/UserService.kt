package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
import com.gowoobro.gymspring.entity.UserPatchRequest
import com.gowoobro.gymspring.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.user.Level
import com.gowoobro.gymspring.enums.user.Use
import com.gowoobro.gymspring.enums.user.Type
import com.gowoobro.gymspring.enums.user.Role
import com.gowoobro.gymspring.enums.user.Sex


@Service
@Transactional
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun findAll(page: Int = 0, pagesize: Int = 10): Page<User> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return userRepository.findAll(pageable)
    }

    fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return userRepository.count()
    }


    fun findByLoginid(loginid: String): List<User> {
        return userRepository.findByLoginid(loginid)
    }

    fun findByPasswd(passwd: String): List<User> {
        return userRepository.findByPasswd(passwd)
    }

    fun findByEmail(email: String): List<User> {
        return userRepository.findByEmail(email)
    }

    fun findByName(name: String): List<User> {
        return userRepository.findByName(name)
    }

    fun findByTel(tel: String): List<User> {
        return userRepository.findByTel(tel)
    }

    fun findByAddress(address: String): List<User> {
        return userRepository.findByAddress(address)
    }

    fun findByImage(image: String): List<User> {
        return userRepository.findByImage(image)
    }

    fun findBySex(sex: Sex): List<User> {
        return userRepository.findBySex(sex)
    }

    fun findByBirth(birth: LocalDateTime): List<User> {
        return userRepository.findByBirth(birth)
    }

    fun findByType(type: Type): List<User> {
        return userRepository.findByType(type)
    }

    fun findByConnectid(connectid: String): List<User> {
        return userRepository.findByConnectid(connectid)
    }

    fun findByLevel(level: Level): List<User> {
        return userRepository.findByLevel(level)
    }

    fun findByRole(role: Role): List<User> {
        return userRepository.findByRole(role)
    }

    fun findByUse(use: Use): List<User> {
        return userRepository.findByUse(use)
    }

    fun findByLogindate(logindate: LocalDateTime): List<User> {
        return userRepository.findByLogindate(logindate)
    }

    fun findByLastchangepasswddate(lastchangepasswddate: LocalDateTime): List<User> {
        return userRepository.findByLastchangepasswddate(lastchangepasswddate)
    }

    fun findByDate(date: LocalDateTime): List<User> {
        return userRepository.findByDate(date)
    }


    fun create(request: UserCreateRequest): User {
        val entity = User(
            loginid = request.loginid,
            passwd = passwordEncoder.encode(request.passwd),
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
            date = request.date,
        )
        return userRepository.save(entity)
    }

    fun createBatch(requests: List<UserCreateRequest>): List<User> {
        val entities = requests.map { request ->
            User(
                loginid = request.loginid,
                passwd = passwordEncoder.encode(request.passwd),
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
                date = request.date,
            )
        }
        return userRepository.saveAll(entities)
    }

    fun update(request: UserUpdateRequest): User? {
        val existing = userRepository.findById(request.id).orElse(null) ?: return null
        val encodedPassword = if (request.passwd != existing.passwd && !request.passwd.startsWith("\$2a\$")) {
            passwordEncoder.encode(request.passwd)
        } else {
            request.passwd
        }
        

        val updated = existing.copy(
            loginid = request.loginid,
            passwd = encodedPassword,
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
            date = request.date,
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

    fun patch(request: UserPatchRequest): User? {
        val existing = userRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            loginid = request.loginid ?: existing.loginid,
            passwd = request.passwd ?: existing.passwd,
            email = request.email ?: existing.email,
            name = request.name ?: existing.name,
            tel = request.tel ?: existing.tel,
            address = request.address ?: existing.address,
            image = request.image ?: existing.image,
            sex = request.sex ?: existing.sex,
            birth = request.birth ?: existing.birth,
            type = request.type ?: existing.type,
            connectid = request.connectid ?: existing.connectid,
            level = request.level ?: existing.level,
            role = request.role ?: existing.role,
            use = request.use ?: existing.use,
            logindate = request.logindate ?: existing.logindate,
            lastchangepasswddate = request.lastchangepasswddate ?: existing.lastchangepasswddate,
            date = request.date ?: existing.date,
        )
        return userRepository.save(updated)
    }
}