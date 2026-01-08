package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Notificationhistory
import com.gowoobro.gymspring.entity.NotificationhistoryCreateRequest
import com.gowoobro.gymspring.entity.NotificationhistoryUpdateRequest
import com.gowoobro.gymspring.entity.NotificationhistoryPatchRequest
import com.gowoobro.gymspring.repository.NotificationhistoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notificationhistory.Type
import com.gowoobro.gymspring.enums.notificationhistory.Status


@Service
@Transactional
class NotificationhistoryService(private val notificationhistoryRepository: NotificationhistoryRepository) {

    fun findAll(page: Int = 0, pagesize: Int = 10): Page<Notificationhistory> {
        val pageable: Pageable = PageRequest.of(page, pagesize)
        return notificationhistoryRepository.findAll(pageable)
    }

    fun findById(id: Long): Notificationhistory? {
        return notificationhistoryRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return notificationhistoryRepository.count()
    }


    fun findBySender(senderuser: Long): List<Notificationhistory> {
        return notificationhistoryRepository.findBysenderId(senderuser)
    }

    fun findByReceiver(receiveruser: Long): List<Notificationhistory> {
        return notificationhistoryRepository.findByreceiverId(receiveruser)
    }

    fun findByGym(gym: Long): List<Notificationhistory> {
        return notificationhistoryRepository.findBygymId(gym)
    }

    fun findByType(type: Type): List<Notificationhistory> {
        return notificationhistoryRepository.findByType(type)
    }

    fun findByTitle(title: String): List<Notificationhistory> {
        return notificationhistoryRepository.findByTitle(title)
    }

    fun findByBody(body: String): List<Notificationhistory> {
        return notificationhistoryRepository.findByBody(body)
    }

    fun findByData(data: String): List<Notificationhistory> {
        return notificationhistoryRepository.findByData(data)
    }

    fun findByStatus(status: Status): List<Notificationhistory> {
        return notificationhistoryRepository.findByStatus(status)
    }

    fun findByErrormessage(errormessage: String): List<Notificationhistory> {
        return notificationhistoryRepository.findByErrormessage(errormessage)
    }

    fun findBySentdate(sentdate: LocalDateTime): List<Notificationhistory> {
        return notificationhistoryRepository.findBySentdate(sentdate)
    }

    fun findByDate(date: LocalDateTime): List<Notificationhistory> {
        return notificationhistoryRepository.findByDate(date)
    }


    fun create(request: NotificationhistoryCreateRequest): Notificationhistory {
        val entity = Notificationhistory(
            senderId = request.sender,
            receiverId = request.receiver,
            gymId = request.gym,
            type = request.type,
            title = request.title,
            body = request.body,
            data = request.data,
            status = request.status,
            errormessage = request.errormessage,
            sentdate = request.sentdate,
            date = request.date,
        )
        return notificationhistoryRepository.save(entity)
    }

    fun createBatch(requests: List<NotificationhistoryCreateRequest>): List<Notificationhistory> {
        val entities = requests.map { request ->
            Notificationhistory(
                senderId = request.sender,
                receiverId = request.receiver,
                gymId = request.gym,
                type = request.type,
                title = request.title,
                body = request.body,
                data = request.data,
                status = request.status,
                errormessage = request.errormessage,
                sentdate = request.sentdate,
                date = request.date,
            )
        }
        return notificationhistoryRepository.saveAll(entities)
    }

    fun update(request: NotificationhistoryUpdateRequest): Notificationhistory? {
        val existing = notificationhistoryRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            senderId = request.sender,
            receiverId = request.receiver,
            gymId = request.gym,
            type = request.type,
            title = request.title,
            body = request.body,
            data = request.data,
            status = request.status,
            errormessage = request.errormessage,
            sentdate = request.sentdate,
            date = request.date,
        )
        return notificationhistoryRepository.save(updated)
    }

    fun delete(entity: Notificationhistory): Boolean {
        return try {
            notificationhistoryRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            notificationhistoryRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Notificationhistory>): Boolean {
        return try {
            notificationhistoryRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: NotificationhistoryPatchRequest): Notificationhistory? {
        val existing = notificationhistoryRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            senderId = request.sender ?: existing.senderId,
            receiverId = request.receiver ?: existing.receiverId,
            gymId = request.gym ?: existing.gymId,
            type = request.type ?: existing.type,
            title = request.title ?: existing.title,
            body = request.body ?: existing.body,
            data = request.data ?: existing.data,
            status = request.status ?: existing.status,
            errormessage = request.errormessage ?: existing.errormessage,
            sentdate = request.sentdate ?: existing.sentdate,
            date = request.date ?: existing.date,
        )
        return notificationhistoryRepository.save(updated)
    }
}