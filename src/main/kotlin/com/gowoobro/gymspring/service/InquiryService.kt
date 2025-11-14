package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Inquiry
import com.gowoobro.gymspring.entity.InquiryCreateRequest
import com.gowoobro.gymspring.entity.InquiryUpdateRequest
import com.gowoobro.gymspring.repository.InquiryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.inquiry.Type
import com.gowoobro.gymspring.enums.inquiry.Status


@Service
@Transactional
class InquiryService(private val inquiryRepository: InquiryRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Inquiry> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return inquiryRepository.findAll(pageable)
    }

    fun findById(id: Long): Inquiry? {
        return inquiryRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return inquiryRepository.count()
    }


    fun findByUser(user: Long): List<Inquiry> {
        return inquiryRepository.findByUserWithJoin(user)
    }

    fun findByGym(gym: Long): List<Inquiry> {
        return inquiryRepository.findByGymWithJoin(gym)
    }

    fun findByType(type: Type): List<Inquiry> {
        return inquiryRepository.findByTypeWithJoin(type)
    }

    fun findByTitle(title: String): List<Inquiry> {
        return inquiryRepository.findByTitleWithJoin(title)
    }

    fun findByContent(content: String): List<Inquiry> {
        return inquiryRepository.findByContentWithJoin(content)
    }

    fun findByStatus(status: Status): List<Inquiry> {
        return inquiryRepository.findByStatusWithJoin(status)
    }

    fun findByAnswer(answer: String): List<Inquiry> {
        return inquiryRepository.findByAnswerWithJoin(answer)
    }

    fun findByAnsweredby(answeredby: Long): List<Inquiry> {
        return inquiryRepository.findByAnsweredbyWithJoin(answeredby)
    }

    fun findByAnswereddate(answereddate: LocalDateTime): List<Inquiry> {
        return inquiryRepository.findByAnswereddateWithJoin(answereddate)
    }

    fun findByCreateddate(createddate: LocalDateTime): List<Inquiry> {
        return inquiryRepository.findByCreateddateWithJoin(createddate)
    }

    fun findByDate(date: LocalDateTime): List<Inquiry> {
        return inquiryRepository.findByDateWithJoin(date)
    }


    fun create(request: InquiryCreateRequest): Inquiry {
        val entity = Inquiry(
            userId = request.user,
            gymId = request.gym,
            type = request.type,
            title = request.title,
            content = request.content,
            status = request.status,
            answer = request.answer,
            answeredbyId = request.answeredby,
            answereddate = request.answereddate,
            createddate = request.createddate,
            date = request.date,
        )
        return inquiryRepository.save(entity)
    }

    fun createBatch(requests: List<InquiryCreateRequest>): List<Inquiry> {
        val entities = requests.map { request ->
            Inquiry(
                userId = request.user,
                gymId = request.gym,
                type = request.type,
                title = request.title,
                content = request.content,
                status = request.status,
                answer = request.answer,
                answeredbyId = request.answeredby,
                answereddate = request.answereddate,
                createddate = request.createddate,
                date = request.date,
            )
        }
        return inquiryRepository.saveAll(entities)
    }

    fun update(request: InquiryUpdateRequest): Inquiry? {
        val existing = inquiryRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            userId = request.user,
            gymId = request.gym,
            type = request.type,
            title = request.title,
            content = request.content,
            status = request.status,
            answer = request.answer,
            answeredbyId = request.answeredby,
            answereddate = request.answereddate,
            createddate = request.createddate,
            date = request.date,
        )
        return inquiryRepository.save(updated)
    }

    fun delete(entity: Inquiry): Boolean {
        return try {
            inquiryRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            inquiryRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Inquiry>): Boolean {
        return try {
            inquiryRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}