package com.pepinho.pmdm.indicemasacorporal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pepinho.pmdm.indicemasacorporal.databinding.ActivityMainBinding
import com.pepinho.pmdm.indicemasacorporal.model.Genero
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // instanca de objeto Binding con acceso  las vista en el layout con nombre activity_main
    private lateinit var binding: ActivityMainBinding
    // Podría crearse una instancia de la clase IMCViewModel
    // private lateinit var viewModel: IMCViewModel
    // Pero se puede hacer uso de la función viewModels() que se encarga de crear una instancia del ViewModel
    // y asociarla a la actividad. by viewModels() es una propiedad delegada que se encarga de crear una instancia
    // del ViewModel y asociarla a la actividad.:
    // viewModel = ViewModelProvider(this).get(IMCViewModel::class.java)
    private val viewModel: IMCViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // "infla" el archivo layout XML y devuelve una instancia del objeto binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Asignamos el contenido de la vista a la actividad, que es el raíz del layout
        setContentView(binding.root)

        // Equivalente a: viewModel = ViewModelProvider(this).get(IMCViewModel::class.java)
//        viewModel = ViewModelProvider(this)[IMCViewModel::class.java]

        // Cálculo del IMC
        binding.btCalcular.setOnClickListener { getIMC() }

        viewModel.resultadoIMC.observe(this) { imc ->
            showIMC(imc)
            binding.ivIMC.drawLine(imc)
        }

        // Asignamos un listener a los EditText al escribir... (al enter que oculte el teclado)
        binding.etPeso.setOnKeyListener { view, keyCode, _ ->
            ocultarTecladoEnter(view, keyCode)
        }
        binding.etAltura.setOnKeyListener { view, keyCode, _ ->
            ocultarTecladoEnter(view, keyCode)
        }
    }

    /**
     * Calculates the tip based on the user input.
     */
    private fun getIMC() {
        // Obtenemos el valor decimal del peso y la altura escritos en el campo EditText
        val peso = getDoubleFromEditText(binding.etPeso)
        val altura = getDoubleFromEditText(binding.etAltura)
//        Log.d("MainActivity", "$peso - $altura")
//
        // Obtenemos el sexo a partir del radio button seleccionado
        val sexo: Genero = when (binding.rgSexo.checkedRadioButtonId) {
            R.id.rbHombre -> Genero.HOMBRE
            R.id.rbMujer -> Genero.MULLER
            else -> Genero.SIN_DEFINIR
        }
        viewModel.calcularIMC(peso, altura, sexo, binding.swAdulto.isChecked)
    }

    private fun getDoubleFromEditText(editText: EditText): Double {
        return editText.text.toString().toDoubleOrNull() ?: 0.0
    }

    /**
     * Formatear el número para que aparezca con dos decimales
     * Por ejemplo: "Índice de masa corporal: 20.23"
     */
    private fun showIMC(imc: Double) {
        val imcFormateado = NumberFormat.getNumberInstance().apply {
            maximumFractionDigits = 2
        }.format(imc)
        binding.txtResultado.text = getString(R.string.txtIndice, imcFormateado)
//        binding.txtResultado.text = getString(R.string.txtIndice, String.format(Locale.ROOT,"%.2f", imc))
    }

    /**
     * Oculto el teclado al pulsar enter
     */
    private fun ocultarTecladoEnter(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Ocultar teclado
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}