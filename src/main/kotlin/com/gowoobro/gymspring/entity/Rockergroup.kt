package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "rockergroup_tb")
data class Rockergroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rg_id")
    val id: Long = 0,
    @Column(name = "rg_gym")
    val gym: Long = 0L,
    @Column(name = "rg_name")
    val name: String = "",
    @Column(name = "rg_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RockergroupCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RockergroupUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)



data class RockergroupResponse(
    val id: Long,
    val gym: Long,
    val name: String,
    val date: LocalDateTime?,

){
    companion object {
        fun from(rockergroup: Rockergroup): RockergroupResponse {
            return RockergroupResponse(
                id = rockergroup.id,
                gym = rockergroup.gym,
                name = rockergroup.name,
                date = rockergroup.date,
            )
        }
    }
}