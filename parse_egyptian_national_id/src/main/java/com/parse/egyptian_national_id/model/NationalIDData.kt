package com.parse.egyptian_national_id.model


data class NationalIDData(
    var error: String? = null,
    var birthCentury: String? = null,
    var birthYear: String? = null,
    var monthOfBirth: String? = null,
    var dayOfBirth: String? = null,
    var birthGovernorate: String? = null,
    var sequenceInComputer: String? = null,
    var gender: String? = null
)