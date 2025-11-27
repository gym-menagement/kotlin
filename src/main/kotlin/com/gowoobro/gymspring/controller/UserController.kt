package com.gowoobro.gymspring.controller

import com.gowoobro.gymspring.entity.User
import com.gowoobro.gymspring.entity.UserCreateRequest
import com.gowoobro.gymspring.entity.UserUpdateRequest
import com.gowoobro.gymspring.entity.UserPatchRequest
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

    private fun filterByDateRange(
        value: LocalDateTime?,
        startRange: LocalDateTime?,
        endRange: LocalDateTime?
    ): Boolean {
        if (value == null) return false
        return when {
            startRange != null && endRange != null -> value in startRange..endRange
            startRange != null -> value >= startRange
            endRange != null -> value <= endRange
            else -> true
        }
    }

    @GetMapping
    fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(required = false) loginid: String?,
        @RequestParam(required = false) passwd: String?,
        @RequestParam(required = false) email: String?,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) tel: String?,
        @RequestParam(required = false) address: String?,
        @RequestParam(required = false) image: String?,
        @RequestParam(required = false) sex: Int?,
        @RequestParam(required = false) startbirth: LocalDateTime?,
        @RequestParam(required = false) endbirth: LocalDateTime?,
        @RequestParam(required = false) type: Int?,
        @RequestParam(required = false) connectid: String?,
        @RequestParam(required = false) level: Int?,
        @RequestParam(required = false) role: Int?,
        @RequestParam(required = false) use: Int?,
        @RequestParam(required = false) startlogindate: LocalDateTime?,
        @RequestParam(required = false) endlogindate: LocalDateTime?,
        @RequestParam(required = false) startlastchangepasswddate: LocalDateTime?,
        @RequestParam(required = false) endlastchangepasswddate: LocalDateTime?,
        @RequestParam(required = false) startdate: LocalDateTime?,
        @RequestParam(required = false) enddate: LocalDateTime?,
    ): ResponseEntity<Map<String, Any>> {
        var results = if (loginid != null || passwd != null || email != null || name != null || tel != null || address != null || image != null || sex != null || startbirth != null || endbirth != null || type != null || connectid != null || level != null || role != null || use != null || startlogindate != null || endlogindate != null || startlastchangepasswddate != null || endlastchangepasswddate != null || startdate != null || enddate != null || false) {
            var filtered = userService.findAll(0, Int.MAX_VALUE).content
            if (loginid != null) {
                filtered = filtered.filter { it.loginid == loginid }
            }
            if (passwd != null) {
                filtered = filtered.filter { it.passwd == passwd }
            }
            if (email != null) {
                filtered = filtered.filter { it.email == email }
            }
            if (name != null) {
                filtered = filtered.filter { it.name == name }
            }
            if (tel != null) {
                filtered = filtered.filter { it.tel == tel }
            }
            if (address != null) {
                filtered = filtered.filter { it.address == address }
            }
            if (image != null) {
                filtered = filtered.filter { it.image == image }
            }
            if (sex != null) {
                filtered = filtered.filter { it.sex.ordinal == sex }
            }
            if (startbirth != null || endbirth != null) {
                filtered = filtered.filter { filterByDateRange(it.birth, startbirth, endbirth) }
            }
            if (type != null) {
                filtered = filtered.filter { it.type.ordinal == type }
            }
            if (connectid != null) {
                filtered = filtered.filter { it.connectid == connectid }
            }
            if (level != null) {
                filtered = filtered.filter { it.level.ordinal == level }
            }
            if (role != null) {
                filtered = filtered.filter { it.role.ordinal == role }
            }
            if (use != null) {
                filtered = filtered.filter { it.use.ordinal == use }
            }
            if (startlogindate != null || endlogindate != null) {
                filtered = filtered.filter { filterByDateRange(it.logindate, startlogindate, endlogindate) }
            }
            if (startlastchangepasswddate != null || endlastchangepasswddate != null) {
                filtered = filtered.filter { filterByDateRange(it.lastchangepasswddate, startlastchangepasswddate, endlastchangepasswddate) }
            }
            if (startdate != null || enddate != null) {
                filtered = filtered.filter { filterByDateRange(it.date, startdate, enddate) }
            }
            filtered
        } else {
            userService.findAll(0, Int.MAX_VALUE).content
        }

        val totalElements = results.size
        val totalPages = if (pageSize > 0) (totalElements + pageSize - 1) / pageSize else 1
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, totalElements)

        val pagedResults = if (startIndex < totalElements) {
            results.subList(startIndex, endIndex)
        } else {
            emptyList()
        }

        val response = mapOf(
            "content" to pagedResults.map { toResponse(it) },
            "page" to page,
            "size" to pageSize,
            "totalElements" to totalElements,
            "totalPages" to totalPages,
            "first" to (page == 0),
            "last" to (page >= totalPages - 1),
            "empty" to pagedResults.isEmpty()
        )

        return ResponseEntity.ok(response)
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

    @PatchMapping("/{id}")
    fun patchUser(
        @PathVariable id: Long,
        @RequestBody request: UserPatchRequest
    ): ResponseEntity<UserResponse> {
        val patchRequest = request.copy(id = id)
        val res = userService.patch(patchRequest)
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