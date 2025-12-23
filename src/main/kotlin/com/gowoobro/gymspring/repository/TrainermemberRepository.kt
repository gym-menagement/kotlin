package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.trainermember.Status



@Repository
interface TrainermemberRepository : JpaRepository<Trainermember, Long> {
    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    override fun findAll(pageable: Pageable): Page<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    override fun findById(id: Long): java.util.Optional<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findBytrainerId(traineruser: Long): List<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findBymemberId(memberuser: Long): List<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findBygymId(gym: Long): List<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByStartdate(startdate: LocalDateTime): List<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByEnddate(enddate: LocalDateTime): List<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByStatus(status: Status): List<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByNote(note: String): List<Trainermember>

    @EntityGraph(attributePaths = [
        "traineruser",
        "memberuser",
        "gym"
    ])
    fun findByDate(date: LocalDateTime): List<Trainermember>
}
