package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Workoutlog
import com.gowoobro.gymspring.entity.WorkoutlogCreateRequest
import com.gowoobro.gymspring.entity.WorkoutlogUpdateRequest
import com.gowoobro.gymspring.service.WorkoutlogService
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
    ): ResponseEntity<Page<Workoutlog>> {
        val result = workoutlogService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getWorkoutlog(@PathVariable id: Long): ResponseEntity<Workoutlog> {
        val result = workoutlogService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/user")
    fun getWorkoutlogByUser(@RequestParam user: Long): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByUser(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/attendance")
    fun getWorkoutlogByAttendance(@RequestParam attendance: Long): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByAttendance(attendance)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/health")
    fun getWorkoutlogByHealth(@RequestParam health: Long): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByHealth(health)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/exercisename")
    fun getWorkoutlogByExercisename(@RequestParam exercisename: String): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByExercisename(exercisename)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/sets")
    fun getWorkoutlogBySets(@RequestParam sets: Int): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findBySets(sets)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/reps")
    fun getWorkoutlogByReps(@RequestParam reps: Int): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByReps(reps)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/weight")
    fun getWorkoutlogByWeight(@RequestParam weight: BigDecimal): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByWeight(weight)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/duration")
    fun getWorkoutlogByDuration(@RequestParam duration: Int): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByDuration(duration)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/calories")
    fun getWorkoutlogByCalories(@RequestParam calories: Int): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByCalories(calories)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/note")
    fun getWorkoutlogByNote(@RequestParam note: String): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByNote(note)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getWorkoutlogByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<Workoutlog>> {
        val result = workoutlogService.findByDate(date)
        return ResponseEntity.ok(result)
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = workoutlogService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createWorkoutlog(@RequestBody request: WorkoutlogCreateRequest): ResponseEntity<Workoutlog> {
        return try {
            val result = workoutlogService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createWorkoutlogs(@RequestBody requests: List<WorkoutlogCreateRequest>): ResponseEntity<List<Workoutlog>> {
        return try {
            val result = workoutlogService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateWorkoutlog(
        @PathVariable id: Long,
        @RequestBody request: WorkoutlogUpdateRequest
    ): ResponseEntity<Workoutlog> {
        val updatedRequest = request.copy(id = id)
        val result = workoutlogService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
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