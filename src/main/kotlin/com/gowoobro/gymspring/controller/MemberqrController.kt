package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Memberqr
import com.gowoobro.gymspring.entity.MemberqrCreateRequest
import com.gowoobro.gymspring.entity.MemberqrUpdateRequest
import com.gowoobro.gymspring.service.MemberqrService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/memberqr")
class MemberqrController(private val memberqrService: MemberqrService) {

    @GetMapping
    fun getMemberqrs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Memberqr>> {
        val result = memberqrService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getMemberqr(@PathVariable id: Long): ResponseEntity<Memberqr> {
        val result = memberqrService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getMemberqrByUser(@RequestParam user: Long): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/code")
    fun getMemberqrByCode(@RequestParam code: String): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByCode(code)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/imageurl")
    fun getMemberqrByImageurl(@RequestParam imageurl: String): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByImageurl(imageurl)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/isactive")
    fun getMemberqrByIsactive(@RequestParam isactive: Int): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByIsactive(isactive)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/expiredate")
    fun getMemberqrByExpiredate(@RequestParam expiredate: LocalDateTime): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByExpiredate(expiredate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/generateddate")
    fun getMemberqrByGenerateddate(@RequestParam generateddate: LocalDateTime): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByGenerateddate(generateddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/lastuseddate")
    fun getMemberqrByLastuseddate(@RequestParam lastuseddate: LocalDateTime): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByLastuseddate(lastuseddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/usecount")
    fun getMemberqrByUsecount(@RequestParam usecount: Int): ResponseEntity<List<Memberqr>> {
        val result = memberqrService.findByUsecount(usecount)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = memberqrService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createMemberqr(@RequestBody request: MemberqrCreateRequest): ResponseEntity<Memberqr> {
        return try {
            val result = memberqrService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createMemberqrs(@RequestBody requests: List<MemberqrCreateRequest>): ResponseEntity<List<Memberqr>> {
        return try {
            val result = memberqrService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateMemberqr(
        @PathVariable id: Long,
        @RequestBody request: MemberqrUpdateRequest
    ): ResponseEntity<Memberqr> {
        val updatedRequest = request.copy(id = id)
        val result = memberqrService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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