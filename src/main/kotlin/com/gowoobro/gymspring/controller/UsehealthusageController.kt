package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Usehealthusage
import com.gowoobro.gymspring.entity.UsehealthusageCreateRequest
import com.gowoobro.gymspring.entity.UsehealthusageUpdateRequest
import com.gowoobro.gymspring.service.UsehealthusageService
import com.gowoobro.gymspring.entity.UsehealthusageResponse
import com.gowoobro.gymspring.enums.usehealthusage.Type

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/usehealthusage")
class UsehealthusageController(
    private val usehealthusageService: UsehealthusageService) {

    private fun toResponse(usehealthusage: Usehealthusage): UsehealthusageResponse {
        return UsehealthusageResponse.from(usehealthusage)
    }

    @GetMapping
    fun getUsehealthusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<UsehealthusageResponse>> {
        val res = usehealthusageService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getUsehealthusage(@PathVariable id: Long): ResponseEntity<UsehealthusageResponse> {
        val res = usehealthusageService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getUsehealthusageByGym(@RequestParam gym: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/usehealth")
    fun getUsehealthusageByUsehealth(@RequestParam usehealth: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByUsehealth(usehealth)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getUsehealthusageByUser(@RequestParam user: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/attendance")
    fun getUsehealthusageByAttendance(@RequestParam attendance: Long): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByAttendance(attendance)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getUsehealthusageByType(@RequestParam type: Type): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/usedcount")
    fun getUsehealthusageByUsedcount(@RequestParam usedcount: Int): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByUsedcount(usedcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/remainingcount")
    fun getUsehealthusageByRemainingcount(@RequestParam remainingcount: Int): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByRemainingcount(remainingcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/checkintime")
    fun getUsehealthusageByCheckintime(@RequestParam checkintime: LocalDateTime): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByCheckintime(checkintime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/checkouttime")
    fun getUsehealthusageByCheckouttime(@RequestParam checkouttime: LocalDateTime): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByCheckouttime(checkouttime)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/duration")
    fun getUsehealthusageByDuration(@RequestParam duration: Int): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByDuration(duration)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getUsehealthusageByNote(@RequestParam note: String): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getUsehealthusageByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<UsehealthusageResponse>> {
        val res = usehealthusageService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = usehealthusageService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createUsehealthusage(@RequestBody request: UsehealthusageCreateRequest): ResponseEntity<UsehealthusageResponse> {
        return try {
            val res = usehealthusageService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createUsehealthusages(@RequestBody requests: List<UsehealthusageCreateRequest>): ResponseEntity<List<UsehealthusageResponse>> {
        return try {
            val res = usehealthusageService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateUsehealthusage(
        @PathVariable id: Long,
        @RequestBody request: UsehealthusageUpdateRequest
    ): ResponseEntity<UsehealthusageResponse> {
        val updatedRequest = request.copy(id = id)
        val res = usehealthusageService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUsehealthusage(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthusageService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteUsehealthusages(@RequestBody entities: List<Usehealthusage>): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthusageService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}