package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
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


    fun findByLoginid(loginid: String): List<User> {
        return userRepository.findByLoginidWithJoin(loginid)
    }

    fun findByPasswd(passwd: String): List<User> {
        return userRepository.findByPasswdWithJoin(passwd)
    }

    fun findByEmail(email: String): List<User> {
        return userRepository.findByEmailWithJoin(email)
    }

    fun findByName(name: String): List<User> {
        return userRepository.findByNameWithJoin(name)
    }

    fun findByTel(tel: String): List<User> {
        return userRepository.findByTelWithJoin(tel)
    }

    fun findByAddress(address: String): List<User> {
        return userRepository.findByAddressWithJoin(address)
    }

    fun findByImage(image: String): List<User> {
        return userRepository.findByImageWithJoin(image)
    }

    fun findBySex(sex: Sex): List<User> {
        return userRepository.findBySexWithJoin(sex)
    }

    fun findByBirth(birth: LocalDateTime): List<User> {
        return userRepository.findByBirthWithJoin(birth)
    }

    fun findByType(type: Type): List<User> {
        return userRepository.findByTypeWithJoin(type)
    }

    fun findByConnectid(connectid: String): List<User> {
        return userRepository.findByConnectidWithJoin(connectid)
    }

    fun findByLevel(level: Level): List<User> {
        return userRepository.findByLevelWithJoin(level)
    }

    fun findByRole(role: Role): List<User> {
        return userRepository.findByRoleWithJoin(role)
    }

    fun findByUse(use: Use): List<User> {
        return userRepository.findByUseWithJoin(use)
    }

    fun findByLogindate(logindate: LocalDateTime): List<User> {
        return userRepository.findByLogindateWithJoin(logindate)
    }

    fun findByLastchangepasswddate(lastchangepasswddate: LocalDateTime): List<User> {
        return userRepository.findByLastchangepasswddateWithJoin(lastchangepasswddate)
    }

    fun findByDate(date: LocalDateTime): List<User> {
        return userRepository.findByDateWithJoin(date)
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
}