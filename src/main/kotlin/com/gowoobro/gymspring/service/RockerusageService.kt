package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Rockerusage
import com.gowoobro.gymspring.entity.RockerusageCreateRequest
import com.gowoobro.gymspring.entity.RockerusageUpdateRequest
import com.gowoobro.gymspring.repository.RockerusageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.math.BigDecimal
import com.gowoobro.gymspring.enums.rockerusage.Status


@Service
@Transactional
class RockerusageService(private val rockerusageRepository: RockerusageRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Rockerusage> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return rockerusageRepository.findAll(pageable)
    }

    fun findById(id: Long): Rockerusage? {
        return rockerusageRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return rockerusageRepository.count()
    }


    fun findByGym(gym: Long): List<Rockerusage> {
        return rockerusageRepository.findBygymId(gym)
    }

    fun findByRocker(rocker: Long): List<Rockerusage> {
        return rockerusageRepository.findByrockerId(rocker)
    }

    fun findByUser(memberuser: Long): List<Rockerusage> {
        return rockerusageRepository.findByuserId(memberuser)
    }

    fun findByUsehealth(usehealth: Long): List<Rockerusage> {
        return rockerusageRepository.findByusehealthId(usehealth)
    }

    fun findByStartdate(startdate: LocalDateTime): List<Rockerusage> {
        return rockerusageRepository.findByStartdate(startdate)
    }

    fun findByEnddate(enddate: LocalDateTime): List<Rockerusage> {
        return rockerusageRepository.findByEnddate(enddate)
    }

    fun findByStatus(status: Status): List<Rockerusage> {
        return rockerusageRepository.findByStatus(status)
    }

    fun findByDeposit(deposit: BigDecimal): List<Rockerusage> {
        return rockerusageRepository.findByDeposit(deposit)
    }

    fun findByMonthlyfee(monthlyfee: BigDecimal): List<Rockerusage> {
        return rockerusageRepository.findByMonthlyfee(monthlyfee)
    }

    fun findByNote(note: String): List<Rockerusage> {
        return rockerusageRepository.findByNote(note)
    }

    fun findByAssignedby(assignedbyuser: Long): List<Rockerusage> {
        return rockerusageRepository.findByassignedbyId(assignedbyuser)
    }

    fun findByAssigneddate(assigneddate: LocalDateTime): List<Rockerusage> {
        return rockerusageRepository.findByAssigneddate(assigneddate)
    }

    fun findByDate(date: LocalDateTime): List<Rockerusage> {
        return rockerusageRepository.findByDate(date)
    }


    fun create(request: RockerusageCreateRequest): Rockerusage {
        val entity = Rockerusage(
            gymId = request.gym,
            rockerId = request.rocker,
            userId = request.user,
            usehealthId = request.usehealth,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            deposit = request.deposit,
            monthlyfee = request.monthlyfee,
            note = request.note,
            assignedbyId = request.assignedby,
            assigneddate = request.assigneddate,
            date = request.date,
        )
        return rockerusageRepository.save(entity)
    }

    fun createBatch(requests: List<RockerusageCreateRequest>): List<Rockerusage> {
        val entities = requests.map { request ->
            Rockerusage(
                gymId = request.gym,
                rockerId = request.rocker,
                userId = request.user,
                usehealthId = request.usehealth,
                startdate = request.startdate,
                enddate = request.enddate,
                status = request.status,
                deposit = request.deposit,
                monthlyfee = request.monthlyfee,
                note = request.note,
                assignedbyId = request.assignedby,
                assigneddate = request.assigneddate,
                date = request.date,
            )
        }
        return rockerusageRepository.saveAll(entities)
    }

    fun update(request: RockerusageUpdateRequest): Rockerusage? {
        val existing = rockerusageRepository.findById(request.id).orElse(null) ?: return null

        val updated = existing.copy(
            gymId = request.gym,
            rockerId = request.rocker,
            userId = request.user,
            usehealthId = request.usehealth,
            startdate = request.startdate,
            enddate = request.enddate,
            status = request.status,
            deposit = request.deposit,
            monthlyfee = request.monthlyfee,
            note = request.note,
            assignedbyId = request.assignedby,
            assigneddate = request.assigneddate,
            date = request.date,
        )
        return rockerusageRepository.save(updated)
    }

    fun delete(entity: Rockerusage): Boolean {
        return try {
            rockerusageRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            rockerusageRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Rockerusage>): Boolean {
        return try {
            rockerusageRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}