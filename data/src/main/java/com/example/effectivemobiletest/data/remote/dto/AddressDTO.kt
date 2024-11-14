package com.example.effectivemobiletest.data.remote.dto

import com.example.effectivemoviletest.domain.model.AddressModel

data class AddressDTO(
    val house: String,
    val street: String,
    val town: String
)

fun AddressDTO.toAddressModel(): AddressModel{
    return AddressModel(
        house = house,
        street = street,
        town = town
    )
}