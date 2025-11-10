package com.gowoobro.gymspring.service

import com.gowoobro.gymspring.entity.Term
import com.gowoobro.gymspring.entity.TermCreateRequest
import com.gowoobro.gymspring.entity.TermUpdateRequest
import com.gowoobro.gymspring.repository.TermRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime



@Service
@Transactional
class TermService(private val termRepository: TermRepository) {

    fun findAll(page: Int = 0, pageSize: Int = 10): Page<Term> {
        val pageable: Pageable = PageRequest.of(page, pageSize)
        return termRepository.findAll(pageable)
    }

    fun findById(id: Long): Term? {
        return termRepository.findById(id).orElse(null)
    }

    fun count(): Long {
        return termRepository.count()
    }


    fun findByGym(gym: Long): List<Term> {
        return termRepository.findByGym(gym)
    }

    fun findByDaytype(daytype: Long): List<Term> {
        return termRepository.findByDaytype(daytype)
    }

    fun findByName(name: String): List<Term> {
        return termRepository.findByName(name)
    }

    fun findByTerm(term: Int): List<Term> {
        return termRepository.findByTerm(term)
    }

    fun findByDate(date: LocalDateTime): List<Term> {
        return termRepository.findByDate(date)
    }


    fun create(request: TermCreateRequest): Term {
        val entity = Term(
            gym = request.gym,
            daytype = request.daytype,
            name = request.name,
            term = request.term,
            date = request.date,
        )
        return termRepository.save(entity)
    }

    fun createBatch(requests: List<TermCreateRequest>): List<Term> {
        val entities = requests.map { request ->
            Term(
                gym = request.gym,
                daytype = request.daytype,
                name = request.name,
                term = request.term,
                date = request.date,
            )
        }
        return termRepository.saveAll(entities)
    }

    fun update(request: TermUpdateRequest): Term? {
        val existing = termRepository.findById(request.id).orElse(null) ?: return null

        

        val updated = existing.copy(
            gym = request.gym,
            daytype = request.daytype,
            name = request.name,
            term = request.term,
            date = request.date,
        )
        return termRepository.save(updated)
    }

    fun delete(entity: Term): Boolean {
        return try {
            termRepository.delete(entity)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteById(id: Long): Boolean {
        return try {
            termRepository.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteBatch(entities: List<Term>): Boolean {
        return try {
            termRepository.deleteAll(entities)
            true
        } catch (e: Exception) {
            false
        }
    }
}