package com.india.kotlinlistdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.india.kotlinlistdemo.databinding.StudioSingleListBinding
import com.india.kotlinlistdemo.model.Data

class StudioAdapter(private var items: List<Data>) : androidx.recyclerview.widget.RecyclerView.Adapter<StudioAdapter.ViewHolder>() {
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StudioSingleListBinding.inflate(inflater)
        println("Size : "+items.size)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.viewModel = items[position]

        holder.binding.rlContainer.setOnClickListener {
            listener.onStudioClick(it, position)
        }

        holder.binding.executePendingBindings()

    }


    inner class ViewHolder(binding: StudioSingleListBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        var binding: StudioSingleListBinding = binding
    }


    interface OnItemClickListener {
        fun onStudioClick(view: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}