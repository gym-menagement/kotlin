package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Trainermember
import com.gowoobro.gymspring.entity.TrainermemberCreateRequest
import com.gowoobro.gymspring.entity.TrainermemberUpdateRequest
import com.gowoobro.gymspring.entity.TrainermemberPatchRequest
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


    fun findByTrainer(traineruser: Long): List<Trainermember> {
        return trainermemberRepository.findBytrainerId(traineruser)
    }

    fun findByMember(memberuser: Long): List<Trainermember> {
        return trainermemberRepository.findBymemberId(memberuser)
    }

    fun findByGym(gym: Long): List<Trainermember> {
        return trainermemberRepository.findBygymId(gym)
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
            trainerId = request.trainer,
            memberId = request.member,
            gymId = request.gym,
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
                trainerId = request.trainer,
                memberId = request.member,
                gymId = request.gym,
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
            trainerId = request.trainer,
            memberId = request.member,
            gymId = request.gym,
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

    fun patch(request: TrainermemberPatchRequest): Trainermember? {
        val existing = trainermemberRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            trainerId = request.trainer ?: existing.trainerId,
            memberId = request.member ?: existing.memberId,
            gymId = request.gym ?: existing.gymId,
            startdate = request.startdate ?: existing.startdate,
            enddate = request.enddate ?: existing.enddate,
            status = request.status ?: existing.status,
            note = request.note ?: existing.note,
            date = request.date ?: existing.date,
        )
        return trainermemberRepository.save(updated)
    }
}