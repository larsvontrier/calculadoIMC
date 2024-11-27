package com.pepinho.pmdm.indicemasacorporal.model

data class UserData(
    val peso: Double,
    val altura: Double,
    val genero: Genero,
    val isAdulto: Boolean = true
) {

//    fun getIMC(): Double {
//        if (altura == 0.0) return 0.0
//        val metros = altura / 100.0
//        return peso / (metros * metros)
//    }

    // Más Kotlin ;-)
    fun getIMC(): Double {
        return if (altura == 0.0) 0.0 else peso / ((altura / 100.0).let { it * it })
    }
}