package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "gym_tb")
data class Gym(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g_id")
    val id: Long = 0,
    @Column(name = "g_name")
    val name: String = "",
    @Column(name = "g_address")
    val address: String = "",
    @Column(name = "g_tel")
    val tel: String = "",
    @Column(name = "g_user")
    val user: Long = 0L,
    @Column(name = "g_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) 

data class GymCreateRequest(
    val name: String = "",
    val address: String = "",
    val tel: String = "",
    val user: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class GymUpdateRequest(
    val id: Long = 0,
    val name: String = "",
    val address: String = "",
    val tel: String = "",
    val user: Long = 0L,
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class GymResponse(
    val id: Long,
    val name: String,
    val address: String,
    val tel: String,
    val user: Long,
    val date: String?,

    val extra: Map<String, Any?> = emptyMap()
){
    companion object {
        fun from(gym: Gym): GymResponse {
            return GymResponse(
                id = gym.id,
                name = gym.name,
                address = gym.address,
                tel = gym.tel,
                user = gym.user,
                date = gym.date?.toString()?.replace("T", " ") ?: "",

                extra =  emptyMap()
            )
        }
    }
}