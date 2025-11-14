package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Appversion
import com.gowoobro.gymspring.entity.AppversionCreateRequest
import com.gowoobro.gymspring.entity.AppversionUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.appversion.Forceupdate
import com.gowoobro.gymspring.enums.appversion.Status


@Repository
interface AppversionRepository : JpaRepository<Appversion, Long> {
    @Query("SELECT m FROM Appversion m")
    override fun findAll(pageable: Pageable): Page<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.platform = :platform")
    fun findByPlatformWithJoin(platform: String): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.version = :version")
    fun findByVersionWithJoin(version: String): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.minversion = :minversion")
    fun findByMinversionWithJoin(minversion: String): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.forceupdate = :forceupdate")
    fun findByForceupdateWithJoin(forceupdate: Forceupdate): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.updatemessage = :updatemessage")
    fun findByUpdatemessageWithJoin(updatemessage: String): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.downloadurl = :downloadurl")
    fun findByDownloadurlWithJoin(downloadurl: String): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.releasedate = :releasedate")
    fun findByReleasedateWithJoin(releasedate: LocalDateTime): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.createddate = :createddate")
    fun findByCreateddateWithJoin(createddate: LocalDateTime): List<Appversion>

    @Query("SELECT m FROM Appversion m WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Appversion>
}