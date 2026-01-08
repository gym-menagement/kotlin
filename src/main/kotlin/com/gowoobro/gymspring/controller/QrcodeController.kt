package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Qrcode
import com.gowoobro.gymspring.entity.QrcodeCreateRequest
import com.gowoobro.gymspring.entity.QrcodeUpdateRequest
import com.gowoobro.gymspring.entity.QrcodePatchRequest
import com.gowoobro.gymspring.service.QrcodeService
import com.gowoobro.gymspring.entity.QrcodeResponse
import com.gowoobro.gymspring.enums.qrcode.Isactive

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/qrcode")
class QrcodeController(
    private val qrcodeService: QrcodeService) {

    private fun toResponse(qrcode: Qrcode): QrcodeResponse {
        return QrcodeResponse.from(qrcode)
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
    fun getQrcodes(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pagesize: Int,
        @RequestParam(required = false) user: Long?,
        @RequestParam(required = false) code: String?,
        @RequestParam(required = false) imageurl: String?,
        @RequestParam(required = false) isactive: Int?,
        @RequestParam(required = false) startexpiredate: LocalDateTime?,
        @RequestParam(required = false) endexpiredate: LocalDateTime?,
        @RequestParam(required = false) startgenerateddate: LocalDateTime?,
        @RequestParam(required = false) endgenerateddate: LocalDateTime?,
        @RequestParam(required = false) startlastuseddate: LocalDateTime?,
        @RequestParam(required = false) endlastuseddate: LocalDateTime?,
        @RequestParam(required = false) usecount: Int?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (user != null || code != null || imageurl != null || isactive != null || startexpiredate != null || endexpiredate != null || startgenerateddate != null || endgenerateddate != null || startlastuseddate != null || endlastuseddate != null || usecount != null || startdate != null || enddate != null || false) {
            var filtered = qrcodeService.findAll(0, Int.MAX_VALUE).content
            if (user != null) {
                filtered = filtered.filter { it.userId == user }
            }
            if (code != null) {
                filtered = filtered.filter { it.code == code }
            }
            if (imageurl != null) {
                filtered = filtered.filter { it.imageurl == imageurl }
            }
            if (isactive != null) {
                filtered = filtered.filter { it.isactive.ordinal == isactive }
            }
            if (startexpiredate != null || endexpiredate != null) {
                filtered = filtered.filter { filterByDateRange(it.expiredate, startexpiredate, endexpiredate) }
            }
            if (startgenerateddate != null || endgenerateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.generateddate, startgenerateddate, endgenerateddate) }
            }
            if (startlastuseddate != null || endlastuseddate != null) {
                filtered = filtered.filter { filterByDateRange(it.lastuseddate, startlastuseddate, endlastuseddate) }
            }
            if (usecount != null) {
                filtered = filtered.filter { it.usecount == usecount }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            qrcodeService.findAll(0, Int.MAX_VALUE).content
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
    fun getQrcode(@PathVariable id: Long): ResponseEntity<QrcodeResponse> {
        val res = qrcodeService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getQrcodeByUser(@RequestParam user: Long): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/code")
    fun getQrcodeByCode(@RequestParam code: String): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByCode(code)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/imageurl")
    fun getQrcodeByImageurl(@RequestParam imageurl: String): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByImageurl(imageurl)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/isactive")
    fun getQrcodeByIsactive(@RequestParam isactive: Isactive): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByIsactive(isactive)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/expiredate")
    fun getQrcodeByExpiredate(@RequestParam expiredate: LocalDateTime): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByExpiredate(expiredate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/generateddate")
    fun getQrcodeByGenerateddate(@RequestParam generateddate: LocalDateTime): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByGenerateddate(generateddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/lastuseddate")
    fun getQrcodeByLastuseddate(@RequestParam lastuseddate: LocalDateTime): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByLastuseddate(lastuseddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/usecount")
    fun getQrcodeByUsecount(@RequestParam usecount: Int): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByUsecount(usecount)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getQrcodeByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<QrcodeResponse>> {
        val res = qrcodeService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = qrcodeService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createQrcode(@RequestBody request: QrcodeCreateRequest): ResponseEntity<QrcodeResponse> {
        return try {
            val res = qrcodeService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createQrcodes(@RequestBody requests: List<QrcodeCreateRequest>): ResponseEntity<List<QrcodeResponse>> {
        return try {
            val res = qrcodeService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateQrcode(
        @PathVariable id: Long,
        @RequestBody request: QrcodeUpdateRequest
    ): ResponseEntity<QrcodeResponse> {
        val updatedRequest = request.copy(id = id)
        val res = qrcodeService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchQrcode(
        @PathVariable id: Long,
        @RequestBody request: QrcodePatchRequest
    ): ResponseEntity<QrcodeResponse> {
        val patchRequest = request.copy(id = id)
        val res = qrcodeService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteQrcode(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = qrcodeService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteQrcodes(@RequestBody entities: List<Qrcode>): ResponseEntity<Map<String, Boolean>> {
        val success = qrcodeService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}