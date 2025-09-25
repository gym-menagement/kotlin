package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
import com.gowoobro.gymspring.service.UserService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    
    @GetMapping
    fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<User>> {
        val result = userService.findAll(page, pageSize)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<User> {
        val result = userService.findById(id)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @GetMapping("/search/loginid")
    fun getUsersByMobileSearchLoginid(@RequestParam loginid: String): ResponseEntity<User?> {
        val result = userService.findByLoginid(loginid)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/search/passwd")
    fun getUsersByMobileSearchPasswd(@RequestParam passwd: String): ResponseEntity<List<User>> {
        val result = userService.findByPasswd(passwd)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/search/email")
    fun getUsersByMobileSearchEmail(@RequestParam email: String): ResponseEntity<List<User>> {
        val result = userService.findByEmail(email)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/search/name")
    fun getUsersByMobileSearchName(@RequestParam name: String): ResponseEntity<List<User>> {
        val result = userService.findByName(name)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/search/tel")
    fun getUsersByMobileSearchTel(@RequestParam tel: String): ResponseEntity<List<User>> {
        val result = userService.findByTel(tel)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/search/address")
    fun getUsersByMobileSearchAddress(@RequestParam address: String): ResponseEntity<List<User>> {
        val result = userService.findByAddress(address)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/search/image")
    fun getUsersByMobileSearchImage(@RequestParam image: String): ResponseEntity<List<User>> {
        val result = userService.findByImage(image)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/search/connectid")
    fun getUsersByMobileSearchConnectid(@RequestParam connectid: String): ResponseEntity<List<User>> {
        val result = userService.findByConnectid(connectid)
        return ResponseEntity.ok(result)
    }
    
    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = userService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }
    
    @PostMapping
    fun createUser(@RequestBody request: UserCreateRequest): ResponseEntity<User> {
        return try {
            val result = userService.create(request)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PostMapping("/batch")
    fun createUsers(@RequestBody requests: List<UserCreateRequest>): ResponseEntity<List<User>> {
        return try {
            val result = userService.createBatch(requests)
            ResponseEntity.ok(result)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }
    
    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserUpdateRequest
    ): ResponseEntity<User> {
        val updatedRequest = request.copy(id = id)
        val result = userService.update(updatedRequest)
        return if (result != null) {
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Map<String, Boolean>> {
        val success = userService.deleteById(id)
        return ResponseEntity.ok(mapOf("success" to success))
    }
    
    @DeleteMapping("/batch")
    fun deleteUsers(@RequestBody entities: List<User>): ResponseEntity<Map<String, Boolean>> {
        val success = userService.deleteBatch(entities)
        return ResponseEntity.ok(mapOf("success" to success))
    }
}