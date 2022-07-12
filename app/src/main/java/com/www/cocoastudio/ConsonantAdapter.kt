package com.www.cocoastudio

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.www.cocoastudio.databinding.ItemConsonantBinding

class ConsonantAdapter(val items: List<Consonant>, private val clickListener: (consonant: Consonant, idx: Int, type :String) -> Unit):
    RecyclerView.Adapter<ConsonantAdapter.ConsonantViewHolder>() {

    class ConsonantViewHolder(val binding: ItemConsonantBinding): RecyclerView.ViewHolder(binding.root)
    //val list: ArrayList<View> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsonantViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consonant, parent, false)
        val viewHolder = ConsonantViewHolder(ItemConsonantBinding.bind(view))

        var txtItem :TextView  = view.findViewById(R.id.txtItem)
        txtItem.setOnClickListener{

            var txtitem = it.findViewById<View>(R.id.txtItem)
            var animclick = AnimationUtils.loadAnimation(parent.context, R.anim.click)
            txtitem.startAnimation(AnimationUtils.loadAnimation(parent.context, R.anim.click))
            clickListener.invoke(items[viewHolder.adapterPosition], viewHolder.adapterPosition, "txt")
        }

        var context :Context = parent.context

        var btnSound : ImageView  = view.findViewById(R.id.btnSound)
        btnSound.setOnClickListener{

            var bsound = it.findViewById<View>(R.id.btnSound)
            var animclick = AnimationUtils.loadAnimation(parent.context, R.anim.click)
            bsound.startAnimation(AnimationUtils.loadAnimation(parent.context, R.anim.click))
            clickListener.invoke(items[viewHolder.adapterPosition], viewHolder.adapterPosition, "img")
            //startActivity(Intent(context, SoundActivity::class.java))

            //clickListener.invoke(items[viewHolder.adapterPosition], viewHolder.adapterPosition)
        }



//        view.setOnClickListener{
//
//            var txtitem = it.findViewById<View>(R.id.txtItem)
//            var animclick = AnimationUtils.loadAnimation(parent.context, R.anim.click)
//            txtitem.startAnimation(AnimationUtils.loadAnimation(parent.context, R.anim.click))
//
//            //선택뷰 영역에서 아이템 찾기
////            adapterPosition = viewHolder.adapterPosition
////            for (i in list){
////                if(i == it)
////                    it.findViewById<View>(R.id.txtItem).setVisibility(View.VISIBLE)
////                else
////                    i.findViewById<View>(R.id.vie_select).setVisibility(View.INVISIBLE)
////            }
//            clickListener.invoke(items[viewHolder.adapterPosition], viewHolder.adapterPosition)
//        }
        return viewHolder
    }

    private fun startActivity(intent: Intent) {

    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ConsonantViewHolder, position: Int) {
        holder.binding.model = items[position]
        //holder.itemView.findViewById<View>(R.id.view_bg).setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), items[position].src))
        //holder.itemView.findViewById<View>(R.id.view_bg).setBackgroundColor(items[position].color)
        //holder.itemView.view_bg.setBackgroundColor(items[position].color)
//
//        val drawable = holder.itemView.view_bg.background as GradientDrawable
//        //drawable.setStroke(3, items[position].color) // width and stroke color



    }
}