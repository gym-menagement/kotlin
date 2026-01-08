package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Notificationhistory
import com.gowoobro.gymspring.entity.NotificationhistoryCreateRequest
import com.gowoobro.gymspring.entity.NotificationhistoryUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.notificationhistory.Type
import com.gowoobro.gymspring.enums.notificationhistory.Status



@Repository
interface NotificationhistoryRepository : JpaRepository<Notificationhistory, Long> {
    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findBysenderId(senderuser: Long): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByreceiverId(receiveruser: Long): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findBygymId(gym: Long): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByType(type: Type): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByTitle(title: String): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByBody(body: String): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByData(data: String): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByStatus(status: Status): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByErrormessage(errormessage: String): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findBySentdate(sentdate: LocalDateTime): List<Notificationhistory>

    @EntityGraph(attributePaths = [
        "senderuser",
        "receiveruser",
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Notificationhistory>
}
