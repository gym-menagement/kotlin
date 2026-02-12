package com.gowoobro.gymspring.entity

import com.gowoobro.gymspring.enums.user.Role

data class UserProfileDto(
    val role: Role,        // MEMBER, TRAINER, GYM_ADMIN
    val gymId: Long?,      // 헬스장 ID (플랫폼 관리자는 null 가능)
    val gymName: String?,  // 헬스장 이름
    val contextId: Long?   // 관련 테이블 ID (membership_id, gymtrainer_id, gym_id)
)
