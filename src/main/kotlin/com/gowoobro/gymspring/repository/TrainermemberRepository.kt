package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.trainermember.Status


@Repository
interface TrainermemberRepository : JpaRepository<Trainermember, Long> {
    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym")
    override fun findAll(pageable: Pageable): Page<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.id = :id")
    override fun findById(id: Long): java.util.Optional<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.trainerId = :traineruser")
    fun findByTrainerWithJoin(traineruser: Long): List<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.memberId = :memberuser")
    fun findByMemberWithJoin(memberuser: Long): List<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.gymId = :gym")
    fun findByGymWithJoin(gym: Long): List<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.startdate = :startdate")
    fun findByStartdateWithJoin(startdate: LocalDateTime): List<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.enddate = :enddate")
    fun findByEnddateWithJoin(enddate: LocalDateTime): List<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.status = :status")
    fun findByStatusWithJoin(status: Status): List<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.note = :note")
    fun findByNoteWithJoin(note: String): List<Trainermember>

    @Query("SELECT m FROM Trainermember m LEFT JOIN FETCH m.traineruser LEFT JOIN FETCH m.memberuser LEFT JOIN FETCH m.gym WHERE m.date = :date")
    fun findByDateWithJoin(date: LocalDateTime): List<Trainermember>
}