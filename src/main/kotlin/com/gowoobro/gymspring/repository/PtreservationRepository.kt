package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Ptreservation
import com.gowoobro.gymspring.entity.PtreservationCreateRequest
import com.gowoobro.gymspring.entity.PtreservationUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime



@Repository
interface PtreservationRepository : JpaRepository<Ptreservation, Long> {
    override fun findAll(pageable: Pageable): Page<Ptreservation>


    fun findByTrainer(trainer: Long): List<Ptreservation>

    fun findByMember(member: Long): List<Ptreservation>

    fun findByGym(gym: Long): List<Ptreservation>

    fun findByReservationdate(reservationdate: LocalDateTime): List<Ptreservation>

    fun findByStarttime(starttime: String): List<Ptreservation>

    fun findByEndtime(endtime: String): List<Ptreservation>

    fun findByDuration(duration: Int): List<Ptreservation>

    fun findByStatus(status: Int): List<Ptreservation>

    fun findByNote(note: String): List<Ptreservation>

    fun findByCancelreason(cancelreason: String): List<Ptreservation>

    fun findByCreateddate(createddate: LocalDateTime): List<Ptreservation>

    fun findByUpdateddate(updateddate: LocalDateTime): List<Ptreservation>
}