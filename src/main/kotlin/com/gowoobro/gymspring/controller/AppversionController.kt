package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Appversion
import com.gowoobro.gymspring.entity.AppversionCreateRequest
import com.gowoobro.gymspring.entity.AppversionUpdateRequest
import com.gowoobro.gymspring.entity.AppversionPatchRequest
import com.gowoobro.gymspring.service.AppversionService
import com.gowoobro.gymspring.entity.AppversionResponse
import com.gowoobro.gymspring.enums.appversion.Forceupdate
import com.gowoobro.gymspring.enums.appversion.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/appversion")
class AppversionController(
    private val appversionService: AppversionService) {

    private fun toResponse(appversion: Appversion): AppversionResponse {
        return AppversionResponse.from(appversion)
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
    fun getAppversions(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) platform: String?,
        @RequestParam(required = false) version: String?,
        @RequestParam(required = false) minversion: String?,
        @RequestParam(required = false) forceupdate: Int?,
        @RequestParam(required = false) updatemessage: String?,
        @RequestParam(required = false) downloadurl: String?,
        @RequestParam(required = false) status: Int?,
        @RequestParam(required = false) startreleasedate: LocalDateTime?,
        @RequestParam(required = false) endreleasedate: LocalDateTime?,
        @RequestParam(required = false) startcreateddate: LocalDateTime?,
        @RequestParam(required = false) endcreateddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (platform != null || version != null || minversion != null || forceupdate != null || updatemessage != null || downloadurl != null || status != null || startreleasedate != null || endreleasedate != null || startcreateddate != null || endcreateddate != null || startdate != null || enddate != null || false) {
            var filtered = appversionService.findAll(0, Int.MAX_VALUE).content
            if (platform != null) {
                filtered = filtered.filter { it.platform == platform }
            }
            if (version != null) {
                filtered = filtered.filter { it.version == version }
            }
            if (minversion != null) {
                filtered = filtered.filter { it.minversion == minversion }
            }
            if (forceupdate != null) {
                filtered = filtered.filter { it.forceupdate.ordinal == forceupdate }
            }
            if (updatemessage != null) {
                filtered = filtered.filter { it.updatemessage == updatemessage }
            }
            if (downloadurl != null) {
                filtered = filtered.filter { it.downloadurl == downloadurl }
            }
            if (status != null) {
                filtered = filtered.filter { it.status.ordinal == status }
            }
            if (startreleasedate != null || endreleasedate != null) {
                filtered = filtered.filter { filterByDateRange(it.releasedate, startreleasedate, endreleasedate) }
            }
            if (startcreateddate != null || endcreateddate != null) {
                filtered = filtered.filter { filterByDateRange(it.createddate, startcreateddate, endcreateddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            appversionService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pageSize > 0) (totalElements + pageSize - 1) / pageSize else 1
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pageSize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getAppversion(@PathVariable id: Long): ResponseEntity<AppversionResponse> {
        val res = appversionService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/platform")
    fun getAppversionByPlatform(@RequestParam platform: String): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByPlatform(platform)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/version")
    fun getAppversionByVersion(@RequestParam version: String): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByVersion(version)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/minversion")
    fun getAppversionByMinversion(@RequestParam minversion: String): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByMinversion(minversion)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/forceupdate")
    fun getAppversionByForceupdate(@RequestParam forceupdate: Forceupdate): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByForceupdate(forceupdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/updatemessage")
    fun getAppversionByUpdatemessage(@RequestParam updatemessage: String): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByUpdatemessage(updatemessage)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/downloadurl")
    fun getAppversionByDownloadurl(@RequestParam downloadurl: String): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByDownloadurl(downloadurl)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getAppversionByStatus(@RequestParam status: Status): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/releasedate")
    fun getAppversionByReleasedate(@RequestParam releasedate: LocalDateTime): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByReleasedate(releasedate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/createddate")
    fun getAppversionByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByCreateddate(createddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getAppversionByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<AppversionResponse>> {
        val res = appversionService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = appversionService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createAppversion(@RequestBody request: AppversionCreateRequest): ResponseEntity<AppversionResponse> {
        return try {
            val res = appversionService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createAppversions(@RequestBody requests: List<AppversionCreateRequest>): ResponseEntity<List<AppversionResponse>> {
        return try {
            val res = appversionService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateAppversion(
        @PathVariable id: Long,
        @RequestBody request: AppversionUpdateRequest
    ): ResponseEntity<AppversionResponse> {
        val updatedRequest = request.copy(id = id)
        val res = appversionService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun patchAppversion(
        @PathVariable id: Long,
        @RequestBody request: AppversionPatchRequest
    ): ResponseEntity<AppversionResponse> {
        val patchRequest = request.copy(id = id)
        val res = appversionService.patch(patchRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAppversion(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = appversionService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteAppversions(@RequestBody entities: List<Appversion>): ResponseEntity<Map<String, Boolean>> {
        val success = appversionService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}