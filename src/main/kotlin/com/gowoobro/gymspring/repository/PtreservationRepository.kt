package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Ptreservation
import com.gowoobro.gymspring.entity.PtreservationCreateRequest
import com.gowoobro.gymspring.entity.PtreservationUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ptreservation.Status



@Repository
interface PtreservationRepository : JpaRepository<Ptreservation, Long> {
    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByTrainer(trainer: Long): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByMember(member: Long): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByGym(gym: Long): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByReservationdate(reservationdate: LocalDateTime): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByStarttime(starttime: String): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByEndtime(endtime: String): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByDuration(duration: Int): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByStatus(status: Status): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByNote(note: String): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByCancelreason(cancelreason: String): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByCreateddate(createddate: LocalDateTime): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByUpdateddate(updateddate: LocalDateTime): List<Ptreservation>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Ptreservation>
}
