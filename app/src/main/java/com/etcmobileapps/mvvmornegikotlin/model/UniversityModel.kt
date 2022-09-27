package com.etcmobileapps.mvvmornegikotlin.model


typealias UniversityModel = ArrayList<UniversityModelElement>

data class UniversityModelElement (
    val domains: List<String>? = null,
    val alphaTwoCode: AlphaTwoCode? = null,
    val country: Country? = null,
    val webPages: List<String>? = null,
    val name: String? = null,
    val stateProvince: Any? = null
)

enum class AlphaTwoCode {
    Tr
}

enum class Country {
    Turkey
}
