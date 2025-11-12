package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Ipblock
import com.gowoobro.gymspring.entity.IpblockCreateRequest
import com.gowoobro.gymspring.entity.IpblockUpdateRequest
import com.gowoobro.gymspring.service.IpblockService
import com.gowoobro.gymspring.entity.IpblockResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ipblock.Type
import com.gowoobro.gymspring.enums.ipblock.Policy
import com.gowoobro.gymspring.enums.ipblock.Use


@RestController
@RequestMapping("/api/ipblock")
class IpblockController(private val ipblockService: IpblockService) {

    @GetMapping
    fun getIpblocks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<IpblockResponse>> {
        val result = ipblockService.findAll(page, pageSize)
        val responsePage = result.map { IpblockResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getIpblock(@PathVariable id: Long): ResponseEntity<IpblockResponse> {
        val result = ipblockService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(IpblockResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/address")
    fun getIpblockByAddress(@RequestParam address: String): ResponseEntity<List<IpblockResponse>> {
        val result = ipblockService.findByAddress(address)
        return ResponseEntity.ok(result.map { IpblockResponse.from(it) } )
    }

    @GetMapping("/search/type")
    fun getIpblockByType(@RequestParam type: Type): ResponseEntity<List<IpblockResponse>> {
        val result = ipblockService.findByType(type)
        return ResponseEntity.ok(result.map { IpblockResponse.from(it) } )
    }

    @GetMapping("/search/policy")
    fun getIpblockByPolicy(@RequestParam policy: Policy): ResponseEntity<List<IpblockResponse>> {
        val result = ipblockService.findByPolicy(policy)
        return ResponseEntity.ok(result.map { IpblockResponse.from(it) } )
    }

    @GetMapping("/search/use")
    fun getIpblockByUse(@RequestParam use: Use): ResponseEntity<List<IpblockResponse>> {
        val result = ipblockService.findByUse(use)
        return ResponseEntity.ok(result.map { IpblockResponse.from(it) } )
    }

    @GetMapping("/search/order")
    fun getIpblockByOrder(@RequestParam order: Int): ResponseEntity<List<IpblockResponse>> {
        val result = ipblockService.findByOrder(order)
        return ResponseEntity.ok(result.map { IpblockResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getIpblockByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<IpblockResponse>> {
        val result = ipblockService.findByDate(date)
        return ResponseEntity.ok(result.map { IpblockResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = ipblockService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createIpblock(@RequestBody request: IpblockCreateRequest): ResponseEntity<IpblockResponse> {
        return try {
            val result = ipblockService.create(request)
            ResponseEntity.ok(IpblockResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createIpblocks(@RequestBody requests: List<IpblockCreateRequest>): ResponseEntity<List<IpblockResponse>> {
        return try {
            val result = ipblockService.createBatch(requests)
            return ResponseEntity.ok(result.map { IpblockResponse.from(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateIpblock(
        @PathVariable id: Long,
        @RequestBody request: IpblockUpdateRequest
    ): ResponseEntity<IpblockResponse> {
        val updatedRequest = request.copy(id = id)
        val result = ipblockService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(IpblockResponse.from(result))
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