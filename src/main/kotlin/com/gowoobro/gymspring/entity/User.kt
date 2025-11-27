package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.user.Level
import com.gowoobro.gymspring.enums.user.Use
import com.gowoobro.gymspring.enums.user.Type
import com.gowoobro.gymspring.enums.user.Role
import com.gowoobro.gymspring.enums.user.Sex

@Entity
@Table(name = "user_tb")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    val id: Long = 0,
    @Column(name = "u_loginid")
    val loginid: String = "",
    @Column(name = "u_passwd")
    val passwd: String = "",
    @Column(name = "u_email")
    val email: String = "",
    @Column(name = "u_name")
    val name: String = "",
    @Column(name = "u_tel")
    val tel: String = "",
    @Column(name = "u_address")
    val address: String = "",
    @Column(name = "u_image")
    val image: String = "",
    @Column(name = "u_sex")
    val sex: Sex = Sex.MALE,
    @Column(name = "u_birth")
    val birth: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "u_type")
    val type: Type = Type.NORMAL,
    @Column(name = "u_connectid")
    val connectid: String = "",
    @Column(name = "u_level")
    val level: Level = Level.NORMAL,
    @Column(name = "u_role")
    val role: Role = Role.MEMBER,
    @Column(name = "u_use")
    val use: Use = Use.USE,
    @Column(name = "u_logindate")
    val logindate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "u_lastchangepasswddate")
    val lastchangepasswddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "u_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) 

data class UserCreateRequest(
    val loginid: String = "",
    val passwd: String = "",
    val email: String = "",
    val name: String = "",
    val tel: String = "",
    val address: String = "",
    val image: String = "",
    val sex: Sex = Sex.MALE,
    val birth: LocalDateTime? = LocalDateTime.now(),
    val type: Type = Type.NORMAL,
    val connectid: String = "",
    val level: Level = Level.NORMAL,
    val role: Role = Role.MEMBER,
    val use: Use = Use.USE,
    val logindate: LocalDateTime? = LocalDateTime.now(),
    val lastchangepasswddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UserUpdateRequest(
    val id: Long = 0,
    val loginid: String = "",
    val passwd: String = "",
    val email: String = "",
    val name: String = "",
    val tel: String = "",
    val address: String = "",
    val image: String = "",
    val sex: Sex = Sex.MALE,
    val birth: LocalDateTime? = LocalDateTime.now(),
    val type: Type = Type.NORMAL,
    val connectid: String = "",
    val level: Level = Level.NORMAL,
    val role: Role = Role.MEMBER,
    val use: Use = Use.USE,
    val logindate: LocalDateTime? = LocalDateTime.now(),
    val lastchangepasswddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class UserExtraInfo(
    val level: String = "",
    val use: String = "",
    val type: String = "",
    val role: String = "",
    val sex: String = "",

)


data class UserResponse(
    val id: Long,
    val loginid: String,
    val passwd: String,
    val email: String,
    val name: String,
    val tel: String,
    val address: String,
    val image: String,
    val sex: Int,
    val birth: String?,
    val type: Int,
    val connectid: String,
    val level: Int,
    val role: Int,
    val use: Int,
    val logindate: String?,
    val lastchangepasswddate: String?,
    val date: String?,

    val extra: UserExtraInfo
){
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id,
                loginid = user.loginid,
                passwd = user.passwd,
                email = user.email,
                name = user.name,
                tel = user.tel,
                address = user.address,
                image = user.image,
                sex = user.sex.ordinal,
                birth = user.birth?.toString()?.replace("T", " ") ?: "",
                type = user.type.ordinal,
                connectid = user.connectid,
                level = user.level.ordinal,
                role = user.role.ordinal,
                use = user.use.ordinal,
                logindate = user.logindate?.toString()?.replace("T", " ") ?: "",
                lastchangepasswddate = user.lastchangepasswddate?.toString()?.replace("T", " ") ?: "",
                date = user.date?.toString()?.replace("T", " ") ?: "",

                extra = UserExtraInfo(
                    level = Level.getDisplayName(user.level),use = Use.getDisplayName(user.use),type = Type.getDisplayName(user.type),role = Role.getDisplayName(user.role),sex = Sex.getDisplayName(user.sex),
                    )
                
            )
        }
    }
}