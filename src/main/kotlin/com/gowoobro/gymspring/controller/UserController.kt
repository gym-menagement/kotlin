package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
import com.gowoobro.gymspring.service.UserService
import com.gowoobro.gymspring.entity.UserResponse
import com.gowoobro.gymspring.enums.user.Level
import com.gowoobro.gymspring.enums.user.Use
import com.gowoobro.gymspring.enums.user.Type
import com.gowoobro.gymspring.enums.user.Role
import com.gowoobro.gymspring.enums.user.Sex

import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService) {

    private fun toResponse(user: User): UserResponse {
        return UserResponse.from(user)
    }

    @GetMapping
    fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ): ResponseEntity<Page<UserResponse>> {
        val res = userService.findAll(page, pageSize)
        val responsePage = res.map { toResponse(it)}
        return ResponseEntity.ok(responsePage)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val res = userService.findById(id)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @GetMapping("/search/loginid")
    fun getUserByLoginid(@RequestParam loginid: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByLoginid(loginid)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/passwd")
    fun getUserByPasswd(@RequestParam passwd: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByPasswd(passwd)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/email")
    fun getUserByEmail(@RequestParam email: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByEmail(email)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/name")
    fun getUserByName(@RequestParam name: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByName(name)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/tel")
    fun getUserByTel(@RequestParam tel: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByTel(tel)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/address")
    fun getUserByAddress(@RequestParam address: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByAddress(address)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/image")
    fun getUserByImage(@RequestParam image: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByImage(image)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/sex")
    fun getUserBySex(@RequestParam sex: Sex): ResponseEntity<List<UserResponse>> {
        val res = userService.findBySex(sex)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/birth")
    fun getUserByBirth(@RequestParam birth: LocalDateTime): ResponseEntity<List<UserResponse>> {
        val res = userService.findByBirth(birth)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/type")
    fun getUserByType(@RequestParam type: Type): ResponseEntity<List<UserResponse>> {
        val res = userService.findByType(type)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/connectid")
    fun getUserByConnectid(@RequestParam connectid: String): ResponseEntity<List<UserResponse>> {
        val res = userService.findByConnectid(connectid)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/level")
    fun getUserByLevel(@RequestParam level: Level): ResponseEntity<List<UserResponse>> {
        val res = userService.findByLevel(level)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/role")
    fun getUserByRole(@RequestParam role: Role): ResponseEntity<List<UserResponse>> {
        val res = userService.findByRole(role)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/use")
    fun getUserByUse(@RequestParam use: Use): ResponseEntity<List<UserResponse>> {
        val res = userService.findByUse(use)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/logindate")
    fun getUserByLogindate(@RequestParam logindate: LocalDateTime): ResponseEntity<List<UserResponse>> {
        val res = userService.findByLogindate(logindate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/lastchangepasswddate")
    fun getUserByLastchangepasswddate(@RequestParam lastchangepasswddate: LocalDateTime): ResponseEntity<List<UserResponse>> {
        val res = userService.findByLastchangepasswddate(lastchangepasswddate)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }

    @GetMapping("/search/date")
    fun getUserByDate(@RequestParam date: LocalDateTime): ResponseEntity<List<UserResponse>> {
        val res = userService.findByDate(date)
        return ResponseEntity.ok(res.map { toResponse(it) } )
    }


    @GetMapping("/count")
    fun getCount(): ResponseEntity<Map<String, Long>> {
        val count = userService.count()
        return ResponseEntity.ok(mapOf("count" to count))
    }

    @PostMapping
    fun createUser(@RequestBody request: UserCreateRequest): ResponseEntity<UserResponse> {
        return try {
            val res = userService.create(request)
            ResponseEntity.ok(toResponse(res))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/batch")
    fun createUsers(@RequestBody requests: List<UserCreateRequest>): ResponseEntity<List<UserResponse>> {
        return try {
            val res = userService.createBatch(requests)
            return ResponseEntity.ok(res.map { toResponse(it) } )
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserUpdateRequest
    ): ResponseEntity<UserResponse> {
        val updatedRequest = request.copy(id = id)
        val res = userService.update(updatedRequest)
        return if (res != null) {
            ResponseEntity.ok(toResponse(res))
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