package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Notice
import com.gowoobro.gymspring.entity.NoticeCreateRequest
import com.gowoobro.gymspring.entity.NoticeUpdateRequest
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
        return noticeRepository.findByGymWithJoin(gym)
    }

    fun findByTitle(title: String): List<Notice> {
        return noticeRepository.findByTitleWithJoin(title)
    }

    fun findByContent(content: String): List<Notice> {
        return noticeRepository.findByContentWithJoin(content)
    }

    fun findByType(type: Type): List<Notice> {
        return noticeRepository.findByTypeWithJoin(type)
    }

    fun findByIspopup(ispopup: Ispopup): List<Notice> {
        return noticeRepository.findByIspopupWithJoin(ispopup)
    }

    fun findByIspush(ispush: Ispush): List<Notice> {
        return noticeRepository.findByIspushWithJoin(ispush)
    }

    fun findByTarget(target: Target): List<Notice> {
        return noticeRepository.findByTargetWithJoin(target)
    }

    fun findByViewcount(viewcount: Int): List<Notice> {
        return noticeRepository.findByViewcountWithJoin(viewcount)
    }

    fun findByStartdate(startdate: LocalDateTime): List<Notice> {
        return noticeRepository.findByStartdateWithJoin(startdate)
    }

    fun findByEnddate(enddate: LocalDateTime): List<Notice> {
        return noticeRepository.findByEnddateWithJoin(enddate)
    }

    fun findByStatus(status: Status): List<Notice> {
        return noticeRepository.findByStatusWithJoin(status)
    }

    fun findByCreatedby(createdby: Long): List<Notice> {
        return noticeRepository.findByCreatedbyWithJoin(createdby)
    }

    fun findByCreateddate(createddate: LocalDateTime): List<Notice> {
        return noticeRepository.findByCreateddateWithJoin(createddate)
    }

    fun findByUpdateddate(updateddate: LocalDateTime): List<Notice> {
        return noticeRepository.findByUpdateddateWithJoin(updateddate)
    }

    fun findByDate(date: LocalDateTime): List<Notice> {
        return noticeRepository.findByDateWithJoin(date)
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
}