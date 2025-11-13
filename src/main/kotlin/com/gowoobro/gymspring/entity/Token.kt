package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.token.Status

@Entity
@Table(name = "token_tb")
data class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "to_id")
    val id: Long = 0,
    @Column(name = "to_user")
    val user: Long = 0L,
    @Column(name = "to_token")
    val token: String = "",
    @Column(name = "to_status")
    val status: Status = Status.ACTIVE,
    @Column(name = "to_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TokenCreateRequest(
    val user: Long = 0L,
    val token: String = "",
    val status: Status = Status.ACTIVE,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TokenUpdateRequest(
    val id: Long = 0,
    val user: Long = 0L,
    val token: String = "",
    val status: Status = Status.ACTIVE,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TokenExtraInfo(
    val status: String = "",

    val user: UserResponse? = null,
)


data class TokenResponse(
    val id: Long,
    val user: Long,
    val token: String,
    val status: Int,
    val date: String?,

    val extra: TokenExtraInfo
){
    companion object {
        fun from(token: Token, userResponse: UserResponse? = null): TokenResponse {
            return TokenResponse(
                id = token.id,
                user = token.user,
                token = token.token,
                status = token.status.ordinal,
                date = token.date?.toString()?.replace("T", " ") ?: "",

                extra = TokenExtraInfo(
                    
                        status = Status.getDisplayName(token.status),
                        
                
                     user = userResponse,
                )
                
            )
        }
    }
}