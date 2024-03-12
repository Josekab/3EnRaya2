package cr.ac.una.myapplication

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class MainActivity : AppCompatActivity() {
    lateinit var jugar :Button
    lateinit var pos1: ImageButton
    lateinit var pos2: ImageButton
    lateinit var pos3: ImageButton
    lateinit var pos4: ImageButton
    lateinit var pos5: ImageButton
    lateinit var pos6: ImageButton
    lateinit var pos7: ImageButton
    lateinit var pos8: ImageButton
    lateinit var pos9: ImageButton
    var juegoService = JuegoService()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jugar = findViewById(R.id.jugar)

        pos1 = findViewById(R.id.pos1)
        pos2 = findViewById(R.id.pos2)
        pos3 = findViewById(R.id.pos3)
        pos4 = findViewById(R.id.pos4)
        pos5 = findViewById(R.id.pos5)
        pos6 = findViewById(R.id.pos6)
        pos7 = findViewById(R.id.pos7)
        pos8 = findViewById(R.id.pos8)
        pos9 = findViewById(R.id.pos9)

        jugar.setOnClickListener() {
            //enableDisableButton()
            mostrarDialogoConfirmacion()
        }
        pos1.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(0, 0))
        }
        pos2.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(0, 1))
        }
        pos3.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(0, 2))
        }
        pos4.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(1, 0))
        }
        pos5.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(1, 1))
        }
        pos6.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(1, 2))
        }
        pos7.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(2, 0))
        }
        pos8.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(2, 1))
        }
        pos9.setOnClickListener(){
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(2, 2))
        }
        enableDisableButton()
    }


    private fun mostrarDialogoConfirmacion() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("¿Desea iniciar el juego?")
            .setTitle("Confirmación")
            .setPositiveButton("Sí") { dialog, which ->
                iniciarJuego()
            }
            .setNegativeButton("No") { dialog, which ->
                // pendiente
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun iniciarJuego() {
        juegoService.inicializar()
        pos1.setBackgroundResource(R.drawable.limpio1)
        pos2.setBackgroundResource(R.drawable.limpio1)
        pos3.setBackgroundResource(R.drawable.limpio1)
        pos4.setBackgroundResource(R.drawable.limpio1)
        pos5.setBackgroundResource(R.drawable.limpio1)
        pos6.setBackgroundResource(R.drawable.limpio1)
        pos7.setBackgroundResource(R.drawable.limpio1)
        pos8.setBackgroundResource(R.drawable.limpio1)
        pos9.setBackgroundResource(R.drawable.limpio1)
        enableDisableButton()
        jugar.isEnabled = false
    }

    private fun enableDisableButton() {
        pos1.isEnabled = true
        pos2.isEnabled = true
        pos3.isEnabled = true
        pos4.isEnabled = true
        pos5.isEnabled = true
        pos6.isEnabled = true
        pos7.isEnabled = true
        pos8.isEnabled = true
        pos9.isEnabled = true
    }


    private fun seleccionafigura(imageButton: ImageButton){
        if (juegoService.figura == 'O')
            imageButton.setBackgroundResource(R.drawable.circulo)
        else
            imageButton.setBackgroundResource(R.drawable.cruz)
        imageButton.isEnabled=false
    }
    private fun muestraDialogo(mensaje: Pair<String, String>){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("Aviso")
            .setTitle(mensaje.first)
            .setPositiveButton("¡Entendido!") { dialog, which ->
                // Do something.
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()

        if (mensaje.first.contains("gana") || mensaje.first.contains("Empate")) {
            pintarGanador(mensaje.second)
            jugar.isEnabled = true
        }
    }

    private fun pintarGanador(linea: String) {
        val buttons = when (linea) {
            "row0" -> listOf(pos1, pos2, pos3)
            "row1" -> listOf(pos4, pos5, pos6)
            "row2" -> listOf(pos7, pos8, pos9)
            "col0" -> listOf(pos1, pos4, pos7)
            "col1" -> listOf(pos2, pos5, pos8)
            "col2" -> listOf(pos3, pos6, pos9)
            "diag0" -> listOf(pos1, pos5, pos9)
            "diag1" -> listOf(pos3, pos5, pos7)
            else -> listOf()
        }

        for (button in buttons) {
            if (juegoService.figura == 'O')
                button.setBackgroundResource(R.drawable.circulo_rojo)
            else
                button.setBackgroundResource(R.drawable.cruz_roja)
        }
    }
}
