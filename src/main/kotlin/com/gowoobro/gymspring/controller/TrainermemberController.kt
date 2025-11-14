package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import com.gowoobro.gymspring.service.TrainermemberService
import com.gowoobro.gymspring.entity.TrainermemberResponse
import com.gowoobro.gymspring.enums.trainermember.Status

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/trainermember")
class TrainermemberController(
    private val trainermemberService: TrainermemberService) {

    private fun toResponse(trainermember: Trainermember): TrainermemberResponse {
        return TrainermemberResponse.from(trainermember)
    }

    @GetMapping
    fun getTrainermembers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<TrainermemberResponse>> {
        val res = trainermemberService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getTrainermember(@PathVariable id: Long): ResponseEntity<TrainermemberResponse> {
        val res = trainermemberService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/trainer")
    fun getTrainermemberByTrainer(@RequestParam trainer: Long): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByTrainer(trainer)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/member")
    fun getTrainermemberByMember(@RequestParam member: Long): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByMember(member)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getTrainermemberByGym(@RequestParam gym: Long): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getTrainermemberByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByStartdate(startdate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getTrainermemberByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByEnddate(enddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getTrainermemberByStatus(@RequestParam status: Status): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByStatus(status)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getTrainermemberByNote(@RequestParam note: String): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getTrainermemberByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val res = trainermemberService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = trainermemberService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createTrainermember(@RequestBody request: TrainermemberCreateRequest): ResponseEntity<TrainermemberResponse> {
        return try {
            val res = trainermemberService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTrainermembers(@RequestBody requests: List<TrainermemberCreateRequest>): ResponseEntity<List<TrainermemberResponse>> {
        return try {
            val res = trainermemberService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateTrainermember(
        @PathVariable id: Long,
        @RequestBody request: TrainermemberUpdateRequest
    ): ResponseEntity<TrainermemberResponse> {
        val updatedRequest = request.copy(id = id)
        val res = trainermemberService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTrainermember(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = trainermemberService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteTrainermembers(@RequestBody entities: List<Trainermember>): ResponseEntity<Map<String, Boolean>> {
        val success = trainermemberService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}