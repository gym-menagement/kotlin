package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Membership
import com.gowoobro.gymspring.entity.MembershipCreateRequest
import com.gowoobro.gymspring.entity.MembershipUpdateRequest
import com.gowoobro.gymspring.service.MembershipService
import com.gowoobro.gymspring.entity.MembershipResponse
import com.gowoobro.gymspring.enums.membership.Sex

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/membership")
class MembershipController(
    private val membershipService: MembershipService) {

    private fun toResponse(membership: Membership): MembershipResponse {
        return MembershipResponse.from(membership)
    }

    @GetMapping
    fun getMemberships(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<MembershipResponse>> {
        val res = membershipService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getMembership(@PathVariable id: Long): ResponseEntity<MembershipResponse> {
        val res = membershipService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getMembershipByGym(@RequestParam gym: Long): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getMembershipByUser(@RequestParam user: Long): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getMembershipByName(@RequestParam name: String): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/sex")
    fun getMembershipBySex(@RequestParam sex: Sex): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findBySex(sex)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/birth")
    fun getMembershipByBirth(@RequestParam birth: LocalDateTime): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByBirth(birth)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/phonenum")
    fun getMembershipByPhonenum(@RequestParam phonenum: String): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByPhonenum(phonenum)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/address")
    fun getMembershipByAddress(@RequestParam address: String): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByAddress(address)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/image")
    fun getMembershipByImage(@RequestParam image: String): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByImage(image)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getMembershipByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<MembershipResponse>> {
        val res = membershipService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = membershipService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMembership(@RequestBody request: MembershipCreateRequest): ResponseEntity<MembershipResponse> {
        return try {
            val res = membershipService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberships(@RequestBody requests: List<MembershipCreateRequest>): ResponseEntity<List<MembershipResponse>> {
        return try {
            val res = membershipService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = membershipService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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