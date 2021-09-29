package com.example.newsapp2.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp2.R

class RecyclerAdapter( private var author:List<String>,
                       private var content:List<String>,
    private var titles: List<String>,
                     private var details: List<String>,
                     private var images: List<String>,
                     private var links: List<String>
                     ):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemTitle : TextView=itemView.findViewById(R.id.tv_title)
        val itemDetail : TextView=itemView.findViewById(R.id.tv_description)
        val itemPicture : ImageView=itemView.findViewById(R.id.iv_image)

        init {
            itemView.setOnClickListener{ v : View ->
                val position : Int = adapterPosition
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(links[position])
                startActivity(itemView.context,intent,null)

            }
        }





    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
       holder.itemTitle.text=titles[position]
        holder.itemDetail.text=details[position]

        Glide.with(holder.itemPicture)
            .load(images[position])
            .into(holder.itemPicture)
    }

    override fun getItemCount(): Int {
        return  titles.size
    }
}