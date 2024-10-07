import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sinisterbuster.R
import com.example.sinisterbuster.model.Paciente

class PacienteAdapter(private val pacientes: MutableList<Paciente>) :
    RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    class PacienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeTextView: TextView = view.findViewById(R.id.nomeTextView)
        val idadeTextView: TextView = view.findViewById(R.id.idadeTextView)
        val cidadeTextView: TextView = view.findViewById(R.id.cidadeTextView)
        val telefoneTextView: TextView = view.findViewById(R.id.telefoneTextView)
        val btnRemover: Button = view.findViewById(R.id.btnRemover) // Botão de remover
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_paciente, parent, false)
        return PacienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = pacientes[position]
        holder.nomeTextView.text = paciente.nome
        holder.idadeTextView.text = "${paciente.idade} anos"
        holder.cidadeTextView.text = paciente.cidade
        holder.telefoneTextView.text = paciente.telefone

        // Configurar o botão para remover o paciente
        holder.btnRemover.setOnClickListener {
            // Remove o paciente da lista
            pacientes.removeAt(position)
            // Notifica o adapter sobre a mudança
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, pacientes.size)
        }
    }

    override fun getItemCount() = pacientes.size
}
