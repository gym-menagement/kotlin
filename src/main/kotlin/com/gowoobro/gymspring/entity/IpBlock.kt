package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ipblock.Type
import com.gowoobro.gymspring.enums.ipblock.Policy
import com.gowoobro.gymspring.enums.ipblock.Use

@Entity
@Table(name = "ipblock_tb")
data class Ipblock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ib_id")
    val id: Long = 0,
    @Column(name = "ib_address")
    val address: String = "",
    @Column(name = "ib_type")
    val type: Type = Type.ADMIN,
    @Column(name = "ib_policy")
    val policy: Policy = Policy.GRANT,
    @Column(name = "ib_use")
    val use: Use = Use.USE,
    @Column(name = "ib_order")
    val order: Int = 0,
    @Column(name = "ib_date")
    val date: LocalDateTime? = LocalDateTime.now(),
) 

data class IpblockCreateRequest(
    val address: String = "",
    val type: Type = Type.ADMIN,
    val policy: Policy = Policy.GRANT,
    val use: Use = Use.USE,
    val order: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class IpblockUpdateRequest(
    val id: Long = 0,
    val address: String = "",
    val type: Type = Type.ADMIN,
    val policy: Policy = Policy.GRANT,
    val use: Use = Use.USE,
    val order: Int = 0,
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class IpblockPatchRequest(
    val id: Long = 0,
    val address: String? = null,
    val type: Type? = null,
    val policy: Policy? = null,
    val use: Use? = null,
    val order: Int? = null,
    val date: LocalDateTime? = null,
)

data class IpblockExtraInfo(
    val type: String = "",
    val policy: String = "",
    val use: String = "",

)


data class IpblockResponse(
    val id: Long,
    val address: String,
    val type: Int,
    val policy: Int,
    val use: Int,
    val order: Int,
    val date: String?,

    val extra: IpblockExtraInfo
){
    companion object {
        fun from(ipblock: Ipblock): IpblockResponse {
            return IpblockResponse(
                id = ipblock.id,
                address = ipblock.address,
                type = ipblock.type.ordinal,
                policy = ipblock.policy.ordinal,
                use = ipblock.use.ordinal,
                order = ipblock.order,
                date = ipblock.date?.toString()?.replace("T", " ") ?: "",

                extra = IpblockExtraInfo(
                    type = Type.getDisplayName(ipblock.type),policy = Policy.getDisplayName(ipblock.policy),use = Use.getDisplayName(ipblock.use),
                    )
                
            )
        }
    }
}