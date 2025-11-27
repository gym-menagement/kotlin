package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Ipblock
import com.gowoobro.gymspring.entity.IpblockCreateRequest
import com.gowoobro.gymspring.entity.IpblockUpdateRequest
import com.gowoobro.gymspring.service.IpblockService
import com.gowoobro.gymspring.entity.IpblockResponse
import com.gowoobro.gymspring.enums.ipblock.Type
import com.gowoobro.gymspring.enums.ipblock.Policy
import com.gowoobro.gymspring.enums.ipblock.Use

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/ipblock")
class IpblockController(
    private val ipblockService: IpblockService) {

    private fun toResponse(ipblock: Ipblock): IpblockResponse {
        return IpblockResponse.from(ipblock)
    }

    private fun filterByDateRange(
        value: LocalDateTime?,
        startRange: LocalDateTime?,
        endRange: LocalDateTime?
    ): Boolean {
        if (value == null) return false
        return when {
            startRange != null && endRange != null -> value in startRange..endRange
            startRange != null -> value >= startRange
            endRange != null -> value <= endRange
            else -> true
        }
    }

    @GetMapping
    fun getIpblocks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) address: String?,
        @RequestParam(required = false) type: Type?,
        @RequestParam(required = false) policy: Policy?,
        @RequestParam(required = false) use: Use?,
        @RequestParam(required = false) order: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (address != null || type != null || policy != null || use != null || order != null || startdate != null || enddate != null || false) {
            var filtered = ipblockService.findAll(0, Int.MAX_VALUE).content
            if (address != null) {
                filtered = filtered.filter { it.address == address }
            }
            if (type != null) {
                filtered = filtered.filter { it.type == type }
            }
            if (policy != null) {
                filtered = filtered.filter { it.policy == policy }
            }
            if (use != null) {
                filtered = filtered.filter { it.use == use }
            }
            if (order != null) {
                filtered = filtered.filter { it.order == order }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            ipblockService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pageSize > 0) (totalElements + pageSize - 1) / pageSize else 1
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pageSize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getIpblock(@PathVariable id: Long): ResponseEntity<IpblockResponse> {
        val res = ipblockService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/address")
    fun getIpblockByAddress(@RequestParam address: String): ResponseEntity<List<IpblockResponse>> {
        val res = ipblockService.findByAddress(address)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getIpblockByType(@RequestParam type: Type): ResponseEntity<List<IpblockResponse>> {
        val res = ipblockService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/policy")
    fun getIpblockByPolicy(@RequestParam policy: Policy): ResponseEntity<List<IpblockResponse>> {
        val res = ipblockService.findByPolicy(policy)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/use")
    fun getIpblockByUse(@RequestParam use: Use): ResponseEntity<List<IpblockResponse>> {
        val res = ipblockService.findByUse(use)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/order")
    fun getIpblockByOrder(@RequestParam order: Int): ResponseEntity<List<IpblockResponse>> {
        val res = ipblockService.findByOrder(order)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getIpblockByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<IpblockResponse>> {
        val res = ipblockService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = ipblockService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createIpblock(@RequestBody request: IpblockCreateRequest): ResponseEntity<IpblockResponse> {
        return try {
            val res = ipblockService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createIpblocks(@RequestBody requests: List<IpblockCreateRequest>): ResponseEntity<List<IpblockResponse>> {
        return try {
            val res = ipblockService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = ipblockService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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