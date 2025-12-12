package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Loginlog
import com.gowoobro.gymspring.entity.LoginlogCreateRequest
import com.gowoobro.gymspring.entity.LoginlogUpdateRequest
import com.gowoobro.gymspring.entity.LoginlogPatchRequest
import com.gowoobro.gymspring.repository.LoginlogRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class LoginlogService(private val loginlogRepository: LoginlogRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Loginlog> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return loginlogRepository.findAll(pageable)
    }

    fun findById(id: Long): Loginlog? {
        return loginlogRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return loginlogRepository.count()
    }


    fun findByIp(ip: String): List<Loginlog> {
        return loginlogRepository.findByIp(ip)
    }

    fun findByIpvalue(ipvalue: Long): List<Loginlog> {
        return loginlogRepository.findByIpvalue(ipvalue)
    }

    fun findByUser(user: Long): List<Loginlog> {
        return loginlogRepository.findByUser(user)
    }

    fun findByDate(date: LocalDateTime): List<Loginlog> {
        return loginlogRepository.findByDate(date)
    }


    fun create(request: LoginlogCreateRequest): Loginlog {
        val entity = Loginlog(
            ip = request.ip,
            ipvalue = request.ipvalue,
            user = request.user,
            date = request.date,
        )
        return loginlogRepository.save(entity)
    }

    fun createBatch(requests: List<LoginlogCreateRequest>): List<Loginlog> {
        val entities = requests.map { request ->
            Loginlog(
                ip = request.ip,
                ipvalue = request.ipvalue,
                user = request.user,
                date = request.date,
            )
        }
        return loginlogRepository.saveAll(entities)
    }

    fun update(request: LoginlogUpdateRequest): Loginlog? {
        val existing = loginlogRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            ip = request.ip,
            ipvalue = request.ipvalue,
            user = request.user,
            date = request.date,
        )
        return loginlogRepository.save(updated)
    }

    fun delete(entity: Loginlog): Boolean {
        return try {
            loginlogRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            loginlogRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Loginlog>): Boolean {
        return try {
            loginlogRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: LoginlogPatchRequest): Loginlog? {
        val existing = loginlogRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            ip = request.ip ?: existing.ip,
            ipvalue = request.ipvalue ?: existing.ipvalue,
            user = request.user ?: existing.user,
            date = request.date ?: existing.date,
        )
        return loginlogRepository.save(updated)
    }
}