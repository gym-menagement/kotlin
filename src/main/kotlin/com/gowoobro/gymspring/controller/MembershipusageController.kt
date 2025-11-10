package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Membershipusage
import com.gowoobro.gymspring.entity.MembershipusageCreateRequest
import com.gowoobro.gymspring.entity.MembershipusageUpdateRequest
import com.gowoobro.gymspring.service.MembershipusageService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/membershipusage")
class MembershipusageController(private val membershipusageService: MembershipusageService) {

    @GetMapping
    fun getMembershipusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Membershipusage>> {
        val result = membershipusageService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getMembershipusage(@PathVariable id: Long): ResponseEntity<Membershipusage> {
        val result = membershipusageService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/membership")
    fun getMembershipusageByMembership(@RequestParam membership: Long): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByMembership(membership)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/user")
    fun getMembershipusageByUser(@RequestParam user: Long): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getMembershipusageByType(@RequestParam type: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/totaldays")
    fun getMembershipusageByTotaldays(@RequestParam totaldays: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByTotaldays(totaldays)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/useddays")
    fun getMembershipusageByUseddays(@RequestParam useddays: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByUseddays(useddays)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/remainingdays")
    fun getMembershipusageByRemainingdays(@RequestParam remainingdays: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByRemainingdays(remainingdays)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/totalcount")
    fun getMembershipusageByTotalcount(@RequestParam totalcount: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByTotalcount(totalcount)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/usedcount")
    fun getMembershipusageByUsedcount(@RequestParam usedcount: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByUsedcount(usedcount)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/remainingcount")
    fun getMembershipusageByRemainingcount(@RequestParam remainingcount: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByRemainingcount(remainingcount)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/startdate")
    fun getMembershipusageByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByStartdate(startdate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/enddate")
    fun getMembershipusageByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByEnddate(enddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getMembershipusageByStatus(@RequestParam status: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/pausedays")
    fun getMembershipusageByPausedays(@RequestParam pausedays: Int): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByPausedays(pausedays)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/lastuseddate")
    fun getMembershipusageByLastuseddate(@RequestParam lastuseddate: LocalDateTime): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByLastuseddate(lastuseddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getMembershipusageByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Membershipusage>> {
        val result = membershipusageService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = membershipusageService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMembershipusage(@RequestBody request: MembershipusageCreateRequest): ResponseEntity<Membershipusage> {
        return try {
            val result = membershipusageService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMembershipusages(@RequestBody requests: List<MembershipusageCreateRequest>): ResponseEntity<List<Membershipusage>> {
        return try {
            val result = membershipusageService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMembershipusage(
        @PathVariable id: Long,
        @RequestBody request: MembershipusageUpdateRequest
    ): ResponseEntity<Membershipusage> {
        val updatedRequest = request.copy(id = id)
        val result = membershipusageService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMembershipusage(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = membershipusageService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteMembershipusages(@RequestBody entities: List<Membershipusage>): ResponseEntity<Map<String, Boolean>> {
        val success = membershipusageService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}