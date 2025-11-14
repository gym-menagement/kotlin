package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Appversion
import com.gowoobro.gymspring.entity.AppversionCreateRequest
import com.gowoobro.gymspring.entity.AppversionUpdateRequest
import com.gowoobro.gymspring.repository.AppversionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.appversion.Forceupdate
import com.gowoobro.gymspring.enums.appversion.Status


@Service
@Transactional
class AppversionService(private val appversionRepository: AppversionRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Appversion> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return appversionRepository.findAll(pageable)
    }

    fun findById(id: Long): Appversion? {
        return appversionRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return appversionRepository.count()
    }


    fun findByPlatform(platform: String): List<Appversion> {
        return appversionRepository.findByPlatform(platform)
    }

    fun findByVersion(version: String): List<Appversion> {
        return appversionRepository.findByVersion(version)
    }

    fun findByMinversion(minversion: String): List<Appversion> {
        return appversionRepository.findByMinversion(minversion)
    }

    fun findByForceupdate(forceupdate: Forceupdate): List<Appversion> {
        return appversionRepository.findByForceupdate(forceupdate)
    }

    fun findByUpdatemessage(updatemessage: String): List<Appversion> {
        return appversionRepository.findByUpdatemessage(updatemessage)
    }

    fun findByDownloadurl(downloadurl: String): List<Appversion> {
        return appversionRepository.findByDownloadurl(downloadurl)
    }

    fun findByStatus(status: Status): List<Appversion> {
        return appversionRepository.findByStatus(status)
    }

    fun findByReleasedate(releasedate: LocalDateTime): List<Appversion> {
        return appversionRepository.findByReleasedate(releasedate)
    }

    fun findByCreateddate(createddate: LocalDateTime): List<Appversion> {
        return appversionRepository.findByCreateddate(createddate)
    }

    fun findByDate(date: LocalDateTime): List<Appversion> {
        return appversionRepository.findByDate(date)
    }


    fun create(request: AppversionCreateRequest): Appversion {
        val entity = Appversion(
            platform = request.platform,
            version = request.version,
            minversion = request.minversion,
            forceupdate = request.forceupdate,
            updatemessage = request.updatemessage,
            downloadurl = request.downloadurl,
            status = request.status,
            releasedate = request.releasedate,
            createddate = request.createddate,
            date = request.date,
        )
        return appversionRepository.save(entity)
    }

    fun createBatch(requests: List<AppversionCreateRequest>): List<Appversion> {
        val entities = requests.map { request ->
            Appversion(
                platform = request.platform,
                version = request.version,
                minversion = request.minversion,
                forceupdate = request.forceupdate,
                updatemessage = request.updatemessage,
                downloadurl = request.downloadurl,
                status = request.status,
                releasedate = request.releasedate,
                createddate = request.createddate,
                date = request.date,
            )
        }
        return appversionRepository.saveAll(entities)
    }

    fun update(request: AppversionUpdateRequest): Appversion? {
        val existing = appversionRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            platform = request.platform,
            version = request.version,
            minversion = request.minversion,
            forceupdate = request.forceupdate,
            updatemessage = request.updatemessage,
            downloadurl = request.downloadurl,
            status = request.status,
            releasedate = request.releasedate,
            createddate = request.createddate,
            date = request.date,
        )
        return appversionRepository.save(updated)
    }

    fun delete(entity: Appversion): Boolean {
        return try {
            appversionRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            appversionRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Appversion>): Boolean {
        return try {
            appversionRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}