package com.gowoobro.gymspring.enums.usehealthusage


enum class Type {
    NONE,
    ENTRY,  // 입장
    PT,  // PT수업
    CLASS,  // 그룹수업
;

    companion object {
        fun getDisplayName(value: Type): String {
            return when (value) {
                NONE -> ""
                ENTRY -> "입장"
                PT -> "PT수업"
                CLASS -> "그룹수업"
            }
        }

        fun fromString(value: String): Type? {
            return when (value) {
                "" -> NONE
                "입장" -> ENTRY
                "PT수업" -> PT
                "그룹수업" -> CLASS
                else -> null
            }
        }
    }
}

