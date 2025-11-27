package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "healthcategory_tb")
data class Healthcategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hc_id")
    val id: Long = 0,
    @Column(name = "hc_gym")
    val gymId: Long = 0L,
    @Column(name = "hc_name")
    val name: String = "",
    @Column(name = "hc_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hc_gym", insertable = false, updatable = false)
    var gym: Gym? = null
}

data class HealthcategoryCreateRequest(
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class HealthcategoryUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val name: String = "",
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class HealthcategoryExtraInfo(

    val gym: GymResponse? = null,
)


data class HealthcategoryResponse(
    val id: Long,
    val gym: Long,
    val name: String,
    val date: String?,

    val extra: HealthcategoryExtraInfo
){
    companion object {
        fun from(healthcategory: Healthcategory): HealthcategoryResponse {
            val gymResponse = healthcategory.gym?.let { GymResponse.from(it) }
            return HealthcategoryResponse(
                id = healthcategory.id,
                gym = healthcategory.gymId,
                name = healthcategory.name,
                date = healthcategory.date?.toString()?.replace("T", " ") ?: "",

                extra = HealthcategoryExtraInfo(
                    gym = gymResponse,)
                
            )
        }
    }
}