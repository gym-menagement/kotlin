package com.gowoobro.gymspring.repository

import com.gowoobro.gymspring.entity.Notificationhistory
import com.gowoobro.gymspring.entity.Notificationsetting
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

/**
 * 알림 Repository 확장 인터페이스
 * 자동 생성된 Repository에 추가 메서드 정의
 */
interface NotificationhistoryRepositoryCustom {
    fun findByReceiverOrderBySentdateDesc(receiverId: Long, pageable: Pageable): Page<Notificationhistory>
    fun findByGymOrderBySentdateDesc(gymId: Long, pageable: Pageable): Page<Notificationhistory>
    fun findByTypeOrderBySentdateDesc(type: Int, pageable: Pageable): Page<Notificationhistory>
    fun findByReceiverAndTypeOrderBySentdateDesc(receiverId: Long, type: Int, pageable: Pageable): Page<Notificationhistory>
}

interface NotificationsettingRepositoryCustom {
    fun findByUser(userId: Long): Optional<Notificationsetting>
}
