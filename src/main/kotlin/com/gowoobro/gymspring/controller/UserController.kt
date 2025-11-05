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


    @GetMapping("/search/id")
    fun getUserById(@RequestParam id: String): ResponseEntity<List<User>> {
        val result = userService.findById(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/loginid")
    fun getUserByLoginid(@RequestParam loginid: String): ResponseEntity<List<User>> {
        val result = userService.findByLoginid(loginid)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/passwd")
    fun getUserByPasswd(@RequestParam passwd: String): ResponseEntity<List<User>> {
        val result = userService.findByPasswd(passwd)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/email")
    fun getUserByEmail(@RequestParam email: String): ResponseEntity<List<User>> {
        val result = userService.findByEmail(email)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/name")
    fun getUserByName(@RequestParam name: String): ResponseEntity<List<User>> {
        val result = userService.findByName(name)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/tel")
    fun getUserByTel(@RequestParam tel: String): ResponseEntity<List<User>> {
        val result = userService.findByTel(tel)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/address")
    fun getUserByAddress(@RequestParam address: String): ResponseEntity<List<User>> {
        val result = userService.findByAddress(address)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/image")
    fun getUserByImage(@RequestParam image: String): ResponseEntity<List<User>> {
        val result = userService.findByImage(image)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/sex")
    fun getUserBySex(@RequestParam sex: String): ResponseEntity<List<User>> {
        val result = userService.findBySex(sex)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/birth")
    fun getUserByBirth(@RequestParam birth: String): ResponseEntity<List<User>> {
        val result = userService.findByBirth(birth)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/type")
    fun getUserByType(@RequestParam type: String): ResponseEntity<List<User>> {
        val result = userService.findByType(type)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/connectid")
    fun getUserByConnectid(@RequestParam connectid: String): ResponseEntity<List<User>> {
        val result = userService.findByConnectid(connectid)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/level")
    fun getUserByLevel(@RequestParam level: String): ResponseEntity<List<User>> {
        val result = userService.findByLevel(level)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/role")
    fun getUserByRole(@RequestParam role: String): ResponseEntity<List<User>> {
        val result = userService.findByRole(role)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/use")
    fun getUserByUse(@RequestParam use: String): ResponseEntity<List<User>> {
        val result = userService.findByUse(use)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/logindate")
    fun getUserByLogindate(@RequestParam logindate: String): ResponseEntity<List<User>> {
        val result = userService.findByLogindate(logindate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/lastchangepasswddate")
    fun getUserByLastchangepasswddate(@RequestParam lastchangepasswddate: String): ResponseEntity<List<User>> {
        val result = userService.findByLastchangepasswddate(lastchangepasswddate)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/search/date")
    fun getUserByDate(@RequestParam date: String): ResponseEntity<List<User>> {
        val result = userService.findByDate(date)
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