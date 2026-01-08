package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Memberbody
import com.gowoobro.gymspring.entity.MemberbodyCreateRequest
import com.gowoobro.gymspring.entity.MemberbodyUpdateRequest
import com.gowoobro.gymspring.entity.MemberbodyPatchRequest
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
    fun getMemberbodys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) height: BigDecimal?,
        @RequestParam(required = false) weight: BigDecimal?,
        @RequestParam(required = false) bodyfat: BigDecimal?,
        @RequestParam(required = false) musclemass: BigDecimal?,
        @RequestParam(required = false) bmi: BigDecimal?,
        @RequestParam(required = false) skeletalmuscle: BigDecimal?,
        @RequestParam(required = false) bodywater: BigDecimal?,
        @RequestParam(required = false) chest: BigDecimal?,
        @RequestParam(required = false) waist: BigDecimal?,
        @RequestParam(required = false) hip: BigDecimal?,
        @RequestParam(required = false) arm: BigDecimal?,
        @RequestParam(required = false) thigh: BigDecimal?,
        @RequestParam(required = false) note: String?,
        @RequestParam(required = false) startmeasureddate: LocalDateTime?,
        @RequestParam(required = false) endmeasureddate: LocalDateTime?,
        @RequestParam(required = false) measuredby: Long?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || user != null || height != null || weight != null || bodyfat != null || musclemass != null || bmi != null || skeletalmuscle != null || bodywater != null || chest != null || waist != null || hip != null || arm != null || thigh != null || note != null || startmeasureddate != null || endmeasureddate != null || measuredby != null || startdate != null || enddate != null || false) {
            var filtered = memberbodyService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (height != null) {
                filtered = filtered.filter { it.height == height }
            }
            if (weight != null) {
                filtered = filtered.filter { it.weight == weight }
            }
            if (bodyfat != null) {
                filtered = filtered.filter { it.bodyfat == bodyfat }
            }
            if (musclemass != null) {
                filtered = filtered.filter { it.musclemass == musclemass }
            }
            if (bmi != null) {
                filtered = filtered.filter { it.bmi == bmi }
            }
            if (skeletalmuscle != null) {
                filtered = filtered.filter { it.skeletalmuscle == skeletalmuscle }
            }
            if (bodywater != null) {
                filtered = filtered.filter { it.bodywater == bodywater }
            }
            if (chest != null) {
                filtered = filtered.filter { it.chest == chest }
            }
            if (waist != null) {
                filtered = filtered.filter { it.waist == waist }
            }
            if (hip != null) {
                filtered = filtered.filter { it.hip == hip }
            }
            if (arm != null) {
                filtered = filtered.filter { it.arm == arm }
            }
            if (thigh != null) {
                filtered = filtered.filter { it.thigh == thigh }
            }
            if (note != null) {
                filtered = filtered.filter { it.note == note }
            }
            if (startmeasureddate != null || endmeasureddate != null) {
                filtered = filtered.filter { filterByDateRange(it.measureddate, startmeasureddate, endmeasureddate) }
            }
            if (measuredby != null) {
                filtered = filtered.filter { it.measuredbyId == measuredby }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            memberbodyService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pagesize > 0) (totalElements + pagesize - 1) / pagesize else 1
        val startIndex = page * pagesize
        val endIndex = minOf(startIndex + pagesize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pagesize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
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


    @GetMapping("/search/gym")
    fun getMemberbodyByGym(@RequestParam gym: Long): ResponseEntity<List<MemberbodyResponse>> {
        val res = memberbodyService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
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

    @PatchMapping("/{id}")
    fun patchMemberbody(
        @PathVariable id: Long,
        @RequestBody request: MemberbodyPatchRequest
    ): ResponseEntity<MemberbodyResponse> {
        val patchRequest = request.copy(id = id)
        val res = memberbodyService.patch(patchRequest)
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