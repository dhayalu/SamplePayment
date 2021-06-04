package com.example.samplepayment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplepayment.R
import com.example.samplepayment.model.Contact

class ContactAdapter(var listItems: ArrayList<Contact>,var i: Int) : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtName = view.findViewById<TextView>(R.id.txtName)
        var txtNumber = view.findViewById<TextView>(R.id.txtNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater =
        if(i==1)
            LayoutInflater.from(parent.context).inflate(R.layout.cell_contact, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.gridcell_contact, parent, false)

        return MyViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {

        Log.d("Size", "" + listItems.size)

        return listItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val contact : Contact = listItems[position]
        holder.txtName.text = contact.name
        holder.txtNumber.text = contact.number
    }

    fun filterList(filteredData: ArrayList < Contact > ) {
        Log.e("list", filteredData.toString())
        Log.e("list", filteredData.size.toString())

        this.listItems = filteredData
        notifyDataSetChanged()
    }

}