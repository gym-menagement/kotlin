package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "daytype_tb")
data class Daytype(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dt_id")
    val id: Long = 0,
    @Column(name = "dt_gym")
    val gym: Long = 0L,
    @Column(name = "dt_name")
    val name: String = "",
    @Column(name = "dt_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class DaytypeCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class DaytypeUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class DaytypeExtraInfo(

    val gym: GymResponse? = null,
)


data class DaytypeResponse(
    val id: Long,
    val gym: Long,
    val name: String,
    val date: String?,

    val extra: DaytypeExtraInfo
){
    companion object {
        fun from(daytype: Daytype, gymResponse: GymResponse? = null): DaytypeResponse {
            return DaytypeResponse(
                id = daytype.id,
                gym = daytype.gym,
                name = daytype.name,
                date = daytype.date?.toString()?.replace("T", " ") ?: "",

                extra = DaytypeExtraInfo(
                    
                
                     gym = gymResponse,
                )
                
            )
        }
    }
}