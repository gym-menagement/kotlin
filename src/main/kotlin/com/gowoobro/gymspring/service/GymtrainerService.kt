package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Gymtrainer
import com.gowoobro.gymspring.entity.GymtrainerCreateRequest
import com.gowoobro.gymspring.entity.GymtrainerUpdateRequest
import com.gowoobro.gymspring.entity.GymtrainerPatchRequest
import com.gowoobro.gymspring.repository.GymtrainerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.gymtrainer.Status


@Service
@Transactional
class GymtrainerService(private val gymtrainerRepository: GymtrainerRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Gymtrainer> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return gymtrainerRepository.findAll(pageable)
    }

    fun findById(id: Long): Gymtrainer? {
        return gymtrainerRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return gymtrainerRepository.count()
    }


    fun findByGym(gym: Long): List<Gymtrainer> {
        return gymtrainerRepository.findByGym(gym)
    }

    fun findByTrainer(trainer: Long): List<Gymtrainer> {
        return gymtrainerRepository.findByTrainer(trainer)
    }

    fun findByStartdate(startdate: LocalDateTime): List<Gymtrainer> {
        return gymtrainerRepository.findByStartdate(startdate)
    }

    fun findByEnddate(enddate: LocalDateTime): List<Gymtrainer> {
        return gymtrainerRepository.findByEnddate(enddate)
    }

    fun findByStatus(status: Status): List<Gymtrainer> {
        return gymtrainerRepository.findByStatus(status)
    }

    fun findByPosition(position: String): List<Gymtrainer> {
        return gymtrainerRepository.findByPosition(position)
    }

    fun findByNote(note: String): List<Gymtrainer> {
        return gymtrainerRepository.findByNote(note)
    }

    fun findByDate(date: LocalDateTime): List<Gymtrainer> {
        return gymtrainerRepository.findByDate(date)
    }


    fun create(request: GymtrainerCreateRequest): Gymtrainer {
        val entity = Gymtrainer(
            gym = request.gym,
            trainer = request.trainer,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            position = request.position,
            note = request.note,
            date = request.date,
        )
        return gymtrainerRepository.save(entity)
    }

    fun createBatch(requests: List<GymtrainerCreateRequest>): List<Gymtrainer> {
        val entities = requests.map { request ->
            Gymtrainer(
                gym = request.gym,
                trainer = request.trainer,
                startdate = request.startdate,
                enddate = request.enddate,
                status = request.status,
                position = request.position,
                note = request.note,
                date = request.date,
            )
        }
        return gymtrainerRepository.saveAll(entities)
    }

    fun update(request: GymtrainerUpdateRequest): Gymtrainer? {
        val existing = gymtrainerRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gym = request.gym,
            trainer = request.trainer,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            position = request.position,
            note = request.note,
            date = request.date,
        )
        return gymtrainerRepository.save(updated)
    }

    fun delete(entity: Gymtrainer): Boolean {
        return try {
            gymtrainerRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            gymtrainerRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Gymtrainer>): Boolean {
        return try {
            gymtrainerRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun patch(request: GymtrainerPatchRequest): Gymtrainer? {
        val existing = gymtrainerRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gym = request.gym ?: existing.gym,
            trainer = request.trainer ?: existing.trainer,
            startdate = request.startdate ?: existing.startdate,
            enddate = request.enddate ?: existing.enddate,
            status = request.status ?: existing.status,
            position = request.position ?: existing.position,
            note = request.note ?: existing.note,
            date = request.date ?: existing.date,
        )
        return gymtrainerRepository.save(updated)
    }
}