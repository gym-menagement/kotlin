package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Workoutlog
import com.gowoobro.gymspring.entity.WorkoutlogCreateRequest
import com.gowoobro.gymspring.entity.WorkoutlogUpdateRequest
import com.gowoobro.gymspring.service.WorkoutlogService
import com.gowoobro.gymspring.entity.WorkoutlogResponse

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.math.BigDecimal

@RestController
@RequestMapping("/api/workoutlog")
class WorkoutlogController(
    private val workoutlogService: WorkoutlogService) {

    private fun toResponse(workoutlog: Workoutlog): WorkoutlogResponse {
        return WorkoutlogResponse.from(workoutlog)
    }

    @GetMapping
    fun getWorkoutlogs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<WorkoutlogResponse>> {
        val res = workoutlogService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getWorkoutlog(@PathVariable id: Long): ResponseEntity<WorkoutlogResponse> {
        val res = workoutlogService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/gym")
    fun getWorkoutlogByGym(@RequestParam gym: Long): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByGym(gym)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getWorkoutlogByUser(@RequestParam user: Long): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByUser(user)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/attendance")
    fun getWorkoutlogByAttendance(@RequestParam attendance: Long): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByAttendance(attendance)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/health")
    fun getWorkoutlogByHealth(@RequestParam health: Long): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByHealth(health)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/exercisename")
    fun getWorkoutlogByExercisename(@RequestParam exercisename: String): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByExercisename(exercisename)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/sets")
    fun getWorkoutlogBySets(@RequestParam sets: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findBySets(sets)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/reps")
    fun getWorkoutlogByReps(@RequestParam reps: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByReps(reps)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/weight")
    fun getWorkoutlogByWeight(@RequestParam weight: BigDecimal): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByWeight(weight)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/duration")
    fun getWorkoutlogByDuration(@RequestParam duration: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByDuration(duration)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/calories")
    fun getWorkoutlogByCalories(@RequestParam calories: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByCalories(calories)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/note")
    fun getWorkoutlogByNote(@RequestParam note: String): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByNote(note)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getWorkoutlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<WorkoutlogResponse>> {
        val res = workoutlogService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = workoutlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createWorkoutlog(@RequestBody request: WorkoutlogCreateRequest): ResponseEntity<WorkoutlogResponse> {
        return try {
            val res = workoutlogService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createWorkoutlogs(@RequestBody requests: List<WorkoutlogCreateRequest>): ResponseEntity<List<WorkoutlogResponse>> {
        return try {
            val res = workoutlogService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateWorkoutlog(
        @PathVariable id: Long,
        @RequestBody request: WorkoutlogUpdateRequest
    ): ResponseEntity<WorkoutlogResponse> {
        val updatedRequest = request.copy(id = id)
        val res = workoutlogService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteWorkoutlog(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = workoutlogService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteWorkoutlogs(@RequestBody entities: List<Workoutlog>): ResponseEntity<Map<String, Boolean>> {
        val success = workoutlogService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}