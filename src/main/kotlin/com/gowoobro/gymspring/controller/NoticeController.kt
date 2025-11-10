package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
import com.gowoobro.gymspring.service.NoticeService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/notice")
class NoticeController(private val noticeService: NoticeService) {

    @GetMapping
    fun getNotices(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Notice>> {
        val result = noticeService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getNotice(@PathVariable id: Long): ResponseEntity<Notice> {
        val result = noticeService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getNoticeByGym(@RequestParam gym: Long): ResponseEntity<List<Notice>> {
        val result = noticeService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/title")
    fun getNoticeByTitle(@RequestParam title: String): ResponseEntity<List<Notice>> {
        val result = noticeService.findByTitle(title)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/content")
    fun getNoticeByContent(@RequestParam content: String): ResponseEntity<List<Notice>> {
        val result = noticeService.findByContent(content)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getNoticeByType(@RequestParam type: Int): ResponseEntity<List<Notice>> {
        val result = noticeService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/ispopup")
    fun getNoticeByIspopup(@RequestParam ispopup: Int): ResponseEntity<List<Notice>> {
        val result = noticeService.findByIspopup(ispopup)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/ispush")
    fun getNoticeByIspush(@RequestParam ispush: Int): ResponseEntity<List<Notice>> {
        val result = noticeService.findByIspush(ispush)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/target")
    fun getNoticeByTarget(@RequestParam target: Int): ResponseEntity<List<Notice>> {
        val result = noticeService.findByTarget(target)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/viewcount")
    fun getNoticeByViewcount(@RequestParam viewcount: Int): ResponseEntity<List<Notice>> {
        val result = noticeService.findByViewcount(viewcount)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/startdate")
    fun getNoticeByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<Notice>> {
        val result = noticeService.findByStartdate(startdate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/enddate")
    fun getNoticeByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<Notice>> {
        val result = noticeService.findByEnddate(enddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getNoticeByStatus(@RequestParam status: Int): ResponseEntity<List<Notice>> {
        val result = noticeService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/createdby")
    fun getNoticeByCreatedby(@RequestParam createdby: Long): ResponseEntity<List<Notice>> {
        val result = noticeService.findByCreatedby(createdby)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/createddate")
    fun getNoticeByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<Notice>> {
        val result = noticeService.findByCreateddate(createddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/updateddate")
    fun getNoticeByUpdateddate(@RequestParam updateddate: LocalDateTime): ResponseEntity<List<Notice>> {
        val result = noticeService.findByUpdateddate(updateddate)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = noticeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createNotice(@RequestBody request: NoticeCreateRequest): ResponseEntity<Notice> {
        return try {
            val result = noticeService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createNotices(@RequestBody requests: List<NoticeCreateRequest>): ResponseEntity<List<Notice>> {
        return try {
            val result = noticeService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateNotice(
        @PathVariable id: Long,
        @RequestBody request: NoticeUpdateRequest
    ): ResponseEntity<Notice> {
        val updatedRequest = request.copy(id = id)
        val result = noticeService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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