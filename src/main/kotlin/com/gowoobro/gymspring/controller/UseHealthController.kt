package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import com.gowoobro.gymspring.service.UsehealthService
import com.gowoobro.gymspring.entity.UsehealthResponse
import com.gowoobro.gymspring.entity.OrderResponse
import com.gowoobro.gymspring.service.OrderService
import com.gowoobro.gymspring.entity.HealthResponse
import com.gowoobro.gymspring.service.HealthService
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.service.UserService
import com.gowoobro.gymspring.entity.RockerResponse
import com.gowoobro.gymspring.service.RockerService
import com.gowoobro.gymspring.entity.TermResponse
import com.gowoobro.gymspring.service.TermService
import com.gowoobro.gymspring.entity.DiscountResponse
import com.gowoobro.gymspring.service.DiscountService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@RestController
@RequestMapping("/api/usehealth")
class UsehealthController(
    private val usehealthService: UsehealthService, private val orderService: OrderService, private val healthService: HealthService, private val userService: UserService, private val rockerService: RockerService, private val termService: TermService, private val discountService: DiscountService) {

    private fun toResponse(usehealth: Usehealth):
    UsehealthResponse {
        
        val order = orderService.findById(usehealth.order)
        val orderResponse = order?.let{ OrderResponse.from(it) }
        
        val health = healthService.findById(usehealth.health)
        val healthResponse = health?.let{ HealthResponse.from(it) }
        
        val user = userService.findById(usehealth.user)
        val userResponse = user?.let{ UserResponse.from(it) }
        
        val rocker = rockerService.findById(usehealth.rocker)
        val rockerResponse = rocker?.let{ RockerResponse.from(it) }
        
        val term = termService.findById(usehealth.term)
        val termResponse = term?.let{ TermResponse.from(it) }
        
        val discount = discountService.findById(usehealth.discount)
        val discountResponse = discount?.let{ DiscountResponse.from(it) }
        
        return UsehealthResponse.from(usehealth, orderResponse, healthResponse, userResponse, rockerResponse, termResponse, discountResponse)
    }

    @GetMapping
    fun getUsehealths(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<UsehealthResponse>> {
        val result = usehealthService.findAll(page, pageSize)
        val responsePage = result.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getUsehealth(@PathVariable id: Long): ResponseEntity<UsehealthResponse> {
        val result = usehealthService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/order")
    fun getUsehealthByOrder(@RequestParam order: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByOrder(order)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/health")
    fun getUsehealthByHealth(@RequestParam health: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByHealth(health)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/user")
    fun getUsehealthByUser(@RequestParam user: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByUser(user)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/rocker")
    fun getUsehealthByRocker(@RequestParam rocker: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByRocker(rocker)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/term")
    fun getUsehealthByTerm(@RequestParam term: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByTerm(term)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/discount")
    fun getUsehealthByDiscount(@RequestParam discount: Long): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByDiscount(discount)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/startday")
    fun getUsehealthByStartday(@RequestParam startday: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByStartday(startday)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/endday")
    fun getUsehealthByEndday(@RequestParam endday: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByEndday(endday)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getUsehealthByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<UsehealthResponse>> {
        val result = usehealthService.findByDate(date)
        return ResponseEntity.ok(result.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = usehealthService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createUsehealth(@RequestBody request: UsehealthCreateRequest): ResponseEntity<UsehealthResponse> {
        return try {
            val result = usehealthService.create(request)
            ResponseEntity.ok(toResponse(result))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createUsehealths(@RequestBody requests: List<UsehealthCreateRequest>): ResponseEntity<List<UsehealthResponse>> {
        return try {
            val result = usehealthService.createBatch(requests)
            return ResponseEntity.ok(result.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateUsehealth(
        @PathVariable id: Long,
        @RequestBody request: UsehealthUpdateRequest
    ): ResponseEntity<UsehealthResponse> {
        val updatedRequest = request.copy(id = id)
        val result = usehealthService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(toResponse(result))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUsehealth(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }

    @DeleteMapping("/batch")
    fun deleteUsehealths(@RequestBody entities: List<Usehealth>): ResponseEntity<Map<String, Boolean>> {
        val success = usehealthService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}