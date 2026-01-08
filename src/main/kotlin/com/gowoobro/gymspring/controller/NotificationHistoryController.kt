package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Notificationhistory
import com.gowoobro.gymspring.entity.NotificationhistoryCreateRequest
import com.gowoobro.gymspring.entity.NotificationhistoryUpdateRequest
import com.gowoobro.gymspring.entity.NotificationhistoryPatchRequest
import com.gowoobro.gymspring.service.NotificationhistoryService
import com.gowoobro.gymspring.entity.NotificationhistoryResponse
import com.gowoobro.gymspring.enums.notificationhistory.Type
import com.gowoobro.gymspring.enums.notificationhistory.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/notificationhistory")
class NotificationhistoryController(
    private val notificationhistoryService: NotificationhistoryService) {

    private fun toResponse(notificationhistory: Notificationhistory): NotificationhistoryResponse {
        return NotificationhistoryResponse.from(notificationhistory)
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
    fun getNotificationhistorys(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) sender: Long?,
        @RequestParam(required = false) receiver: Long?,
        @RequestParam(required = false) gym: Long?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) body: String?,
        @RequestParam(required = false) data: String?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) errormessage: String?,
        @RequestParam(required = false) startsentdate: LocalDateTime?,
        @RequestParam(required = false) endsentdate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (sender != null || receiver != null || gym != null || type != null || title != null || body != null || data != null || status != null || errormessage != null || startsentdate != null || endsentdate != null || startdate != null || enddate != null || false) {
            var filtered = notificationhistoryService.findAll(0, Int.MAX_VALUE).content
            if (sender != null) {
                filtered = filtered.filter { it.senderId == sender }
            }
            if (receiver != null) {
                filtered = filtered.filter { it.receiverId == receiver }
            }
            if (gym != null) {
                filtered = filtered.filter { it.gymId == gym }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (title != null) {
                filtered = filtered.filter { it.title == title }
            }
            if (body != null) {
                filtered = filtered.filter { it.body == body }
            }
            if (data != null) {
                filtered = filtered.filter { it.data == data }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (errormessage != null) {
                filtered = filtered.filter { it.errormessage == errormessage }
            }
            if (startsentdate != null || endsentdate != null) {
                filtered = filtered.filter { filterByDateRange(it.sentdate, startsentdate, endsentdate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            notificationhistoryService.findAll(0, Int.MAX_VALUE).content
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
    fun getNotificationhistory(@PathVariable id: Long): ResponseEntity<NotificationhistoryResponse> {
        val res = notificationhistoryService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/sender")
    fun getNotificationhistoryBySender(@RequestParam sender: Long): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findBySender(sender)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/receiver")
    fun getNotificationhistoryByReceiver(@RequestParam receiver: Long): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByReceiver(receiver)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getNotificationhistoryByGym(@RequestParam gym: Long): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getNotificationhistoryByType(@RequestParam type: Type): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/title")
    fun getNotificationhistoryByTitle(@RequestParam title: String): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByTitle(title)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/body")
    fun getNotificationhistoryByBody(@RequestParam body: String): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByBody(body)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/data")
    fun getNotificationhistoryByData(@RequestParam data: String): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByData(data)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getNotificationhistoryByStatus(@RequestParam status: Status): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/errormessage")
    fun getNotificationhistoryByErrormessage(@RequestParam errormessage: String): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByErrormessage(errormessage)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/sentdate")
    fun getNotificationhistoryBySentdate(@RequestParam sentdate: LocalDateTime): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findBySentdate(sentdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getNotificationhistoryByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<NotificationhistoryResponse>> {
        val res = notificationhistoryService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = notificationhistoryService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createNotificationhistory(@RequestBody request: NotificationhistoryCreateRequest): ResponseEntity<NotificationhistoryResponse> {
        return try {
            val res = notificationhistoryService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createNotificationhistorys(@RequestBody requests: List<NotificationhistoryCreateRequest>): ResponseEntity<List<NotificationhistoryResponse>> {
        return try {
            val res = notificationhistoryService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateNotificationhistory(
        @PathVariable id: Long,
        @RequestBody request: NotificationhistoryUpdateRequest
    ): ResponseEntity<NotificationhistoryResponse> {
        val updatedRequest = request.copy(id = id)
        val res = notificationhistoryService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchNotificationhistory(
        @PathVariable id: Long,
        @RequestBody request: NotificationhistoryPatchRequest
    ): ResponseEntity<NotificationhistoryResponse> {
        val patchRequest = request.copy(id = id)
        val res = notificationhistoryService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteNotificationhistory(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = notificationhistoryService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteNotificationhistorys(@RequestBody entities: List<Notificationhistory>): ResponseEntity<Map<String, Boolean>> {
        val success = notificationhistoryService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}