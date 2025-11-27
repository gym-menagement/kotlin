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
    val gymId: Long = 0L,
    @Column(name = "rg_name")
    val name: String = "",
    @Column(name = "rg_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rg_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

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

data class RockergroupPatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val name: String? = null,
    val date: LocalDateTime? = null,
)

data class RockergroupExtraInfo(

    val gym: GymResponse? = null,
)


data class RockergroupResponse(
    val id: Long,
    val gym: Long,
    val name: String,
    val date: String?,

    val extra: RockergroupExtraInfo
){
    companion object {
        fun from(rockergroup: Rockergroup): RockergroupResponse {
            val gymResponse = rockergroup.gym?.let { GymResponse.from(it) }
            return RockergroupResponse(
                id = rockergroup.id,
                gym = rockergroup.gymId,
                name = rockergroup.name,
                date = rockergroup.date?.toString()?.replace("T", " ") ?: "",

                extra = RockergroupExtraInfo(
                    gym = gymResponse,)
                
            )
        }
    }
}