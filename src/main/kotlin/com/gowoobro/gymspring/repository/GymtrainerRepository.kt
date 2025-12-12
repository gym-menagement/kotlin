package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Gymtrainer
import com.gowoobro.gymspring.entity.GymtrainerCreateRequest
import com.gowoobro.gymspring.entity.GymtrainerUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.gymtrainer.Status



@Repository
interface GymtrainerRepository : JpaRepository<Gymtrainer, Long> {
    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    override fun findAll(pageable: Pageable): Page<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    override fun findById(id: Long): java.util.Optional<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByGym(gym: Long): List<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByTrainer(trainer: Long): List<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByStartdate(startdate: LocalDateTime): List<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByEnddate(enddate: LocalDateTime): List<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByStatus(status: Status): List<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByPosition(position: String): List<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByNote(note: String): List<Gymtrainer>

    @EntityGraph(attributePaths = [
        "gym",
        "traineruser"
    ])
    fun findByDate(date: LocalDateTime): List<Gymtrainer>
}
