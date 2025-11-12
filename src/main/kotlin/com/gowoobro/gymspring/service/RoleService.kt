package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Role
import com.gowoobro.gymspring.entity.RoleCreateRequest
import com.gowoobro.gymspring.entity.RoleUpdateRequest
import com.gowoobro.gymspring.repository.RoleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class RoleService(private val roleRepository: RoleRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Role> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return roleRepository.findAll(pageable)
    }

    fun findById(id: Long): Role? {
        return roleRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return roleRepository.count()
    }


    fun findByGym(gym: Long): List<Role> {
        return roleRepository.findByGym(gym)
    }

    fun findByRoleid(roleid: Int): List<Role> {
        return roleRepository.findByRoleid(roleid)
    }

    fun findByName(name: String): List<Role> {
        return roleRepository.findByName(name)
    }

    fun findByDate(date: LocalDateTime): List<Role> {
        return roleRepository.findByDate(date)
    }


    fun create(request: RoleCreateRequest): Role {
        val entity = Role(
            gym = request.gym,
            roleid = request.roleid,
            name = request.name,
            date = request.date,
        )
        return roleRepository.save(entity)
    }

    fun createBatch(requests: List<RoleCreateRequest>): List<Role> {
        val entities = requests.map { request ->
            Role(
                gym = request.gym,
                roleid = request.roleid,
                name = request.name,
                date = request.date,
            )
        }
        return roleRepository.saveAll(entities)
    }

    fun update(request: RoleUpdateRequest): Role? {
        val existing = roleRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            gym = request.gym,
            roleid = request.roleid,
            name = request.name,
            date = request.date,
        )
        return roleRepository.save(updated)
    }

    fun delete(entity: Role): Boolean {
        return try {
            roleRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            roleRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Role>): Boolean {
        return try {
            roleRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}