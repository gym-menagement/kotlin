package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.NotificationHistory
import com.gowoobro.gymspring.enums.notificationhistory.NotificationType
import com.gowoobro.gymspring.enums.notificationhistory.SendStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface NotificationHistoryRepository : JpaRepository<NotificationHistory, Long> {
    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    fun findByReceiverId(receiverId: Long, pageable: Pageable): Page<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    fun findBySenderId(senderId: Long, pageable: Pageable): Page<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    fun findByGymId(gymId: Long, pageable: Pageable): Page<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    fun findByType(type: NotificationType, pageable: Pageable): Page<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    fun findByStatus(status: SendStatus, pageable: Pageable): Page<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    fun findByReceiverIdAndType(receiverId: Long, type: NotificationType, pageable: Pageable): Page<NotificationHistory>

    @EntityGraph(attributePaths = [
        "sender",
        "receiver",
        "gym"
    ])
    fun findBySentDateBetween(startDate: LocalDateTime, endDate: LocalDateTime, pageable: Pageable): Page<NotificationHistory>
}
