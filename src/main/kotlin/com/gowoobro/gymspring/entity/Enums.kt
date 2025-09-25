package com.gowoobro.gymspring.entity

enum class Type {
    SYSTEM,
    USER,
    ADMIN,
    NOTIFICATION,
    WARNING,
    ERROR,
    INFO,
    DEBUG
}

enum class Status {
    ACTIVE,
    INACTIVE,
    PENDING,
    COMPLETED,
    CANCELLED,
    EXPIRED
}

enum class Policy {
    ALLOW,
    DENY,
    BLOCK,
    WARN
}

enum class Use {
    YES,
    NO,
    ENABLED,
    DISABLED
}

enum class Level {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED,
    PREMIUM,
    VIP
}

enum class UserRole {
    ADMIN,
    MANAGER,
    TRAINER,
    MEMBER,
    GUEST
}