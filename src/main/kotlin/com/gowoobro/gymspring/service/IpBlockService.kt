package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Ipblock
import com.gowoobro.gymspring.entity.IpblockCreateRequest
import com.gowoobro.gymspring.entity.IpblockUpdateRequest
import com.gowoobro.gymspring.repository.IpblockRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class IpblockService(private val ipblockRepository: IpblockRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Ipblock> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return ipblockRepository.findAll(pageable)
    }

    fun findById(id: Long): Ipblock? {
        return ipblockRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return ipblockRepository.count()
    }


    fun findById(id: String): List<Ipblock> {
        return ipblockRepository.findById(id)
    }

    fun findByAddress(address: String): List<Ipblock> {
        return ipblockRepository.findByAddress(address)
    }

    fun findByType(type: String): List<Ipblock> {
        return ipblockRepository.findByType(type)
    }

    fun findByPolicy(policy: String): List<Ipblock> {
        return ipblockRepository.findByPolicy(policy)
    }

    fun findByUse(use: String): List<Ipblock> {
        return ipblockRepository.findByUse(use)
    }

    fun findByOrder(order: String): List<Ipblock> {
        return ipblockRepository.findByOrder(order)
    }

    fun findByDate(date: String): List<Ipblock> {
        return ipblockRepository.findByDate(date)
    }


    fun create(request: IpblockCreateRequest): Ipblock {
        val entity = Ipblock()
        return ipblockRepository.save(entity)
    }

    fun createBatch(requests: List<IpblockCreateRequest>): List<Ipblock> {
        val entities = requests.map { request ->
            Ipblock()
        }
        return ipblockRepository.saveAll(entities)
    }

    fun update(request: IpblockUpdateRequest): Ipblock? {
        val existing = ipblockRepository.findById(request.id).orElse(null) ?: return null
        return ipblockRepository.save(existing)
    }

    fun delete(entity: Ipblock): Boolean {
        return try {
            ipblockRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            ipblockRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Ipblock>): Boolean {
        return try {
            ipblockRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}