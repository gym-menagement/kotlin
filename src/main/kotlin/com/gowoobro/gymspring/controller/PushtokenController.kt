package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Pushtoken
import com.gowoobro.gymspring.entity.PushtokenCreateRequest
import com.gowoobro.gymspring.entity.PushtokenUpdateRequest
import com.gowoobro.gymspring.service.PushtokenService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.pushtoken.Isactive


@RestController
@RequestMapping("/api/pushtoken")
class PushtokenController(private val pushtokenService: PushtokenService) {

    @GetMapping
    fun getPushtokens(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Pushtoken>> {
        val result = pushtokenService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getPushtoken(@PathVariable id: Long): ResponseEntity<Pushtoken> {
        val result = pushtokenService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getPushtokenByUser(@RequestParam user: Long): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/token")
    fun getPushtokenByToken(@RequestParam token: String): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByToken(token)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/devicetype")
    fun getPushtokenByDevicetype(@RequestParam devicetype: String): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByDevicetype(devicetype)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/deviceid")
    fun getPushtokenByDeviceid(@RequestParam deviceid: String): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByDeviceid(deviceid)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/appversion")
    fun getPushtokenByAppversion(@RequestParam appversion: String): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByAppversion(appversion)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/isactive")
    fun getPushtokenByIsactive(@RequestParam isactive: Isactive): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByIsactive(isactive)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/createddate")
    fun getPushtokenByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByCreateddate(createddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/updateddate")
    fun getPushtokenByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getPushtokenByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Pushtoken>> {
        val result = pushtokenService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = pushtokenService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createPushtoken(@RequestBody request: PushtokenCreateRequest): ResponseEntity<Pushtoken> {
        return try {
            val result = pushtokenService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createPushtokens(@RequestBody requests: List<PushtokenCreateRequest>): ResponseEntity<List<Pushtoken>> {
        return try {
            val result = pushtokenService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updatePushtoken(
        @PathVariable id: Long,
        @RequestBody request: PushtokenUpdateRequest
    ): ResponseEntity<Pushtoken> {
        val updatedRequest = request.copy(id = id)
        val result = pushtokenService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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