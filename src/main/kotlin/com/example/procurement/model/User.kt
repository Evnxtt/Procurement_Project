package com.example.procurement.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val role: Role
)

@Serializable
enum class Role {
    REQUESTER,
    APPROVER,
    PROCUREMENT_OFFICER,
    FINANCE,
    ADMIN
}
