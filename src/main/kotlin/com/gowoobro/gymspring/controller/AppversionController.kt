package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Appversion
import com.gowoobro.gymspring.entity.AppversionCreateRequest
import com.gowoobro.gymspring.entity.AppversionUpdateRequest
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

    @GetMapping
    fun getAppversions(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<AppversionResponse>> {
        val res = appversionService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
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