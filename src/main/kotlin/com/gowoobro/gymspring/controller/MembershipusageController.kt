package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Membershipusage
import com.gowoobro.gymspring.entity.MembershipusageCreateRequest
import com.gowoobro.gymspring.entity.MembershipusageUpdateRequest
import com.gowoobro.gymspring.service.MembershipusageService
import com.gowoobro.gymspring.entity.MembershipusageResponse
import com.gowoobro.gymspring.entity.MembershipResponse
import com.gowoobro.gymspring.service.MembershipService
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.membershipusage.Type
import com.gowoobro.gymspring.enums.membershipusage.Status


@RestController
@RequestMapping("/api/membershipusage")
class MembershipusageController(
    private val membershipusageService: MembershipusageService, private val membershipService: MembershipService, private val userService: UserService) {

    private fun toResponse(membershipusage: Membershipusage):
    MembershipusageResponse {
        
        val membership = membershipService.findById(membershipusage.membership)
        val membershipResponse = membership?.let{ MembershipResponse.from(it) }
        
        val user = userService.findById(membershipusage.user)
        val userResponse = user?.let{ UserResponse.from(it) }
        
        return MembershipusageResponse.from(membershipusage, membershipResponse, userResponse)
    }

    @GetMapping
    fun getMembershipusages(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<MembershipusageResponse>> {
        val result = membershipusageService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getMembershipusage(@PathVariable id: Long): ResponseEntity<MembershipusageResponse> {
        val result = membershipusageService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/membership")
    fun getMembershipusageByMembership(@RequestParam membership: Long): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByMembership(membership)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getMembershipusageByUser(@RequestParam user: Long): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByUser(user)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getMembershipusageByType(@RequestParam type: Type): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByType(type)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/totaldays")
    fun getMembershipusageByTotaldays(@RequestParam totaldays: Int): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByTotaldays(totaldays)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/useddays")
    fun getMembershipusageByUseddays(@RequestParam useddays: Int): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByUseddays(useddays)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/remainingdays")
    fun getMembershipusageByRemainingdays(@RequestParam remainingdays: Int): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByRemainingdays(remainingdays)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/totalcount")
    fun getMembershipusageByTotalcount(@RequestParam totalcount: Int): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByTotalcount(totalcount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/usedcount")
    fun getMembershipusageByUsedcount(@RequestParam usedcount: Int): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByUsedcount(usedcount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/remainingcount")
    fun getMembershipusageByRemainingcount(@RequestParam remainingcount: Int): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByRemainingcount(remainingcount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getMembershipusageByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByStartdate(startdate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getMembershipusageByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByEnddate(enddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getMembershipusageByStatus(@RequestParam status: Status): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByStatus(status)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/pausedays")
    fun getMembershipusageByPausedays(@RequestParam pausedays: Int): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByPausedays(pausedays)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/lastuseddate")
    fun getMembershipusageByLastuseddate(@RequestParam lastuseddate: LocalDateTime): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByLastuseddate(lastuseddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getMembershipusageByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<MembershipusageResponse>> {
        val result = membershipusageService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = membershipusageService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMembershipusage(@RequestBody request: MembershipusageCreateRequest): ResponseEntity<MembershipusageResponse> {
        return try {
            val result = membershipusageService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMembershipusages(@RequestBody requests: List<MembershipusageCreateRequest>): ResponseEntity<List<MembershipusageResponse>> {
        return try {
            val result = membershipusageService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMembershipusage(
        @PathVariable id: Long,
        @RequestBody request: MembershipusageUpdateRequest
    ): ResponseEntity<MembershipusageResponse> {
        val updatedRequest = request.copy(id = id)
        val result = membershipusageService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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