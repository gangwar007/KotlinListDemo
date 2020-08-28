package com.india.kotlinlistdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.india.kotlinlistdemo.databinding.StudioSingleListBinding
import com.india.kotlinlistdemo.model.Data

class StudioAdapter(private var items: List<Data>) : androidx.recyclerview.widget.RecyclerView.Adapter<StudioAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StudioSingleListBinding.inflate(inflater)
        print("Size : "+items.size)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.viewModel = items[position]

        holder.binding.executePendingBindings()

    }


    inner class ViewHolder(binding: StudioSingleListBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        var binding: StudioSingleListBinding = binding

    }

}