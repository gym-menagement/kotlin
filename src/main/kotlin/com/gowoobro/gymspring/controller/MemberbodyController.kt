package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Memberbody
import com.gowoobro.gymspring.entity.MemberbodyCreateRequest
import com.gowoobro.gymspring.entity.MemberbodyUpdateRequest
import com.gowoobro.gymspring.service.MemberbodyService
import com.gowoobro.gymspring.entity.MemberbodyResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.math.BigDecimal

@RestController
@RequestMapping("/api/memberbody")
class MemberbodyController(
    private val memberbodyService: MemberbodyService) {

    private fun toResponse(memberbody: Memberbody): MemberbodyResponse {
        return MemberbodyResponse.from(memberbody)
    }

    @GetMapping
    fun getMemberbodys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<MemberbodyResponse>> {
        val res = memberbodyService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getMemberbody(@PathVariable id: Long): ResponseEntity<MemberbodyResponse> {
        val res = memberbodyService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getMemberbodyByUser(@RequestParam user: Long): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/height")
    fun getMemberbodyByHeight(@RequestParam height: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByHeight(height)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/weight")
    fun getMemberbodyByWeight(@RequestParam weight: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByWeight(weight)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/bodyfat")
    fun getMemberbodyByBodyfat(@RequestParam bodyfat: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByBodyfat(bodyfat)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/musclemass")
    fun getMemberbodyByMusclemass(@RequestParam musclemass: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByMusclemass(musclemass)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/bmi")
    fun getMemberbodyByBmi(@RequestParam bmi: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByBmi(bmi)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/skeletalmuscle")
    fun getMemberbodyBySkeletalmuscle(@RequestParam skeletalmuscle: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findBySkeletalmuscle(skeletalmuscle)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/bodywater")
    fun getMemberbodyByBodywater(@RequestParam bodywater: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByBodywater(bodywater)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/chest")
    fun getMemberbodyByChest(@RequestParam chest: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByChest(chest)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/waist")
    fun getMemberbodyByWaist(@RequestParam waist: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByWaist(waist)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/hip")
    fun getMemberbodyByHip(@RequestParam hip: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByHip(hip)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/arm")
    fun getMemberbodyByArm(@RequestParam arm: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByArm(arm)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/thigh")
    fun getMemberbodyByThigh(@RequestParam thigh: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByThigh(thigh)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getMemberbodyByNote(@RequestParam note: String): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/measureddate")
    fun getMemberbodyByMeasureddate(@RequestParam measureddate: LocalDateTime): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByMeasureddate(measureddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/measuredby")
    fun getMemberbodyByMeasuredby(@RequestParam measuredby: Long): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByMeasuredby(measuredby)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getMemberbodyByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = memberbodyService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMemberbody(@RequestBody request: MemberbodyCreateRequest): ResponseEntity<MemberbodyResponse> {
        return try {
            val res = memberbodyService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberbodys(@RequestBody requests: List<MemberbodyCreateRequest>): ResponseEntity<List<MemberbodyResponse>> {
        return try {
            val res = memberbodyService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMemberbody(
        @PathVariable id: Long,
        @RequestBody request: MemberbodyUpdateRequest
    ): ResponseEntity<MemberbodyResponse> {
        val updatedRequest = request.copy(id = id)
        val res = memberbodyService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMemberbody(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = memberbodyService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteMemberbodys(@RequestBody entities: List<Memberbody>): ResponseEntity<Map<String, Boolean>> {
        val success = memberbodyService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}