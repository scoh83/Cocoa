package com.www.cocoastudio
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView

class IntroActivity : AppCompatActivity() {

    private var soundPool: SoundPool? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        val soundId1 = soundPool!!.load(this, R.raw.intro, 1)
        val soundId2 = soundPool!!.load(this, R.raw.intro2, 1)

        var logA: TextView = findViewById(R.id.logA);
        var logB: TextView = findViewById(R.id.logB);
        var logC: TextView = findViewById(R.id.logC);

        var animBounce = AnimationUtils.loadAnimation(this@IntroActivity, R.anim.bouncedown)

        Handler().postDelayed(Runnable {
            soundPool?.play(soundId1, 0.8f, 0.8f, 0, 0, 1.0f)
            logA.visibility = View.VISIBLE;
            logA.startAnimation(AnimationUtils.loadAnimation(this@IntroActivity, R.anim.bouncedown))
        }, 500L)

        Handler().postDelayed(Runnable {
            logB.visibility = View.VISIBLE;
            logB.startAnimation(AnimationUtils.loadAnimation(this@IntroActivity, R.anim.bouncedown))
        }, 1000L)

        Handler().postDelayed(Runnable {
            logC.visibility = View.VISIBLE;
            logC.startAnimation(animBounce)
            soundPool?.play(soundId2, 0.8f, 0.8f, 0, 0, 1.0f)
        }, 2000L)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this@IntroActivity, HomeActivity::class.java))
            finish()
        }, 4000L)

        getWindow().setNavigationBarColor(Color.parseColor("#FF3700B3"))    // set color only navigation bar

    }
}