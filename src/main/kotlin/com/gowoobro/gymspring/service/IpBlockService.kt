package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Ipblock
import com.gowoobro.gymspring.entity.IpblockCreateRequest
import com.gowoobro.gymspring.entity.IpblockUpdateRequest
import com.gowoobro.gymspring.repository.IpblockRepository
import com.gowoobro.gymspring.entity.Type
import com.gowoobro.gymspring.entity.Status
import com.gowoobro.gymspring.entity.Policy
import com.gowoobro.gymspring.entity.Use
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
    
    fun findByAddressContaining(address: String): List<Ipblock> {
        return ipblockRepository.findByAddressContaining(address)
    }
    
    fun findByType(type: Type): List<Ipblock> {
        return ipblockRepository.findByType(type)
    }
    
    fun findByPolicy(policy: Policy): List<Ipblock> {
        return ipblockRepository.findByPolicy(policy)
    }
    
    fun findByUse(use: Use): List<Ipblock> {
        return ipblockRepository.findByUse(use)
    }
    
    fun findByOrder(order: Int): List<Ipblock> {
        return ipblockRepository.findByOrder(order)
    }
    
    fun count(): Long {
        return ipblockRepository.count()
    }
    
    fun create(request: IpblockCreateRequest): Ipblock {
        val entity = Ipblock(
            address = request.address,
            type = request.type,
            policy = request.policy,
            use = request.use,
            order = request.order,
            date = request.date,
        )
        return ipblockRepository.save(entity)
    }
    
    fun createBatch(requests: List<IpblockCreateRequest>): List<Ipblock> {
        val entities = requests.map { request ->
            Ipblock(
                address = request.address,
                type = request.type,
                policy = request.policy,
                use = request.use,
                order = request.order,
                date = request.date,
            )
        }
        return ipblockRepository.saveAll(entities)
    }
    
    fun update(request: IpblockUpdateRequest): Ipblock? {
        val existing = ipblockRepository.findById(request.id).orElse(null) ?: return null
        
        val updated = existing.copy(
            address = request.address,
            type = request.type,
            policy = request.policy,
            use = request.use,
            order = request.order,
            date = request.date,
        )
        return ipblockRepository.save(updated)
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