package com.www.cocoastudio

import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.www.cocoastudio.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    lateinit var mediaPlayer : MediaPlayer
    lateinit var viewPager : ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.bg)

        Handler().postDelayed(Runnable {
            mediaPlayer.setVolume(0.3f, 0.3f)
            mediaPlayer.start()
        }, 500L)

        viewPager = binding.viewPager

        var adapter = MainPageradapter(this as FragmentActivity)
        viewPager.adapter = adapter

        binding.btnArrowLeft.setOnClickListener {
            var itemCnt = viewPager.currentItem - 1
            viewPager.currentItem = itemCnt
            adapter.createFragment(itemCnt)
        }

        binding.btnArrowRight.setOnClickListener {
            var itemCnt = viewPager.currentItem + 1
            viewPager.currentItem = itemCnt
            adapter.createFragment(itemCnt)

        }

        viewPager.setPageTransformer(DepthPageTransformer())

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0-> {SetBgColor("#3490dc", "일반글자")}
                    1-> {SetBgColor("#8ab9b5", "받침글자")}
                    2-> {SetBgColor("#7c4aba", "된소리글자")}
                    3-> {SetBgColor("#d8b499", "겹모음글자")}
                    else -> {SetBgColor("#3490dc", "겹모음글자")}
                }

                //Log.d("ttttttttttttttt","viewPager.currentItem : "+viewPager.currentItem.toString())
            }
        })
    }

    fun SetBgColor(bgColor :String, title : String) {
        getWindow().setStatusBarColor(Color.parseColor(bgColor))        // set color only status bar
        getWindow().setNavigationBarColor(Color.parseColor(bgColor))    // set color only navigation bar
        binding.homeLayout.setBackgroundColor(Color.parseColor(bgColor))
        //binding.title1.text = title
    }

    override fun onResume() {
        super.onResume()
        Log.d("tttttt", "onResume1")
    }

    override fun onRestart() {
        super.onRestart()

        mediaPlayer = MediaPlayer.create(this, R.raw.bg)

        Handler().postDelayed(Runnable {
            mediaPlayer.setVolume(0.6f, 0.6f)
            mediaPlayer.start()
        }, 500L)

        var adapter = MainPageradapter(this as FragmentActivity)
        viewPager.adapter = adapter

        Log.d("tttttt", "onRestart1")
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.release()
        Log.d("tttttt", "onPause1")
    }


}