package com.example.contributorintruductionapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ContributorListAdapter(private val contributorList: List<ContributorData>) : RecyclerView.Adapter<ContributorListAdapter.ViewHolder>() {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(data: ContributorData)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contributorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count: ${contributorList.size}")
        val contributor = contributorList[position]
        val listener = object : OnItemClickListener {
            override fun onItemClick(data: ContributorData) {
            }
        }
        holder.apply {
            bind(listener, contributor)
            itemView.tag = contributor
        }
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById<ImageView>(R.id.contributerImg)
        var tvName: TextView = itemView.findViewById<TextView>(R.id.tvName)
        fun bind(listener: OnItemClickListener, contributor: ContributorData) {
            tvName.text = contributor.login
            Picasso.get().load(contributor.avatar_url).into(imageView)
            itemView.setOnClickListener {
                listener.onItemClick(contributor)
            }

        }
    }



}