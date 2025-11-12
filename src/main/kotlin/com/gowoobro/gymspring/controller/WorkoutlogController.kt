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
class WorkoutlogController(private val workoutlogService: WorkoutlogService) {

    @GetMapping
    fun getWorkoutlogs(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<WorkoutlogResponse>> {
        val result = workoutlogService.findAll(page, pageSize)
        val responsePage = result.map { WorkoutlogResponse.from(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getWorkoutlog(@PathVariable id: Long): ResponseEntity<WorkoutlogResponse> {
        val result = workoutlogService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(WorkoutlogResponse.from(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getWorkoutlogByUser(@RequestParam user: Long): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByUser(user)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/attendance")
    fun getWorkoutlogByAttendance(@RequestParam attendance: Long): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByAttendance(attendance)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/health")
    fun getWorkoutlogByHealth(@RequestParam health: Long): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByHealth(health)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/exercisename")
    fun getWorkoutlogByExercisename(@RequestParam exercisename: String): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByExercisename(exercisename)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/sets")
    fun getWorkoutlogBySets(@RequestParam sets: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findBySets(sets)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/reps")
    fun getWorkoutlogByReps(@RequestParam reps: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByReps(reps)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/weight")
    fun getWorkoutlogByWeight(@RequestParam weight: BigDecimal): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByWeight(weight)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/duration")
    fun getWorkoutlogByDuration(@RequestParam duration: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByDuration(duration)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/calories")
    fun getWorkoutlogByCalories(@RequestParam calories: Int): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByCalories(calories)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/note")
    fun getWorkoutlogByNote(@RequestParam note: String): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByNote(note)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }

    @GetMapping("/search/date")
    fun getWorkoutlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<WorkoutlogResponse>> {
        val result = workoutlogService.findByDate(date)
        return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = workoutlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createWorkoutlog(@RequestBody request: WorkoutlogCreateRequest): ResponseEntity<WorkoutlogResponse> {
        return try {
            val result = workoutlogService.create(request)
            ResponseEntity.ok(WorkoutlogResponse.from(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createWorkoutlogs(@RequestBody requests: List<WorkoutlogCreateRequest>): ResponseEntity<List<WorkoutlogResponse>> {
        return try {
            val result = workoutlogService.createBatch(requests)
            return ResponseEntity.ok(result.map { WorkoutlogResponse.from(it) } )
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
        val result = workoutlogService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(WorkoutlogResponse.from(result))
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