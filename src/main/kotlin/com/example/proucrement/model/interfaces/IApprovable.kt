package com.example.procurement.model.interfaces

interface IApprovable {
    fun approve(): Boolean
    fun reject(reason: String)
}
