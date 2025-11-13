package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Memberqr
import com.gowoobro.gymspring.entity.MemberqrCreateRequest
import com.gowoobro.gymspring.entity.MemberqrUpdateRequest
import com.gowoobro.gymspring.service.MemberqrService
import com.gowoobro.gymspring.entity.MemberqrResponse
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.memberqr.Isactive


@RestController
@RequestMapping("/api/memberqr")
class MemberqrController(
    private val memberqrService: MemberqrService, private val userService: UserService) {

    private fun toResponse(memberqr: Memberqr):
    MemberqrResponse {
        
        val user = userService.findById(memberqr.user)
        val userResponse = user?.let{ UserResponse.from(it) }
        
        return MemberqrResponse.from(memberqr, userResponse)
    }

    @GetMapping
    fun getMemberqrs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<MemberqrResponse>> {
        val result = memberqrService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getMemberqr(@PathVariable id: Long): ResponseEntity<MemberqrResponse> {
        val result = memberqrService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getMemberqrByUser(@RequestParam user: Long): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByUser(user)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/code")
    fun getMemberqrByCode(@RequestParam code: String): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByCode(code)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/imageurl")
    fun getMemberqrByImageurl(@RequestParam imageurl: String): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByImageurl(imageurl)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/isactive")
    fun getMemberqrByIsactive(@RequestParam isactive: Isactive): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByIsactive(isactive)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/expiredate")
    fun getMemberqrByExpiredate(@RequestParam expiredate: LocalDateTime): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByExpiredate(expiredate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/generateddate")
    fun getMemberqrByGenerateddate(@RequestParam generateddate: LocalDateTime): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByGenerateddate(generateddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/lastuseddate")
    fun getMemberqrByLastuseddate(@RequestParam lastuseddate: LocalDateTime): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByLastuseddate(lastuseddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/usecount")
    fun getMemberqrByUsecount(@RequestParam usecount: Int): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByUsecount(usecount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getMemberqrByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<MemberqrResponse>> {
        val result = memberqrService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = memberqrService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMemberqr(@RequestBody request: MemberqrCreateRequest): ResponseEntity<MemberqrResponse> {
        return try {
            val result = memberqrService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberqrs(@RequestBody requests: List<MemberqrCreateRequest>): ResponseEntity<List<MemberqrResponse>> {
        return try {
            val result = memberqrService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMemberqr(
        @PathVariable id: Long,
        @RequestBody request: MemberqrUpdateRequest
    ): ResponseEntity<MemberqrResponse> {
        val updatedRequest = request.copy(id = id)
        val result = memberqrService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMemberqr(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = memberqrService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteMemberqrs(@RequestBody entities: List<Memberqr>): ResponseEntity<Map<String, Boolean>> {
        val success = memberqrService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}