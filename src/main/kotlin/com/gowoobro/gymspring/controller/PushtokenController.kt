package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Pushtoken
import com.gowoobro.gymspring.entity.PushtokenCreateRequest
import com.gowoobro.gymspring.entity.PushtokenUpdateRequest
import com.gowoobro.gymspring.entity.PushtokenPatchRequest
import com.gowoobro.gymspring.service.PushtokenService
import com.gowoobro.gymspring.entity.PushtokenResponse
import com.gowoobro.gymspring.enums.pushtoken.Isactive

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/pushtoken")
class PushtokenController(
    private val pushtokenService: PushtokenService) {

    private fun toResponse(pushtoken: Pushtoken): PushtokenResponse {
        return PushtokenResponse.from(pushtoken)
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
    fun getPushtokens(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) token: String?,
        @RequestParam(required = false) devicetype: String?,
        @RequestParam(required = false) deviceid: String?,
        @RequestParam(required = false) appversion: String?,
        @RequestParam(required = false) isactive: Int?,
        @RequestParam(required = false) startcreateddate: LocalDateTime?,
        @RequestParam(required = false) endcreateddate: LocalDateTime?,
        @RequestParam(required = false) startupdateddate: LocalDateTime?,
        @RequestParam(required = false) endupdateddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || token != null || devicetype != null || deviceid != null || appversion != null || isactive != null || startcreateddate != null || endcreateddate != null || startupdateddate != null || endupdateddate != null || startdate != null || enddate != null || false) {
            var filtered = pushtokenService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.user == user }
            }
            if (token != null) {
                filtered = filtered.filter { it.token == token }
            }
            if (devicetype != null) {
                filtered = filtered.filter { it.devicetype == devicetype }
            }
            if (deviceid != null) {
                filtered = filtered.filter { it.deviceid == deviceid }
            }
            if (appversion != null) {
                filtered = filtered.filter { it.appversion == appversion }
            }
            if (isactive != null) {
                filtered = filtered.filter { it.isactive.ordinal == isactive }
            }
            if (startcreateddate != null || endcreateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.createddate, startcreateddate, endcreateddate) }
            }
            if (startupdateddate != null || endupdateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.updateddate, startupdateddate, endupdateddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            pushtokenService.findAll(0, Int.MAX_VALUE).content
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
    fun getPushtoken(@PathVariable id: Long): ResponseEntity<PushtokenResponse> {
        val res = pushtokenService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getPushtokenByUser(@RequestParam user: Long): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/token")
    fun getPushtokenByToken(@RequestParam token: String): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByToken(token)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/devicetype")
    fun getPushtokenByDevicetype(@RequestParam devicetype: String): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByDevicetype(devicetype)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/deviceid")
    fun getPushtokenByDeviceid(@RequestParam deviceid: String): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByDeviceid(deviceid)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/appversion")
    fun getPushtokenByAppversion(@RequestParam appversion: String): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByAppversion(appversion)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/isactive")
    fun getPushtokenByIsactive(@RequestParam isactive: Isactive): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByIsactive(isactive)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getPushtokenByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByCreateddate(createddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/updateddate")
    fun getPushtokenByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPushtokenByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PushtokenResponse>> {
        val res = pushtokenService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = pushtokenService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPushtoken(@RequestBody request: PushtokenCreateRequest): ResponseEntity<PushtokenResponse> {
        return try {
            val res = pushtokenService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPushtokens(@RequestBody requests: List<PushtokenCreateRequest>): ResponseEntity<List<PushtokenResponse>> {
        return try {
            val res = pushtokenService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePushtoken(
        @PathVariable id: Long,
        @RequestBody request: PushtokenUpdateRequest
    ): ResponseEntity<PushtokenResponse> {
        val updatedRequest = request.copy(id = id)
        val res = pushtokenService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchPushtoken(
        @PathVariable id: Long,
        @RequestBody request: PushtokenPatchRequest
    ): ResponseEntity<PushtokenResponse> {
        val patchRequest = request.copy(id = id)
        val res = pushtokenService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePushtoken(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = pushtokenService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deletePushtokens(@RequestBody entities: List<Pushtoken>): ResponseEntity<Map<String, Boolean>> {
        val success = pushtokenService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}