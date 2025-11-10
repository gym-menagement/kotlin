package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Memberbody
import com.gowoobro.gymspring.entity.MemberbodyCreateRequest
import com.gowoobro.gymspring.entity.MemberbodyUpdateRequest
import com.gowoobro.gymspring.service.MemberbodyService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.math.BigDecimal


@RestController
@RequestMapping("/api/memberbody")
class MemberbodyController(private val memberbodyService: MemberbodyService) {

    @GetMapping
    fun getMemberbodys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Memberbody>> {
        val result = memberbodyService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getMemberbody(@PathVariable id: Long): ResponseEntity<Memberbody> {
        val result = memberbodyService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getMemberbodyByUser(@RequestParam user: Long): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/height")
    fun getMemberbodyByHeight(@RequestParam height: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByHeight(height)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/weight")
    fun getMemberbodyByWeight(@RequestParam weight: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByWeight(weight)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/bodyfat")
    fun getMemberbodyByBodyfat(@RequestParam bodyfat: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByBodyfat(bodyfat)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/musclemass")
    fun getMemberbodyByMusclemass(@RequestParam musclemass: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByMusclemass(musclemass)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/bmi")
    fun getMemberbodyByBmi(@RequestParam bmi: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByBmi(bmi)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/skeletalmuscle")
    fun getMemberbodyBySkeletalmuscle(@RequestParam skeletalmuscle: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findBySkeletalmuscle(skeletalmuscle)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/bodywater")
    fun getMemberbodyByBodywater(@RequestParam bodywater: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByBodywater(bodywater)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/chest")
    fun getMemberbodyByChest(@RequestParam chest: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByChest(chest)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/waist")
    fun getMemberbodyByWaist(@RequestParam waist: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByWaist(waist)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/hip")
    fun getMemberbodyByHip(@RequestParam hip: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByHip(hip)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/arm")
    fun getMemberbodyByArm(@RequestParam arm: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByArm(arm)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/thigh")
    fun getMemberbodyByThigh(@RequestParam thigh: BigDecimal): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByThigh(thigh)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/note")
    fun getMemberbodyByNote(@RequestParam note: String): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByNote(note)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/measureddate")
    fun getMemberbodyByMeasureddate(@RequestParam measureddate: LocalDateTime): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByMeasureddate(measureddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/measuredby")
    fun getMemberbodyByMeasuredby(@RequestParam measuredby: Long): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByMeasuredby(measuredby)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getMemberbodyByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Memberbody>> {
        val result = memberbodyService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = memberbodyService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMemberbody(@RequestBody request: MemberbodyCreateRequest): ResponseEntity<Memberbody> {
        return try {
            val result = memberbodyService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberbodys(@RequestBody requests: List<MemberbodyCreateRequest>): ResponseEntity<List<Memberbody>> {
        return try {
            val result = memberbodyService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMemberbody(
        @PathVariable id: Long,
        @RequestBody request: MemberbodyUpdateRequest
    ): ResponseEntity<Memberbody> {
        val updatedRequest = request.copy(id = id)
        val result = memberbodyService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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