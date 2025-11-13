package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Rockergroup
import com.gowoobro.gymspring.entity.RockergroupCreateRequest
import com.gowoobro.gymspring.entity.RockergroupUpdateRequest
import com.gowoobro.gymspring.service.RockergroupService
import com.gowoobro.gymspring.entity.RockergroupResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/rockergroup")
class RockergroupController(
    private val rockergroupService: RockergroupService, private val gymService: GymService) {

    private fun toResponse(rockergroup: Rockergroup):
    RockergroupResponse {
        
        val gym = gymService.findById(rockergroup.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        return RockergroupResponse.from(rockergroup, gymResponse)
    }

    @GetMapping
    fun getRockergroups(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<RockergroupResponse>> {
        val result = rockergroupService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getRockergroup(@PathVariable id: Long): ResponseEntity<RockergroupResponse> {
        val result = rockergroupService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getRockergroupByGym(@RequestParam gym: Long): ResponseEntity<List<RockergroupResponse>> {
        val result = rockergroupService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getRockergroupByName(@RequestParam name: String): ResponseEntity<List<RockergroupResponse>> {
        val result = rockergroupService.findByName(name)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getRockergroupByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<RockergroupResponse>> {
        val result = rockergroupService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = rockergroupService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createRockergroup(@RequestBody request: RockergroupCreateRequest): ResponseEntity<RockergroupResponse> {
        return try {
            val result = rockergroupService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createRockergroups(@RequestBody requests: List<RockergroupCreateRequest>): ResponseEntity<List<RockergroupResponse>> {
        return try {
            val result = rockergroupService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRockergroup(
        @PathVariable id: Long,
        @RequestBody request: RockergroupUpdateRequest
    ): ResponseEntity<RockergroupResponse> {
        val updatedRequest = request.copy(id = id)
        val result = rockergroupService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteRockergroup(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = rockergroupService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteRockergroups(@RequestBody entities: List<Rockergroup>): ResponseEntity<Map<String, Boolean>> {
        val success = rockergroupService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}