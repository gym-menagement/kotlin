package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
import com.gowoobro.gymspring.service.NoticeService
import com.gowoobro.gymspring.entity.NoticeResponse
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notice.Type
import com.gowoobro.gymspring.enums.notice.Ispopup
import com.gowoobro.gymspring.enums.notice.Ispush
import com.gowoobro.gymspring.enums.notice.Target
import com.gowoobro.gymspring.enums.notice.Status


@RestController
@RequestMapping("/api/notice")
class NoticeController(
    private val noticeService: NoticeService, private val gymService: GymService, private val userService: UserService) {

    private fun toResponse(notice: Notice):
    NoticeResponse {
        
        val gym = gymService.findById(notice.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        val user = userService.findById(notice.createdby)
        val userResponse = user?.let{ UserResponse.from(it) }
        
        return NoticeResponse.from(notice, gymResponse, userResponse)
    }

    @GetMapping
    fun getNotices(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<NoticeResponse>> {
        val result = noticeService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getNotice(@PathVariable id: Long): ResponseEntity<NoticeResponse> {
        val result = noticeService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getNoticeByGym(@RequestParam gym: Long): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/title")
    fun getNoticeByTitle(@RequestParam title: String): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByTitle(title)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getNoticeByContent(@RequestParam content: String): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByContent(content)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getNoticeByType(@RequestParam type: Type): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByType(type)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/ispopup")
    fun getNoticeByIspopup(@RequestParam ispopup: Ispopup): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByIspopup(ispopup)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/ispush")
    fun getNoticeByIspush(@RequestParam ispush: Ispush): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByIspush(ispush)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/target")
    fun getNoticeByTarget(@RequestParam target: Target): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByTarget(target)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/viewcount")
    fun getNoticeByViewcount(@RequestParam viewcount: Int): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByViewcount(viewcount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getNoticeByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByStartdate(startdate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getNoticeByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByEnddate(enddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getNoticeByStatus(@RequestParam status: Status): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByStatus(status)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/createdby")
    fun getNoticeByCreatedby(@RequestParam createdby: Long): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByCreatedby(createdby)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getNoticeByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByCreateddate(createddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/updateddate")
    fun getNoticeByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getNoticeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val result = noticeService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = noticeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createNotice(@RequestBody request: NoticeCreateRequest): ResponseEntity<NoticeResponse> {
        return try {
            val result = noticeService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createNotices(@RequestBody requests: List<NoticeCreateRequest>): ResponseEntity<List<NoticeResponse>> {
        return try {
            val result = noticeService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateNotice(
        @PathVariable id: Long,
        @RequestBody request: NoticeUpdateRequest
    ): ResponseEntity<NoticeResponse> {
        val updatedRequest = request.copy(id = id)
        val result = noticeService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteNotice(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = noticeService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteNotices(@RequestBody entities: List<Notice>): ResponseEntity<Map<String, Boolean>> {
        val success = noticeService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}