package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import com.gowoobro.gymspring.service.MembershipService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/membership")
class MembershipController(private val membershipService: MembershipService) {

    @GetMapping
    fun getMemberships(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Membership>> {
        val result = membershipService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getMembership(@PathVariable id: Long): ResponseEntity<Membership> {
        val result = membershipService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getMembershipByGym(@RequestParam gym: Long): ResponseEntity<List<Membership>> {
        val result = membershipService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/user")
    fun getMembershipByUser(@RequestParam user: Long): ResponseEntity<List<Membership>> {
        val result = membershipService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getMembershipByName(@RequestParam name: String): ResponseEntity<List<Membership>> {
        val result = membershipService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/sex")
    fun getMembershipBySex(@RequestParam sex: Int): ResponseEntity<List<Membership>> {
        val result = membershipService.findBySex(sex)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/birth")
    fun getMembershipByBirth(@RequestParam birth: LocalDateTime): ResponseEntity<List<Membership>> {
        val result = membershipService.findByBirth(birth)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/phonenum")
    fun getMembershipByPhonenum(@RequestParam phonenum: String): ResponseEntity<List<Membership>> {
        val result = membershipService.findByPhonenum(phonenum)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/address")
    fun getMembershipByAddress(@RequestParam address: String): ResponseEntity<List<Membership>> {
        val result = membershipService.findByAddress(address)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/image")
    fun getMembershipByImage(@RequestParam image: String): ResponseEntity<List<Membership>> {
        val result = membershipService.findByImage(image)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getMembershipByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Membership>> {
        val result = membershipService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = membershipService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMembership(@RequestBody request: MembershipCreateRequest): ResponseEntity<Membership> {
        return try {
            val result = membershipService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberships(@RequestBody requests: List<MembershipCreateRequest>): ResponseEntity<List<Membership>> {
        return try {
            val result = membershipService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMembership(
        @PathVariable id: Long,
        @RequestBody request: MembershipUpdateRequest
    ): ResponseEntity<Membership> {
        val updatedRequest = request.copy(id = id)
        val result = membershipService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMembership(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = membershipService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteMemberships(@RequestBody entities: List<Membership>): ResponseEntity<Map<String, Boolean>> {
        val success = membershipService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}