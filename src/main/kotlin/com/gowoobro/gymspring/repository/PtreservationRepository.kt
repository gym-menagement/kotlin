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

import com.gowoobro.gymspring.enums.ptreservation.Status


@Repository
interface PtreservationRepository : JpaRepository<Ptreservation, Long> {
    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.trainerId = :traineruser")
    fun findByTrainerWithJoin(traineruser: Long): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.memberId = :memberuser")
    fun findByMemberWithJoin(memberuser: Long): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.reservationdate = :reservationdate")
    fun findByReservationdateWithJoin(reservationdate: LocalDateTime): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.starttime = :starttime")
    fun findByStarttimeWithJoin(starttime: String): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.endtime = :endtime")
    fun findByEndtimeWithJoin(endtime: String): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.duration = :duration")
    fun findByDurationWithJoin(duration: Int): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.note = :note")
    fun findByNoteWithJoin(note: String): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.cancelreason = :cancelreason")
    fun findByCancelreasonWithJoin(cancelreason: String): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.createddate = :createddate")
    fun findByCreateddateWithJoin(createddate: LocalDateTime): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.updateddate = :updateddate")
    fun findByUpdateddateWithJoin(updateddate: LocalDateTime): List<Ptreservation>

    @Query("SELECT m FROM Ptreservation m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Ptreservation>
}