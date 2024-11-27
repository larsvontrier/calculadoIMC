package com.pepinho.pmdm.indicemasacorporal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pepinho.pmdm.indicemasacorporal.model.Genero
import com.pepinho.pmdm.indicemasacorporal.model.UserData


/**
 * ViewModel para la actividad IMC
 * EL patrón MVVM se basa en la separación de la lógica de presentación de la lógica de negocio.
 * En este caso, la lógica de negocio es el cálculo del IMC y la lógica de presentación es la
 * representación de los datos en la vista.
 * La vista tiene una referencia al ViewModel y el ViewModel tiene una referencia del modelo.
 */
class IMCViewModel : ViewModel() {

    private val _resultadoIMC = MutableLiveData<Double>()
    // LiveData es una clase que se utiliza para almacenar datos observables.
    // Aquí se almacena el resultado del cálculo del IMC que se recuerda en la vista.
    // MutableLiveData es una subclase de LiveData que permite modificar el valor almacenado, pero
    // exponemos el LiveData para que no se pueda modificar desde fuera y sea de sólo lectura.
    val resultadoIMC: LiveData<Double>
        get() = _resultadoIMC

    fun calcularIMC(peso: Double, altura: Double, genero: Genero, isAdulto: Boolean = true) {
        _resultadoIMC.value = UserData(peso, altura, genero, isAdulto).getIMC()
    }

    // Por si quieres hacer pruebas con el ViewModel para poner la imagen dependiendo del IMC
    fun getResultImage(imc: Double): Int {
        return when {
            imc < 18.5 -> R.drawable.underweight
            imc <= 24.9 -> R.drawable.normal
            imc <= 29.9 -> R.drawable.overweight
            else -> R.drawable.obese
        }
    }
}
