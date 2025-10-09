package com.example.procurement.model

import kotlin.test.Test
import kotlin.test.assertEquals

class UserTest {

    @Test
    fun `should create user with role`() {
        val user = User(
            id = 100,
            name = "Budi",
            email = "budi@company.com",
            role = Role.REQUESTER
        )

        assertEquals(100, user.id)
        assertEquals("Budi", user.name)
        assertEquals("budi@company.com", user.email)
        assertEquals(Role.REQUESTER, user.role)
    }
}
