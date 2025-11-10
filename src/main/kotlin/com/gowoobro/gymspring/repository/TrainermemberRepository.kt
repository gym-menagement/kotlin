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



@Repository
interface TrainermemberRepository : JpaRepository<Trainermember, Long> {
    override fun findAll(pageable: Pageable): Page<Trainermember>

    fun findByTrainer(trainer: Long): List<Trainermember>

    fun findByMember(member: Long): List<Trainermember>

    fun findByGym(gym: Long): List<Trainermember>

    fun findByStartdate(startdate: LocalDateTime): List<Trainermember>

    fun findByEnddate(enddate: LocalDateTime): List<Trainermember>

    fun findByStatus(status: Int): List<Trainermember>

    fun findByNote(note: String): List<Trainermember>

    fun findByDate(date: LocalDateTime): List<Trainermember>
}