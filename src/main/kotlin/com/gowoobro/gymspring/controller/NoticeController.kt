package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
import com.gowoobro.gymspring.entity.NoticePatchRequest
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

    private fun filterByDateRange(
        value: LocalDateTime?,
        startRange: LocalDateTime?,
        endRange: LocalDateTime?
    ): Boolean {
        if (value == null) return false
        return when {
            startRange != null && endRange != null -> value in startRange..endRange
            startRange != null -> value >= startRange
            endRange != null -> value <= endRange
            else -> true
        }
    }

    @GetMapping
    fun getNotices(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) content: String?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) ispopup: Int?,
        @RequestParam(required = false) ispush: Int?,
        @RequestParam(required = false) target: Int?,
        @RequestParam(required = false) viewcount: Int?,
        @RequestParam(required = false) startstartdate: LocalDateTime?,
        @RequestParam(required = false) endstartdate: LocalDateTime?,
        @RequestParam(required = false) startenddate: LocalDateTime?,
        @RequestParam(required = false) endenddate: LocalDateTime?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) createdby: Long?,
        @RequestParam(required = false) startcreateddate: LocalDateTime?,
        @RequestParam(required = false) endcreateddate: LocalDateTime?,
        @RequestParam(required = false) startupdateddate: LocalDateTime?,
        @RequestParam(required = false) endupdateddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (gym != null || title != null || content != null || type != null || ispopup != null || ispush != null || target != null || viewcount != null || startstartdate != null || endstartdate != null || startenddate != null || endenddate != null || status != null || createdby != null || startcreateddate != null || endcreateddate != null || startupdateddate != null || endupdateddate != null || startdate != null || enddate != null || false) {
            var filtered = noticeService.findAll(0, Int.MAX_VALUE).content
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (title != null) {
                filtered = filtered.filter { it.title == title }
            }
            if (content != null) {
                filtered = filtered.filter { it.content == content }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (ispopup != null) {
                filtered = filtered.filter { it.ispopup.ordinal == ispopup }
            }
            if (ispush != null) {
                filtered = filtered.filter { it.ispush.ordinal == ispush }
            }
            if (target != null) {
                filtered = filtered.filter { it.target.ordinal == target }
            }
            if (viewcount != null) {
                filtered = filtered.filter { it.viewcount == viewcount }
            }
            if (startstartdate != null || endstartdate != null) {
                filtered = filtered.filter { filterByDateRange(it.startdate, startstartdate, endstartdate) }
            }
            if (startenddate != null || endenddate != null) {
                filtered = filtered.filter { filterByDateRange(it.enddate, startenddate, endenddate) }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (createdby != null) {
                filtered = filtered.filter { it.createdbyId == createdby }
            }
            if (startcreateddate != null || endcreateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.createddate, startcreateddate, endcreateddate) }
            }
            if (startupdateddate != null || endupdateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.updateddate, startupdateddate, endupdateddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            noticeService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pagesize > 0) (totalElements + pagesize - 1) / pagesize else 1
        val startIndex = page * pagesize
        val endIndex = minOf(startIndex + pagesize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pagesize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
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

    @PatchMapping("/{id}")
    fun patchNotice(
        @PathVariable id: Long,
        @RequestBody request: NoticePatchRequest
    ): ResponseEntity<NoticeResponse> {
        val patchRequest = request.copy(id = id)
        val res = noticeService.patch(patchRequest)
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