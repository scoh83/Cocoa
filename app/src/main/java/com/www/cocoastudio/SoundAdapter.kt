package com.www.cocoastudio

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.www.cocoastudio.databinding.ItemSoundBinding

class SoundAdapter(val items: List<Sound>, private val clickListener: (sound: Sound, idx: Int) -> Unit):
    RecyclerView.Adapter<SoundAdapter.SoundViewHolder>() {

    class SoundViewHolder(val binding: ItemSoundBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {

        Log.d("ssssssssssssssss","onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent, false)
        val viewHolder = SoundViewHolder(ItemSoundBinding.bind(view))


        var btnSound : ImageView  = view.findViewById(R.id.btnSound)
        btnSound.setOnClickListener{
            var animclick = AnimationUtils.loadAnimation(parent.context, R.anim.click)
            btnSound.startAnimation(AnimationUtils.loadAnimation(parent.context, R.anim.click))
            clickListener.invoke(items[viewHolder.adapterPosition], viewHolder.adapterPosition)
        }

        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        holder.binding.model = items[position]
    }
}