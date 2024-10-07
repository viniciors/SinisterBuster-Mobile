package com.example.sinisterbuster

import PacienteAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sinisterbuster.model.Paciente

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdicionarPaciente: Button
    private val pacientes = mutableListOf<Paciente>()
    private lateinit var adapter: PacienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewPacientes)
        btnAdicionarPaciente = findViewById(R.id.btnAdicionarPaciente)

        pacientes.add(Paciente("Enzo Fenili", 20, "Osasco", "11940028922"))

        // Configurar RecyclerView e Adapter
        adapter = PacienteAdapter(pacientes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Evento do botão para adicionar paciente
        btnAdicionarPaciente.setOnClickListener {
            adicionarPaciente()
        }
    }

    private fun adicionarPaciente() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_adicionar_paciente, null)
        val editNome = dialogLayout.findViewById<EditText>(R.id.editNome)
        val editIdade = dialogLayout.findViewById<EditText>(R.id.editIdade)
        val editCidade = dialogLayout.findViewById<EditText>(R.id.editCidade)
        val editTelefone = dialogLayout.findViewById<EditText>(R.id.editTelefone)

        with(builder) {
            setTitle("Adicionar Paciente")
            setView(dialogLayout)
            setPositiveButton("Adicionar") { _, _ ->
                val nome = editNome.text.toString()
                val idade = editIdade.text.toString().toIntOrNull() ?: 0
                val cidade = editCidade.text.toString()
                val telefone = editTelefone.text.toString()

                // Adiciona o novo paciente à lista e atualiza o RecyclerView
                pacientes.add(Paciente(nome, idade, cidade, telefone))
                adapter.notifyDataSetChanged()
            }
            setNegativeButton("Cancelar", null)
            show()
        }
    }
}
