package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import com.gowoobro.gymspring.repository.TrainermemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.trainermember.Status


@Service
@Transactional
class TrainermemberService(private val trainermemberRepository: TrainermemberRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Trainermember> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return trainermemberRepository.findAll(pageable)
    }

    fun findById(id: Long): Trainermember? {
        return trainermemberRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return trainermemberRepository.count()
    }


    fun findByTrainer(trainer: Long): List<Trainermember> {
        return trainermemberRepository.findByTrainer(trainer)
    }

    fun findByMember(member: Long): List<Trainermember> {
        return trainermemberRepository.findByMember(member)
    }

    fun findByGym(gym: Long): List<Trainermember> {
        return trainermemberRepository.findByGym(gym)
    }

    fun findByStartdate(startdate: LocalDateTime): List<Trainermember> {
        return trainermemberRepository.findByStartdate(startdate)
    }

    fun findByEnddate(enddate: LocalDateTime): List<Trainermember> {
        return trainermemberRepository.findByEnddate(enddate)
    }

    fun findByStatus(status: Status): List<Trainermember> {
        return trainermemberRepository.findByStatus(status)
    }

    fun findByNote(note: String): List<Trainermember> {
        return trainermemberRepository.findByNote(note)
    }

    fun findByDate(date: LocalDateTime): List<Trainermember> {
        return trainermemberRepository.findByDate(date)
    }


    fun create(request: TrainermemberCreateRequest): Trainermember {
        val entity = Trainermember(
            trainer = request.trainer,
            member = request.member,
            gym = request.gym,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            note = request.note,
            date = request.date,
        )
        return trainermemberRepository.save(entity)
    }

    fun createBatch(requests: List<TrainermemberCreateRequest>): List<Trainermember> {
        val entities = requests.map { request ->
            Trainermember(
                trainer = request.trainer,
                member = request.member,
                gym = request.gym,
                startdate = request.startdate,
                enddate = request.enddate,
                status = request.status,
                note = request.note,
                date = request.date,
            )
        }
        return trainermemberRepository.saveAll(entities)
    }

    fun update(request: TrainermemberUpdateRequest): Trainermember? {
        val existing = trainermemberRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            trainer = request.trainer,
            member = request.member,
            gym = request.gym,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            note = request.note,
            date = request.date,
        )
        return trainermemberRepository.save(updated)
    }

    fun delete(entity: Trainermember): Boolean {
        return try {
            trainermemberRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            trainermemberRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Trainermember>): Boolean {
        return try {
            trainermemberRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}