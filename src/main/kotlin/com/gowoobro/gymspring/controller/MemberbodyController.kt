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
class MemberbodyController(private val memberbodyService: MemberbodyService) {

    @GetMapping
    fun getMemberbodys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<MemberbodyResponse>> {
        val result = memberbodyService.findAll(page, pageSize)
        val responsePage = result.map { MemberbodyResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getMemberbody(@PathVariable id: Long): ResponseEntity<MemberbodyResponse> {
        val result = memberbodyService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(MemberbodyResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getMemberbodyByUser(@RequestParam user: Long): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByUser(user)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/height")
    fun getMemberbodyByHeight(@RequestParam height: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByHeight(height)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/weight")
    fun getMemberbodyByWeight(@RequestParam weight: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByWeight(weight)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/bodyfat")
    fun getMemberbodyByBodyfat(@RequestParam bodyfat: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByBodyfat(bodyfat)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/musclemass")
    fun getMemberbodyByMusclemass(@RequestParam musclemass: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByMusclemass(musclemass)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/bmi")
    fun getMemberbodyByBmi(@RequestParam bmi: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByBmi(bmi)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/skeletalmuscle")
    fun getMemberbodyBySkeletalmuscle(@RequestParam skeletalmuscle: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findBySkeletalmuscle(skeletalmuscle)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/bodywater")
    fun getMemberbodyByBodywater(@RequestParam bodywater: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByBodywater(bodywater)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/chest")
    fun getMemberbodyByChest(@RequestParam chest: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByChest(chest)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/waist")
    fun getMemberbodyByWaist(@RequestParam waist: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByWaist(waist)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/hip")
    fun getMemberbodyByHip(@RequestParam hip: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByHip(hip)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/arm")
    fun getMemberbodyByArm(@RequestParam arm: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByArm(arm)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/thigh")
    fun getMemberbodyByThigh(@RequestParam thigh: BigDecimal): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByThigh(thigh)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/note")
    fun getMemberbodyByNote(@RequestParam note: String): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByNote(note)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/measureddate")
    fun getMemberbodyByMeasureddate(@RequestParam measureddate: LocalDateTime): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByMeasureddate(measureddate)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/measuredby")
    fun getMemberbodyByMeasuredby(@RequestParam measuredby: Long): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByMeasuredby(measuredby)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getMemberbodyByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<MemberbodyResponse>> {
        val result = memberbodyService.findByDate(date)
        return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = memberbodyService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMemberbody(@RequestBody request: MemberbodyCreateRequest): ResponseEntity<MemberbodyResponse> {
        return try {
            val result = memberbodyService.create(request)
            ResponseEntity.ok(MemberbodyResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberbodys(@RequestBody requests: List<MemberbodyCreateRequest>): ResponseEntity<List<MemberbodyResponse>> {
        return try {
            val result = memberbodyService.createBatch(requests)
            return ResponseEntity.ok(result.map { MemberbodyResponse.from(it) } )
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
        val result = memberbodyService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(MemberbodyResponse.from(result))
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