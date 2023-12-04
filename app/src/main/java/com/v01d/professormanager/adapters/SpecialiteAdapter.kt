
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.v01d.professormanager.R
import com.v01d.professormanager.entities.Specialite

class SpecialiteAdapter:RecyclerView.Adapter<SpecialiteAdapter.ServiceItemViewHolder>() {

    var data = listOf<Specialite>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    var onDeleteClickListener: ((Specialite) -> Unit)? = null
    var onUpdateClickListener: ((Specialite) -> Unit)? = null

    override fun onBindViewHolder(holder: ServiceItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        // Set click listener for the card
        holder.deleteSpecialite.setOnClickListener {
            onDeleteClickListener?.invoke(item)
        }

        holder.updateSpecialite.setOnClickListener {
            onUpdateClickListener?.invoke(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceItemViewHolder =
        ServiceItemViewHolder.inflateFrom(parent)

    override fun getItemCount() = data.size

    class ServiceItemViewHolder(val rootView: CardView):RecyclerView.ViewHolder(rootView){

        val serviceName = rootView.findViewById<TextView>(R.id.nameSpecialiteText)
        val deleteSpecialite = rootView.findViewById<Button>(R.id.deleteSpecialiteBtn)
        val updateSpecialite = rootView.findViewById<Button>(R.id.updateSpecialiteBtn)
        companion object {
            fun inflateFrom(parent: ViewGroup):ServiceItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.specialite_item,parent,false) as CardView
                return ServiceItemViewHolder(view)
            }
        }
        fun bind(item:Specialite){
            serviceName.text = item.name

        }
    }
}

