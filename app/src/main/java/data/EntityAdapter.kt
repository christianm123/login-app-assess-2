package data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginappassess2.DetailsActivity
import com.example.loginappassess2.R

class EntityAdapter(private val context: Context, private val entityList: List<Entity>) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    inner class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val typeTextView: TextView = view.findViewById(R.id.typeTextView)
        val ownerTextView: TextView = view.findViewById(R.id.ownerTextView)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                val entity = entityList[position]

                // Navigate to DetailsActivity with the selected entity's information
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("ENTITY_ID", entity.id)
                intent.putExtra("ENTITY_NAME", entity.name)
                intent.putExtra("ENTITY_TYPE", entity.type)
                intent.putExtra("ENTITY_OWNER", entity.owner)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.entity_item, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entityList[position]
        holder.nameTextView.text = entity.name
        holder.typeTextView.text = entity.type
        holder.ownerTextView.text = entity.owner
    }

    override fun getItemCount(): Int {
        return entityList.size
    }
}
