package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Pushtoken
import com.gowoobro.gymspring.entity.PushtokenCreateRequest
import com.gowoobro.gymspring.entity.PushtokenUpdateRequest
import com.gowoobro.gymspring.service.PushtokenService
import com.gowoobro.gymspring.entity.PushtokenResponse
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.pushtoken.Isactive


@RestController
@RequestMapping("/api/pushtoken")
class PushtokenController(
    private val pushtokenService: PushtokenService, private val userService: UserService) {

    private fun toResponse(pushtoken: Pushtoken):
    PushtokenResponse {
        
        val user = userService.findById(pushtoken.user)
        val userResponse = user?.let{ UserResponse.from(it) }
        
        return PushtokenResponse.from(pushtoken, userResponse)
    }

    @GetMapping
    fun getPushtokens(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<PushtokenResponse>> {
        val result = pushtokenService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getPushtoken(@PathVariable id: Long): ResponseEntity<PushtokenResponse> {
        val result = pushtokenService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getPushtokenByUser(@RequestParam user: Long): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByUser(user)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/token")
    fun getPushtokenByToken(@RequestParam token: String): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByToken(token)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/devicetype")
    fun getPushtokenByDevicetype(@RequestParam devicetype: String): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByDevicetype(devicetype)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/deviceid")
    fun getPushtokenByDeviceid(@RequestParam deviceid: String): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByDeviceid(deviceid)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/appversion")
    fun getPushtokenByAppversion(@RequestParam appversion: String): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByAppversion(appversion)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/isactive")
    fun getPushtokenByIsactive(@RequestParam isactive: Isactive): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByIsactive(isactive)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getPushtokenByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByCreateddate(createddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/updateddate")
    fun getPushtokenByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getPushtokenByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<PushtokenResponse>> {
        val result = pushtokenService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = pushtokenService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPushtoken(@RequestBody request: PushtokenCreateRequest): ResponseEntity<PushtokenResponse> {
        return try {
            val result = pushtokenService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPushtokens(@RequestBody requests: List<PushtokenCreateRequest>): ResponseEntity<List<PushtokenResponse>> {
        return try {
            val result = pushtokenService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = pushtokenService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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