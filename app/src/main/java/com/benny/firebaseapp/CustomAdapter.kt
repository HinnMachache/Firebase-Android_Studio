package com.benny.firebaseapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val usersList: List<User>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        holder.txtAge.text = "${user.age} years old"
        holder.txtEmail.text = "${user.email}"
        holder.txtName.text = user.name
        holder.itemView.setOnClickListener {
            //TODO
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtAge: TextView = itemView.findViewById(R.id.txtAge)
        val txtEmail: TextView = itemView.findViewById(R.id.txtEmail)

    }
}