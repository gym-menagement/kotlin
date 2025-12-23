package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
import com.gowoobro.gymspring.entity.NoticePatchRequest
import com.gowoobro.gymspring.repository.NoticeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notice.Type
import com.gowoobro.gymspring.enums.notice.Ispopup
import com.gowoobro.gymspring.enums.notice.Ispush
import com.gowoobro.gymspring.enums.notice.Target
import com.gowoobro.gymspring.enums.notice.Status


@Service
@Transactional
class NoticeService(private val noticeRepository: NoticeRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Notice> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return noticeRepository.findAll(pageable)
    }

    fun findById(id: Long): Notice? {
        return noticeRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return noticeRepository.count()
    }


    fun findByGym(gym: Long): List<Notice> {
        return noticeRepository.findBygymId(gym)
    }

    fun findByTitle(title: String): List<Notice> {
        return noticeRepository.findByTitle(title)
    }

    fun findByContent(content: String): List<Notice> {
        return noticeRepository.findByContent(content)
    }

    fun findByType(type: Type): List<Notice> {
        return noticeRepository.findByType(type)
    }

    fun findByIspopup(ispopup: Ispopup): List<Notice> {
        return noticeRepository.findByIspopup(ispopup)
    }

    fun findByIspush(ispush: Ispush): List<Notice> {
        return noticeRepository.findByIspush(ispush)
    }

    fun findByTarget(target: Target): List<Notice> {
        return noticeRepository.findByTarget(target)
    }

    fun findByViewcount(viewcount: Int): List<Notice> {
        return noticeRepository.findByViewcount(viewcount)
    }

    fun findByStartdate(startdate: LocalDateTime): List<Notice> {
        return noticeRepository.findByStartdate(startdate)
    }

    fun findByEnddate(enddate: LocalDateTime): List<Notice> {
        return noticeRepository.findByEnddate(enddate)
    }

    fun findByStatus(status: Status): List<Notice> {
        return noticeRepository.findByStatus(status)
    }

    fun findByCreatedby(user: Long): List<Notice> {
        return noticeRepository.findBycreatedbyId(user)
    }

    fun findByCreateddate(createddate: LocalDateTime): List<Notice> {
        return noticeRepository.findByCreateddate(createddate)
    }

    fun findByUpdateddate(updateddate: LocalDateTime): List<Notice> {
        return noticeRepository.findByUpdateddate(updateddate)
    }

    fun findByDate(date: LocalDateTime): List<Notice> {
        return noticeRepository.findByDate(date)
    }


    fun create(request: NoticeCreateRequest): Notice {
        val entity = Notice(
            gymId = request.gym,
            title = request.title,
            content = request.content,
            type = request.type,
            ispopup = request.ispopup,
            ispush = request.ispush,
            target = request.target,
            viewcount = request.viewcount,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            createdbyId = request.createdby,
            createddate = request.createddate,
            updateddate = request.updateddate,
            date = request.date,
        )
        return noticeRepository.save(entity)
    }

    fun createBatch(requests: List<NoticeCreateRequest>): List<Notice> {
        val entities = requests.map { request ->
            Notice(
                gymId = request.gym,
                title = request.title,
                content = request.content,
                type = request.type,
                ispopup = request.ispopup,
                ispush = request.ispush,
                target = request.target,
                viewcount = request.viewcount,
                startdate = request.startdate,
                enddate = request.enddate,
                status = request.status,
                createdbyId = request.createdby,
                createddate = request.createddate,
                updateddate = request.updateddate,
                date = request.date,
            )
        }
        return noticeRepository.saveAll(entities)
    }

    fun update(request: NoticeUpdateRequest): Notice? {
        val existing = noticeRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            title = request.title,
            content = request.content,
            type = request.type,
            ispopup = request.ispopup,
            ispush = request.ispush,
            target = request.target,
            viewcount = request.viewcount,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            createdbyId = request.createdby,
            createddate = request.createddate,
            updateddate = request.updateddate,
            date = request.date,
        )
        return noticeRepository.save(updated)
    }

    fun delete(entity: Notice): Boolean {
        return try {
            noticeRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            noticeRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Notice>): Boolean {
        return try {
            noticeRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: NoticePatchRequest): Notice? {
        val existing = noticeRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym ?: existing.gymId,
            title = request.title ?: existing.title,
            content = request.content ?: existing.content,
            type = request.type ?: existing.type,
            ispopup = request.ispopup ?: existing.ispopup,
            ispush = request.ispush ?: existing.ispush,
            target = request.target ?: existing.target,
            viewcount = request.viewcount ?: existing.viewcount,
            startdate = request.startdate ?: existing.startdate,
            enddate = request.enddate ?: existing.enddate,
            status = request.status ?: existing.status,
            createdbyId = request.createdby ?: existing.createdbyId,
            createddate = request.createddate ?: existing.createddate,
            updateddate = request.updateddate ?: existing.updateddate,
            date = request.date ?: existing.date,
        )
        return noticeRepository.save(updated)
    }
}