package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.appversion.Forceupdate
import com.gowoobro.gymspring.enums.appversion.Status

@Entity
@Table(name = "appversion_tb")
data class Appversion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "av_id")
    val id: Long = 0,
    @Column(name = "av_platform")
    val platform: String = "",
    @Column(name = "av_version")
    val version: String = "",
    @Column(name = "av_minversion")
    val minversion: String = "",
    @Column(name = "av_forceupdate")
    val forceupdate: Forceupdate = Forceupdate.NO,
    @Column(name = "av_updatemessage")
    val updatemessage: String = "",
    @Column(name = "av_downloadurl")
    val downloadurl: String = "",
    @Column(name = "av_status")
    val status: Status = Status.INACTIVE,
    @Column(name = "av_releasedate")
    val releasedate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "av_createddate")
    val createddate: LocalDateTime? = LocalDateTime.now(),
    @Column(name = "av_date")
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AppversionCreateRequest(
    val platform: String = "",
    val version: String = "",
    val minversion: String = "",
    val forceupdate: Forceupdate = Forceupdate.NO,
    val updatemessage: String = "",
    val downloadurl: String = "",
    val status: Status = Status.INACTIVE,
    val releasedate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AppversionUpdateRequest(
    val id: Long = 0,
    val platform: String = "",
    val version: String = "",
    val minversion: String = "",
    val forceupdate: Forceupdate = Forceupdate.NO,
    val updatemessage: String = "",
    val downloadurl: String = "",
    val status: Status = Status.INACTIVE,
    val releasedate: LocalDateTime? = LocalDateTime.now(),
    val createddate: LocalDateTime? = LocalDateTime.now(),
    val date: LocalDateTime? = LocalDateTime.now(),
)

data class AppversionExtraInfo(
    val forceupdate: String = "",
    val status: String = "",

)


data class AppversionResponse(
    val id: Long,
    val platform: String,
    val version: String,
    val minversion: String,
    val forceupdate: Int,
    val updatemessage: String,
    val downloadurl: String,
    val status: Int,
    val releasedate: String?,
    val createddate: String?,
    val date: String?,

    val extra: AppversionExtraInfo
){
    companion object {
        fun from(appversion: Appversion): AppversionResponse {
            return AppversionResponse(
                id = appversion.id,
                platform = appversion.platform,
                version = appversion.version,
                minversion = appversion.minversion,
                forceupdate = appversion.forceupdate.ordinal,
                updatemessage = appversion.updatemessage,
                downloadurl = appversion.downloadurl,
                status = appversion.status.ordinal,
                releasedate = appversion.releasedate?.toString()?.replace("T", " ") ?: "",
                createddate = appversion.createddate?.toString()?.replace("T", " ") ?: "",
                date = appversion.date?.toString()?.replace("T", " ") ?: "",

                extra = AppversionExtraInfo(
                    forceupdate = Forceupdate.getDisplayName(appversion.forceupdate),status = Status.getDisplayName(appversion.status),
                    )
                
            )
        }
    }
}