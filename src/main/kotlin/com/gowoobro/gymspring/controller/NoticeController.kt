package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
import com.gowoobro.gymspring.service.NoticeService
import com.gowoobro.gymspring.entity.NoticeResponse
import com.gowoobro.gymspring.enums.notice.Type
import com.gowoobro.gymspring.enums.notice.Ispopup
import com.gowoobro.gymspring.enums.notice.Ispush
import com.gowoobro.gymspring.enums.notice.Target
import com.gowoobro.gymspring.enums.notice.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/notice")
class NoticeController(
    private val noticeService: NoticeService) {

    private fun toResponse(notice: Notice): NoticeResponse {
        return NoticeResponse.from(notice)
    }

    @GetMapping
    fun getNotices(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<NoticeResponse>> {
        val res = noticeService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getNotice(@PathVariable id: Long): ResponseEntity<NoticeResponse> {
        val res = noticeService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getNoticeByGym(@RequestParam gym: Long): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/title")
    fun getNoticeByTitle(@RequestParam title: String): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByTitle(title)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/content")
    fun getNoticeByContent(@RequestParam content: String): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByContent(content)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getNoticeByType(@RequestParam type: Type): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/ispopup")
    fun getNoticeByIspopup(@RequestParam ispopup: Ispopup): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByIspopup(ispopup)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/ispush")
    fun getNoticeByIspush(@RequestParam ispush: Ispush): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByIspush(ispush)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/target")
    fun getNoticeByTarget(@RequestParam target: Target): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByTarget(target)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/viewcount")
    fun getNoticeByViewcount(@RequestParam viewcount: Int): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByViewcount(viewcount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getNoticeByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByStartdate(startdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getNoticeByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByEnddate(enddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getNoticeByStatus(@RequestParam status: Status): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createdby")
    fun getNoticeByCreatedby(@RequestParam createdby: Long): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByCreatedby(createdby)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getNoticeByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByCreateddate(createddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/updateddate")
    fun getNoticeByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getNoticeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<NoticeResponse>> {
        val res = noticeService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = noticeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createNotice(@RequestBody request: NoticeCreateRequest): ResponseEntity<NoticeResponse> {
        return try {
            val res = noticeService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createNotices(@RequestBody requests: List<NoticeCreateRequest>): ResponseEntity<List<NoticeResponse>> {
        return try {
            val res = noticeService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
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
        val res = noticeService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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