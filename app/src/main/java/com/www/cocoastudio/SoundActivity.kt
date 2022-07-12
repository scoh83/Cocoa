package com.www.cocoastudio
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.lottie.LottieAnimationView
import org.json.JSONArray
import org.json.JSONObject

class SoundActivity : AppCompatActivity() {

    var stepIdx :Int =0
    var code =""
    lateinit var jsonString: String
    lateinit var jsonStepArray: JSONArray


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)

        getWindow().setNavigationBarColor(Color.parseColor("#db74ff"))    // set color only navigation bar

        if(intent.hasExtra("Code"))
        {
            var JsonName = intent.getStringExtra("JsonName").toString()
            jsonString = assets.open("$JsonName.json").reader().readText();
            var jsonObject = JSONObject(jsonString)                            // Json 데이터를 가져와서

            if(intent.hasExtra("Code"))
            {
                code = intent.getStringExtra("Code").toString()
                jsonStepArray = jsonObject.getJSONArray("step$code")
            }
            else
                jsonStepArray = jsonObject.getJSONArray("step$stepIdx")
        }

        val sound = arrayListOf<Sound>()
        for (index in 0 until jsonStepArray.length()) {
            val jsonObj = jsonStepArray.getJSONObject(index)                // 한글 과

            var text = GetText(jsonObj.getString("example"), jsonObj.getString("postion"), jsonObj.getString("answer"))
            var charsArray = CharArray(text.length)
            for (i in text.indices) {
                charsArray[i] = text[i]
            }

            sound.add(Sound(
                jsonObj.getString("answer"),
                jsonObj.getString("example"),
                jsonObj.getString("postion"),
                index ,
                this.getResources().getIdentifier("drawable/"+jsonObj.getString("image"), "drawable", this.getPackageName()),
                charsArray[0].toString(),
                if(charsArray[0].toString() == jsonObj.getString("answer"))  Color.parseColor("#FF89BB") else Color.parseColor("#FF000000"),
                View.VISIBLE,

                if(text.length > 1) charsArray[1].toString() else "",
                if(text.length > 1 && charsArray[1].toString() == jsonObj.getString("answer"))  Color.parseColor("#FF89BB") else Color.parseColor("#FF000000"),
                if(text.length > 1)  View.VISIBLE else View.GONE,

                if(text.length > 2) charsArray[2].toString() else "",
                if(text.length > 2 && charsArray[2].toString() == jsonObj.getString("answer"))  Color.parseColor("#FF89BB") else Color.parseColor("#FF000000"),
                if(text.length > 2)  View.VISIBLE else View.GONE,

                if(text.length > 3) charsArray[3].toString() else "",
                if(text.length > 3 && charsArray[3].toString() == jsonObj.getString("answer"))  Color.parseColor("#FF89BB") else Color.parseColor("#FF000000"),
                if(text.length > 3)  View.VISIBLE else View.GONE,
                jsonObj.getString("url")
                )
            )
        }

        var webView: WebView = findViewById(R.id.webViewSound)

        var recyclerview_sound : RecyclerView =  findViewById(R.id.recyclerview_sound)
        recyclerview_sound.apply {
            adapter = SoundAdapter(sound){ sound, idx ->
                webView.webViewClient = WebViewClient()
                webView.loadUrl(sound.url)
            }
        }

        val layoutManager =  StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerview_sound.setLayoutManager(layoutManager)

    }


    fun GetText(example:String, postion:String, answer: String) : String
    {
//        // 정답영역 셋팅팅
//        for (index in 0 until 4) {
//            txtanswer[index].visibility = View.GONE
//            txtanswer[index].text = ""
//        }

        var text =""

        // 정답 표시 영역
        var chars = example
        var charsArray = CharArray(chars.length)
        for (i in chars.indices) {
            charsArray[i] = chars[i]
        }

        var i = 0
        var s = ""
        for (index in 0 until charsArray.count() + 1) {
            //txtanswer[index].visibility = View.VISIBLE
            if (postion.toInt() == index)
                s += answer

            if (postion.toInt() != index) {
                //txtanswer[index].text = chars[i].toString()
                s += chars[i].toString()
                i++
            }

            text = s
        }
        return text
    }

}