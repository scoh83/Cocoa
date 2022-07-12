package com.www.cocoastudio

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPageradapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private  var NUM_PAGES = 5

    override fun getItemCount(): Int  = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        Log.d("ttttttttttttttt","position : "+position)
        return when(position){
            0-> {MainFragment.newInstance("step", "#3490dc")}
            1-> {MainFragment.newInstance("step2","#8ab9b5")}
            2-> {MainFragment.newInstance("step3","#7c4aba")}
            3-> {MainFragment.newInstance("step4","#d8b499")}
            else -> {MainFragment.newInstance("step5","#3490dc")}
        }
    }



}