package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Appversion
import com.gowoobro.gymspring.entity.AppversionCreateRequest
import com.gowoobro.gymspring.entity.AppversionUpdateRequest
import com.gowoobro.gymspring.service.AppversionService
import com.gowoobro.gymspring.entity.AppversionResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.appversion.Forceupdate
import com.gowoobro.gymspring.enums.appversion.Status


@RestController
@RequestMapping("/api/appversion")
class AppversionController(private val appversionService: AppversionService) {

    @GetMapping
    fun getAppversions(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<AppversionResponse>> {
        val result = appversionService.findAll(page, pageSize)
        val responsePage = result.map { AppversionResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getAppversion(@PathVariable id: Long): ResponseEntity<AppversionResponse> {
        val result = appversionService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(AppversionResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/platform")
    fun getAppversionByPlatform(@RequestParam platform: String): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByPlatform(platform)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/version")
    fun getAppversionByVersion(@RequestParam version: String): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByVersion(version)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/minversion")
    fun getAppversionByMinversion(@RequestParam minversion: String): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByMinversion(minversion)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/forceupdate")
    fun getAppversionByForceupdate(@RequestParam forceupdate: Forceupdate): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByForceupdate(forceupdate)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/updatemessage")
    fun getAppversionByUpdatemessage(@RequestParam updatemessage: String): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByUpdatemessage(updatemessage)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/downloadurl")
    fun getAppversionByDownloadurl(@RequestParam downloadurl: String): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByDownloadurl(downloadurl)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/status")
    fun getAppversionByStatus(@RequestParam status: Status): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByStatus(status)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/releasedate")
    fun getAppversionByReleasedate(@RequestParam releasedate: LocalDateTime): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByReleasedate(releasedate)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/createddate")
    fun getAppversionByCreateddate(@RequestParam createddate: LocalDateTime): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByCreateddate(createddate)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getAppversionByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<AppversionResponse>> {
        val result = appversionService.findByDate(date)
        return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = appversionService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createAppversion(@RequestBody request: AppversionCreateRequest): ResponseEntity<AppversionResponse> {
        return try {
            val result = appversionService.create(request)
            ResponseEntity.ok(AppversionResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createAppversions(@RequestBody requests: List<AppversionCreateRequest>): ResponseEntity<List<AppversionResponse>> {
        return try {
            val result = appversionService.createBatch(requests)
            return ResponseEntity.ok(result.map { AppversionResponse.from(it) } )
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
        val result = appversionService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(AppversionResponse.from(result))
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