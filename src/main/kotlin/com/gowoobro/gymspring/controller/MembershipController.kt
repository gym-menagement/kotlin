package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import com.gowoobro.gymspring.service.MembershipService
import com.gowoobro.gymspring.entity.MembershipResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membership.Sex


@RestController
@RequestMapping("/api/membership")
class MembershipController(private val membershipService: MembershipService) {

    @GetMapping
    fun getMemberships(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<MembershipResponse>> {
        val result = membershipService.findAll(page, pageSize)
        val responsePage = result.map { MembershipResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getMembership(@PathVariable id: Long): ResponseEntity<MembershipResponse> {
        val result = membershipService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(MembershipResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getMembershipByGym(@RequestParam gym: Long): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByGym(gym)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/user")
    fun getMembershipByUser(@RequestParam user: Long): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByUser(user)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/name")
    fun getMembershipByName(@RequestParam name: String): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByName(name)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/sex")
    fun getMembershipBySex(@RequestParam sex: Sex): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findBySex(sex)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/birth")
    fun getMembershipByBirth(@RequestParam birth: LocalDateTime): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByBirth(birth)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/phonenum")
    fun getMembershipByPhonenum(@RequestParam phonenum: String): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByPhonenum(phonenum)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/address")
    fun getMembershipByAddress(@RequestParam address: String): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByAddress(address)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/image")
    fun getMembershipByImage(@RequestParam image: String): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByImage(image)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getMembershipByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<MembershipResponse>> {
        val result = membershipService.findByDate(date)
        return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = membershipService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMembership(@RequestBody request: MembershipCreateRequest): ResponseEntity<MembershipResponse> {
        return try {
            val result = membershipService.create(request)
            ResponseEntity.ok(MembershipResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberships(@RequestBody requests: List<MembershipCreateRequest>): ResponseEntity<List<MembershipResponse>> {
        return try {
            val result = membershipService.createBatch(requests)
            return ResponseEntity.ok(result.map { MembershipResponse.from(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMembership(
        @PathVariable id: Long,
        @RequestBody request: MembershipUpdateRequest
    ): ResponseEntity<MembershipResponse> {
        val updatedRequest = request.copy(id = id)
        val result = membershipService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(MembershipResponse.from(result))
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