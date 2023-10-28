package com.example.roomdatabasekotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ListAdapter() :RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=userList[position]
        holder.itemView.findViewById<TextView>(R.id.name).text=currentItem.name
        holder.itemView.findViewById<TextView>(R.id.course).text=currentItem.course
        holder.itemView.findViewById<TextView>(R.id.father).text=currentItem.fathername
        holder.itemView.findViewById<TextView>(R.id.regno).text=currentItem.reg_no.toString()
        holder.itemView.findViewById<TextView>(R.id.entry).text=currentItem.entrydate.toString()
        holder.itemView.findViewById<TextView>(R.id.dob).text=currentItem.dob.toString()
holder.itemView.findViewById<RelativeLayout>(R.id.rowLayout).setOnClickListener {
    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
    holder.itemView.findNavController().navigate(action)

}
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

    }

