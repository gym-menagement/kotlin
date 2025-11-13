package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import com.gowoobro.gymspring.service.TrainermemberService
import com.gowoobro.gymspring.entity.TrainermemberResponse
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import com.gowoobro.gymspring.entity.GymResponse
import com.gowoobro.gymspring.service.GymService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.trainermember.Status


@RestController
@RequestMapping("/api/trainermember")
class TrainermemberController(
    private val trainermemberService: TrainermemberService, private val userService: UserService, private val gymService: GymService) {

    private fun toResponse(trainermember: Trainermember):
    TrainermemberResponse {
        
        val traineruser = userService.findById(trainermember.trainer)
        val traineruserResponse = traineruser?.let{ UserResponse.from(it) }
        
        val memberuser = userService.findById(trainermember.member)
        val memberuserResponse = memberuser?.let{ UserResponse.from(it) }
        
        val gym = gymService.findById(trainermember.gym)
        val gymResponse = gym?.let{ GymResponse.from(it) }
        
        return TrainermemberResponse.from(trainermember, traineruserResponse, memberuserResponse, gymResponse)
    }

    @GetMapping
    fun getTrainermembers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<TrainermemberResponse>> {
        val result = trainermemberService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getTrainermember(@PathVariable id: Long): ResponseEntity<TrainermemberResponse> {
        val result = trainermemberService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/trainer")
    fun getTrainermemberByTrainer(@RequestParam trainer: Long): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByTrainer(trainer)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/member")
    fun getTrainermemberByMember(@RequestParam member: Long): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByMember(member)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/gym")
    fun getTrainermemberByGym(@RequestParam gym: Long): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByGym(gym)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/startdate")
    fun getTrainermemberByStartdate(@RequestParam startdate: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByStartdate(startdate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/enddate")
    fun getTrainermemberByEnddate(@RequestParam enddate: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByEnddate(enddate)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/status")
    fun getTrainermemberByStatus(@RequestParam status: Status): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByStatus(status)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getTrainermemberByNote(@RequestParam note: String): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByNote(note)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getTrainermemberByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<TrainermemberResponse>> {
        val result = trainermemberService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = trainermemberService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createTrainermember(@RequestBody request: TrainermemberCreateRequest): ResponseEntity<TrainermemberResponse> {
        return try {
            val result = trainermemberService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createTrainermembers(@RequestBody requests: List<TrainermemberCreateRequest>): ResponseEntity<List<TrainermemberResponse>> {
        return try {
            val result = trainermemberService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
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
        val result = trainermemberService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
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