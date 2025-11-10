package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import com.gowoobro.gymspring.service.TrainermemberService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.trainermember.Status


@RestController
@RequestMapping("/api/trainermember")
class TrainermemberController(private val trainermemberService: TrainermemberService) {

    @GetMapping
    fun getTrainermembers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<Trainermember>> {
        val result = trainermemberService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getTrainermember(@PathVariable id: Long): ResponseEntity<Trainermember> {
        val result = trainermemberService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/trainer")
    fun getTrainermemberByTrainer(@RequestParam trainer: Long): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByTrainer(trainer)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/member")
    fun getTrainermemberByMember(@RequestParam member: Long): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByMember(member)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/gym")
    fun getTrainermemberByGym(@RequestParam gym: Long): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByGym(gym)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/startdate")
    fun getTrainermemberByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByStartdate(startdate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/enddate")
    fun getTrainermemberByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByEnddate(enddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/status")
    fun getTrainermemberByStatus(@RequestParam status: Status): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByStatus(status)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/note")
    fun getTrainermemberByNote(@RequestParam note: String): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByNote(note)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getTrainermemberByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Trainermember>> {
        val result = trainermemberService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = trainermemberService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createTrainermember(@RequestBody request: TrainermemberCreateRequest): ResponseEntity<Trainermember> {
        return try {
            val result = trainermemberService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTrainermembers(@RequestBody requests: List<TrainermemberCreateRequest>): ResponseEntity<List<Trainermember>> {
        return try {
            val result = trainermemberService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateTrainermember(
        @PathVariable id: Long,
        @RequestBody request: TrainermemberUpdateRequest
    ): ResponseEntity<Trainermember> {
        val updatedRequest = request.copy(id = id)
        val result = trainermemberService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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