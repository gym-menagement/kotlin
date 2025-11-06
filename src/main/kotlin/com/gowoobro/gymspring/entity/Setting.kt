package com.gowoobro.gymspring.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "setting_tb")
data class Setting(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "se_id")
    val id: Long = 0,
    @Column(name = "se_category")
    val category: String = "",
    @Column(name = "se_name")
    val name: String = "",
    @Column(name = "se_key")
    val key: String = "",
    @Column(name = "se_value")
    val value: String = "",
    @Column(name = "se_remark")
    val remark: String = "",
    @Column(name = "se_type")
    @Enumerated(EnumType.ORDINAL)
    val type: Type,
    @Column(name = "se_data")
    val data: String = "",
    @Column(name = "se_order")
    val order: Int = 0,
    @Column(name = "se_date")
    val date: LocalDateTime? = null,
)

data class SettingCreateRequest(
    val category: String = "",
    val name: String = "",
    val key: String = "",
    val value: String = "",
    val remark: String = "",
    val type: Type,
    val data: String = "",
    val order: Int = 0,
    val date: LocalDateTime? = null,
)

data class SettingUpdateRequest(
    val id: Long = 0,
    val category: String = "",
    val name: String = "",
    val key: String = "",
    val value: String = "",
    val remark: String = "",
    val type: Type,
    val data: String = "",
    val order: Int = 0,
    val date: LocalDateTime? = null,
)