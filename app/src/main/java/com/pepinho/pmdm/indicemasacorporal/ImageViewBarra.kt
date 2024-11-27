package com.pepinho.pmdm.indicemasacorporal

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ImageViewBarra @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        const val MAX_IMC = 50f
        // Al principio iba a poner por rangos de la imagen para ajustar mejor.
        // pero prefiero centrarme en lo importante.
        const val MAX_PORCENTAJE_BAJO: Float = 22.5f
        const val MAX_PORCENTAJE_NORMAL: Float = 37.5f
        const val MAX_PORCENTAJE_SOBREPESO: Float = 74.0f
    }

    // Posición de la línea.
    private var posicionLinea: Float = -1.0f // valor inicial negativo
        set(value) {
            field = value
            invalidate() // Invalida la vista para que se vuelva a dibujar
        }

    /*
    El objeto Paint es un objeto que contiene información sobre cómo dibujar gráficos,
    como el color, el grosor de la línea, etc.
    Lo vamos a utilizar para cambiar el color de la línea y el grosor de la misma.
     */
    private val paint = Paint().apply {
        color = Color.RED // Pon el color que desees
        strokeWidth = 5f // Grosor de la línea
    }

    // Cuando se dibuja la vista, se invoca automáticamente a este método.
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (posicionLinea > 0.0f)
            canvas.drawLine(posicionLinea, 0f, posicionLinea, height.toFloat(), paint)
    }

    // Por si quieres cambiar el color desde fuera
    fun setColor(color: Int) {
        paint.color = color
        invalidate()
    }

    // O el ancho... ,-)
    fun setStrokeWidth(width: Float) {
        paint.strokeWidth = width
        invalidate()
    }

    // Le paso el IMC y dibuja una línea en la posición que le corresponde.
    fun drawLine(imc: Double) {
        val imcF = imc.toFloat()
        posicionLinea = when {
            imcF <= 0 -> 0f // Negativo lo pongo en el 0
            imcF >= MAX_IMC -> width.toFloat() // Mayor que el IMC máximo en el borde
            else -> width.toFloat() * (imcF / MAX_IMC) // Dónde toque, aproximadamente
        }
        invalidate()
    }
}
