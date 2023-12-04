package com.v01d.professormanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.v01d.professormanager.R
import com.v01d.professormanager.entities.Professor
import com.v01d.professormanager.entities.Specialite

class ProfessorAdapter:RecyclerView.Adapter<ProfessorAdapter.ServiceItemViewHolder>() {

    var data = listOf<Professor>()
        set(value){
            field=value
            notifyDataSetChanged()
        }

    var onDeleteClickListener: ((Professor) -> Unit)? = null
    var onUpdateClickListener: ((Professor) -> Unit)? = null

    override fun onBindViewHolder(holder: ServiceItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        // Set click listener for the card
        holder.deleteButton.setOnClickListener {
            onDeleteClickListener?.invoke(item)
        }

        holder.updateButton.setOnClickListener {
            onUpdateClickListener?.invoke(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceItemViewHolder =
        ServiceItemViewHolder.inflateFrom(parent)

    override fun getItemCount() = data.size

    class ServiceItemViewHolder(val rootView: CardView):RecyclerView.ViewHolder(rootView){

        val firstName = rootView.findViewById<TextView>(R.id.first_name_text)
        val lastName = rootView.findViewById<TextView>(R.id.last_name_text)
        val specialiteName = rootView.findViewById<TextView>(R.id.specialite_text)
        val deleteButton = rootView.findViewById<Button>(R.id.deleteProfessorBtn)
        val updateButton = rootView.findViewById<Button>(R.id.updateProfessorBtn)
        companion object {
            fun inflateFrom(parent: ViewGroup):ServiceItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.professor_item,parent,false) as CardView
                return ServiceItemViewHolder(view)
            }
        }
        fun bind(item:Professor){
            firstName.text = item.first_name
            lastName.text = item.last_name
            specialiteName.text = item.specialite.name
        }
    }
}

