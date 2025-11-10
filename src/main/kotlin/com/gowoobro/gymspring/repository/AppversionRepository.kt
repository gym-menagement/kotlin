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



@Repository
interface AppversionRepository : JpaRepository<Appversion, Long> {
    override fun findAll(pageable: Pageable): Page<Appversion>

    fun findByPlatform(platform: String): List<Appversion>

    fun findByVersion(version: String): List<Appversion>

    fun findByMinversion(minversion: String): List<Appversion>

    fun findByForceupdate(forceupdate: Int): List<Appversion>

    fun findByUpdatemessage(updatemessage: String): List<Appversion>

    fun findByDownloadurl(downloadurl: String): List<Appversion>

    fun findByStatus(status: Int): List<Appversion>

    fun findByReleasedate(releasedate: LocalDateTime): List<Appversion>

    fun findByCreateddate(createddate: LocalDateTime): List<Appversion>
}