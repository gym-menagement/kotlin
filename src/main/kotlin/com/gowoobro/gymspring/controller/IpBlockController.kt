package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Ipblock
import com.gowoobro.gymspring.entity.IpblockCreateRequest
import com.gowoobro.gymspring.entity.IpblockUpdateRequest
import com.gowoobro.gymspring.service.IpblockService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ipblock")
class IpblockController(private val ipblockService: IpblockService) {

    @GetMapping
    fun getIpblocks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Ipblock>> {
        val result = ipblockService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getIpblock(@PathVariable id: Long): ResponseEntity<Ipblock> {
        val result = ipblockService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/id")
    fun getIpblockById(@RequestParam id: String): ResponseEntity<List<Ipblock>> {
        val result = ipblockService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/address")
    fun getIpblockByAddress(@RequestParam address: String): ResponseEntity<List<Ipblock>> {
        val result = ipblockService.findByAddress(address)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getIpblockByType(@RequestParam type: String): ResponseEntity<List<Ipblock>> {
        val result = ipblockService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/policy")
    fun getIpblockByPolicy(@RequestParam policy: String): ResponseEntity<List<Ipblock>> {
        val result = ipblockService.findByPolicy(policy)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/use")
    fun getIpblockByUse(@RequestParam use: String): ResponseEntity<List<Ipblock>> {
        val result = ipblockService.findByUse(use)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/order")
    fun getIpblockByOrder(@RequestParam order: String): ResponseEntity<List<Ipblock>> {
        val result = ipblockService.findByOrder(order)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getIpblockByDate(@RequestParam date: String): ResponseEntity<List<Ipblock>> {
        val result = ipblockService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = ipblockService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createIpblock(@RequestBody request: IpblockCreateRequest): ResponseEntity<Ipblock> {
        return try {
            val result = ipblockService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createIpblocks(@RequestBody requests: List<IpblockCreateRequest>): ResponseEntity<List<Ipblock>> {
        return try {
            val result = ipblockService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateIpblock(
        @PathVariable id: Long,
        @RequestBody request: IpblockUpdateRequest
    ): ResponseEntity<Ipblock> {
        val updatedRequest = request.copy(id = id)
        val result = ipblockService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteIpblock(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = ipblockService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteIpblocks(@RequestBody entities: List<Ipblock>): ResponseEntity<Map<String, Boolean>> {
        val success = ipblockService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}