package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Ptreservation
import com.gowoobro.gymspring.entity.PtreservationCreateRequest
import com.gowoobro.gymspring.entity.PtreservationUpdateRequest
import com.gowoobro.gymspring.repository.PtreservationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

import com.gowoobro.gymspring.enums.ptreservation.Status


@Service
@Transactional
class PtreservationService(private val ptreservationRepository: PtreservationRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Ptreservation> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return ptreservationRepository.findAll(pageable)
    }

    fun findById(id: Long): Ptreservation? {
        return ptreservationRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return ptreservationRepository.count()
    }


    fun findByTrainer(trainer: Long): List<Ptreservation> {
        return ptreservationRepository.findByTrainer(trainer)
    }

    fun findByMember(member: Long): List<Ptreservation> {
        return ptreservationRepository.findByMember(member)
    }

    fun findByGym(gym: Long): List<Ptreservation> {
        return ptreservationRepository.findByGym(gym)
    }

    fun findByReservationdate(reservationdate: LocalDateTime): List<Ptreservation> {
        return ptreservationRepository.findByReservationdate(reservationdate)
    }

    fun findByStarttime(starttime: String): List<Ptreservation> {
        return ptreservationRepository.findByStarttime(starttime)
    }

    fun findByEndtime(endtime: String): List<Ptreservation> {
        return ptreservationRepository.findByEndtime(endtime)
    }

    fun findByDuration(duration: Int): List<Ptreservation> {
        return ptreservationRepository.findByDuration(duration)
    }

    fun findByStatus(status: Status): List<Ptreservation> {
        return ptreservationRepository.findByStatus(status)
    }

    fun findByNote(note: String): List<Ptreservation> {
        return ptreservationRepository.findByNote(note)
    }

    fun findByCancelreason(cancelreason: String): List<Ptreservation> {
        return ptreservationRepository.findByCancelreason(cancelreason)
    }

    fun findByCreateddate(createddate: LocalDateTime): List<Ptreservation> {
        return ptreservationRepository.findByCreateddate(createddate)
    }

    fun findByUpdateddate(updateddate: LocalDateTime): List<Ptreservation> {
        return ptreservationRepository.findByUpdateddate(updateddate)
    }


    fun create(request: PtreservationCreateRequest): Ptreservation {
        val entity = Ptreservation(
            trainer = request.trainer,
            member = request.member,
            gym = request.gym,
            reservationdate = request.reservationdate,
            starttime = request.starttime,
            endtime = request.endtime,
            duration = request.duration,
            status = request.status,
            note = request.note,
            cancelreason = request.cancelreason,
            createddate = request.createddate,
            updateddate = request.updateddate,
        )
        return ptreservationRepository.save(entity)
    }

    fun createBatch(requests: List<PtreservationCreateRequest>): List<Ptreservation> {
        val entities = requests.map { request ->
            Ptreservation(
                trainer = request.trainer,
                member = request.member,
                gym = request.gym,
                reservationdate = request.reservationdate,
                starttime = request.starttime,
                endtime = request.endtime,
                duration = request.duration,
                status = request.status,
                note = request.note,
                cancelreason = request.cancelreason,
                createddate = request.createddate,
                updateddate = request.updateddate,
            )
        }
        return ptreservationRepository.saveAll(entities)
    }

    fun update(request: PtreservationUpdateRequest): Ptreservation? {
        val existing = ptreservationRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            trainer = request.trainer,
            member = request.member,
            gym = request.gym,
            reservationdate = request.reservationdate,
            starttime = request.starttime,
            endtime = request.endtime,
            duration = request.duration,
            status = request.status,
            note = request.note,
            cancelreason = request.cancelreason,
            createddate = request.createddate,
            updateddate = request.updateddate,
        )
        return ptreservationRepository.save(updated)
    }

    fun delete(entity: Ptreservation): Boolean {
        return try {
            ptreservationRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            ptreservationRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Ptreservation>): Boolean {
        return try {
            ptreservationRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}