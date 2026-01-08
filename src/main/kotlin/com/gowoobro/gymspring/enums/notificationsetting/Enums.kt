package com.gowoobro.gymspring.enums.notificationsetting


enum class Enabled {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Enabled): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Enabled? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Membershipexpiry {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Membershipexpiry): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Membershipexpiry? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Membershipnear {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Membershipnear): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Membershipnear? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Attendanceenc {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Attendanceenc): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Attendanceenc? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Gymannounce {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Gymannounce): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Gymannounce? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Systemnotice {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Systemnotice): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Systemnotice? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Paymentconfirm {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Paymentconfirm): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Paymentconfirm? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Pauseexpiry {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Pauseexpiry): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Pauseexpiry? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Weeklygoal {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Weeklygoal): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Weeklygoal? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Personalrecord {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Personalrecord): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Personalrecord? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}


enum class Quietenabled {
    NONE,
    ENABLED,  // 활성화
    DISABLED,  // 비활성화
;

    companion object {
        fun getDisplayName(value: Quietenabled): String {
            return when (value) {
                NONE -> ""
                ENABLED -> "활성화"
                DISABLED -> "비활성화"
            }
        }

        fun fromString(value: String): Quietenabled? {
            return when (value) {
                "" -> NONE
                "활성화" -> ENABLED
                "비활성화" -> DISABLED
                else -> null
            }
        }
    }
}

