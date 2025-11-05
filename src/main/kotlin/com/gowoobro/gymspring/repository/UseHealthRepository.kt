package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Usehealth
import com.gowoobro.gymspring.entity.UsehealthCreateRequest
import com.gowoobro.gymspring.entity.UsehealthUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UsehealthRepository : JpaRepository<Usehealth, Long> {
    override fun findAll(pageable: Pageable): Page<Usehealth>

    override fun findById(id: String): List<Usehealth>

    override fun findByOrder(order: String): List<Usehealth>

    override fun findByHealth(health: String): List<Usehealth>

    override fun findByUser(user: String): List<Usehealth>

    override fun findByRocker(rocker: String): List<Usehealth>

    override fun findByTerm(term: String): List<Usehealth>

    override fun findByDiscount(discount: String): List<Usehealth>

    override fun findByStartday(startday: String): List<Usehealth>

    override fun findByEndday(endday: String): List<Usehealth>

    override fun findByDate(date: String): List<Usehealth>
}