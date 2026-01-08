package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.NotificationSetting
import com.gowoobro.gymspring.enums.notificationsetting.NotificationEnabled
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationSettingRepository : JpaRepository<NotificationSetting, Long> {
    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findAll(pageable: Pageable): Page<NotificationSetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    override fun findById(id: Long): java.util.Optional<NotificationSetting>

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByUserId(userId: Long): NotificationSetting?

    @EntityGraph(attributePaths = [
        "user"
    ])
    fun findByEnabled(enabled: NotificationEnabled, pageable: Pageable): Page<NotificationSetting>
}
