package com.example.procurement.model

import kotlin.test.Test
import kotlin.test.assertEquals

class VendorTest {

    @Test
    fun `should create vendor with valid data`() {
        val vendor = Vendor(
            id = 1,
            name = "PT Sumber Makmur",
            contactEmail = "contact@sumber.com",
            phoneNumber = "08123456789"
        )

        assertEquals(1, vendor.id)
        assertEquals("PT Sumber Makmur", vendor.name)
        assertEquals("contact@sumber.com", vendor.contactEmail)
        assertEquals("08123456789", vendor.phoneNumber)
    }

    @Test
    fun `should allow vendor with empty phone number`() {
        val vendor = Vendor(
            id = 2,
            name = "PT Tanpa Nomor",
            contactEmail = "nohp@vendor.com",
            phoneNumber = ""
        )

        // Tidak ada validasi, jadi tetap diterima
        assertEquals("", vendor.phoneNumber)
        assertEquals("PT Tanpa Nomor", vendor.name)
    }
}
