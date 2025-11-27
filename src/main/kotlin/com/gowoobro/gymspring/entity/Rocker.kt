package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.rocker.Available

@Entity
@Table(name = "rocker_tb")
data class Rocker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    val id: Long = 0,
    @Column(name = "r_gym")
    val gymId: Long = 0L,
    @Column(name = "r_group")
    val groupId: Long = 0L,
    @Column(name = "r_name")
    val name: String = "",
    @Column(name = "r_available")
    val available: Available = Available.IN_USE,
    @Column(name = "r_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_group", insertable = false, updatable = false)
    var rockergroup: Rockergroup? = null
}

data class RockerCreateRequest(
    val gym: Long = 0L,
    val group: Long = 0L,
    val name: String = "",
    val available: Available = Available.IN_USE,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RockerUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val group: Long = 0L,
    val name: String = "",
    val available: Available = Available.IN_USE,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class RockerExtraInfo(
    val available: String = "",

    val gym: GymResponse? = null,
    val rockergroup: RockergroupResponse? = null,
)


data class RockerResponse(
    val id: Long,
    val gym: Long,
    val group: Long,
    val name: String,
    val available: Int,
    val date: String?,

    val extra: RockerExtraInfo
){
    companion object {
        fun from(rocker: Rocker): RockerResponse {
            val gymResponse = rocker.gym?.let { GymResponse.from(it) }
            val rockergroupResponse = rocker.rockergroup?.let { RockergroupResponse.from(it) }
            return RockerResponse(
                id = rocker.id,
                gym = rocker.gymId,
                group = rocker.groupId,
                name = rocker.name,
                available = rocker.available.ordinal,
                date = rocker.date?.toString()?.replace("T", " ") ?: "",

                extra = RockerExtraInfo(
                    available = Available.getDisplayName(rocker.available),
                    gym = gymResponse,rockergroup = rockergroupResponse,)
                
            )
        }
    }
}