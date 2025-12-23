package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "term_tb")
data class Term(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    val id: Long = 0,
    @Column(name = "t_gym")
    val gymId: Long = 0L,
    @Column(name = "t_daytype")
    val daytypeId: Long = 0L,
    @Column(name = "t_name")
    val name: String = "",
    @Column(name = "t_term")
    val term: Int = 0,
    @Column(name = "t_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_gym", insertable = false, updatable = false)
    var gym: Gym? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_daytype", insertable = false, updatable = false)
    var daytype: Daytype? = null
}

data class TermCreateRequest(
    val gym: Long = 0L,
    val daytype: Long = 0L,
    val name: String = "",
    val term: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TermUpdateRequest(
    val id: Long = 0,
    val gym: Long = 0L,
    val daytype: Long = 0L,
    val name: String = "",
    val term: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class TermPatchRequest(
    val id: Long = 0,
    val gym: Long? = null,
    val daytype: Long? = null,
    val name: String? = null,
    val term: Int? = null,
    val date: LocalDateTime? = null,
)

data class TermExtraInfo(

    val gym: GymResponse? = null,
    val daytype: DaytypeResponse? = null,
)


data class TermResponse(
    val id: Long,
    val gym: Long,
    val daytype: Long,
    val name: String,
    val term: Int,
    val date: String?,

    val extra: TermExtraInfo
){
    companion object {
        fun from(term: Term): TermResponse {
            val gymResponse = term.gym?.let { GymResponse.from(it) }
            val daytypeResponse = term.daytype?.let { DaytypeResponse.from(it) }
            return TermResponse(
                id = term.id,
                gym = term.gymId,
                daytype = term.daytypeId,
                name = term.name,
                term = term.term,
                date = term.date?.toString()?.replace("T", " ") ?: "",

                extra = TermExtraInfo(
                    gym = gymResponse,daytype = daytypeResponse,)
                
            )
        }
    }
}