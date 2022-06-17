package com.www.cocoastudio
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.www.cocoastudio.databinding.ItemConsonantBinding

class MainActivity : AppCompatActivity() {

    private var soundPool: SoundPool? = null
    val consonant = arrayListOf<Consonant>()
    lateinit var mediaPlayer :MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("tttttt", "onDestroy2")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        val soundId1 = soundPool!!.load(this, R.raw.intro2, 1)

        mediaPlayer = MediaPlayer.create(this, R.raw.bg)

        Handler().postDelayed(Runnable {
            mediaPlayer.setVolume(0.6f, 0.6f)
            mediaPlayer.start()
        }, 500L)


        val webView = findViewById<WebView>(R.id.wView) // 웹뷰 셋팅
        webView.apply {
            webViewClient = WebViewClient() // 하이퍼링크 클릭시 새창 띄우기 방지
            webChromeClient = WebChromeClient() // 크롬환경에 맞는 세팅을 해줌. 특히, 알람등을 받기위해서는 꼭 선언해주어야함 (alert같은 경우)
            settings.javaScriptEnabled = true // 자바스크립트 허용
            settings.javaScriptCanOpenWindowsAutomatically = false
            // 팝업창을 띄울 경우가 있는데, 해당 속성을 추가해야 window.open() 이 제대로 작동 , 자바스크립트 새창도 띄우기 허용여부
            settings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부 (멀티뷰)
            settings.loadsImagesAutomatically = true // 웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정하는 속성
            settings.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
            settings.loadWithOverviewMode = true // 메타태그 허용 여부
            settings.setSupportZoom(true) // 화면 줌 허용여부
            settings.builtInZoomControls = false // 화면 확대 축소 허용여부
            settings.displayZoomControls = false // 줌 컨트롤 없애기.
            settings.cacheMode = WebSettings.LOAD_NO_CACHE // 웹뷰의 캐시 모드를 설정하는 속성으로써 5가지 모드
            /*
            (1) LOAD_CACHE_ELSE_NETWORK 기간이 만료돼 캐시를 사용할 수 없을 경우 네트워크를 사용합니다.
            (2) LOAD_CACHE_ONLY 네트워크를 사용하지 않고 캐시를 불러옵니다.
            (3) LOAD_DEFAULT 기본적인 모드로 캐시를 사용하고 만료된 경우 네트워크를 사용해 로드합니다.
            (4) LOAD_NORMAL 기본적인 모드로 캐시를 사용합니다.
            (5) LOAD_NO_CACHE 캐시모드를 사용하지 않고 네트워크를 통해서만 호출합니다.
             */
            settings.setAppCacheEnabled(true) // 앱 내부 캐시 사용 여부 설정 (Deprecated )
            settings.domStorageEnabled = true // 로컬 스토리지 사용 여부를 설정하는 속성으로 팝업창등을 '하루동안 보지 않기' 기능 사용에 필요
            settings.allowContentAccess // 웹뷰 내에서 파일 액세스 활성화 여부
            settings.userAgentString = "app" // 웹에서 해당 속성을 통해 앱에서 띄운 웹뷰로 인지 할 수 있도록 합니다.
            settings.defaultTextEncodingName = "UTF-8" // 인코딩 설정
            settings.databaseEnabled = true //Database Storage API 사용 여부 설정
        }
        webView.setBackgroundColor(Color.TRANSPARENT)
        //1920px; height: 1080px 원본 사이즈
        //
        webView.loadData("<style> \n" +
            "    div {\n" +
            "        width: 100px;\n" +
            "        height: 888px;\n" +
            "        -ms-transform: rotate(90deg); /* IE 9 */\n" +
            "        -webkit-transform: rotate(90deg); /* Chrome, Safari, Opera */\n" +
            "        transform: rotate(90deg);\n" +
            "    }\n" +
            "    </style>\n" +
            "<div style='margin-top: -400px;'>\n" +
            "<script src='https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js'></script>\n" +
            "<lottie-player src='https://assets8.lottiefiles.com/packages/lf20_3lpfhpv4.json' background='transparent' speed='1' style='width: 888px; height: 500px;' loop autoplay></lottie-player>\n" +
            "</div>\n"
            ,"text/html; charset=utf-8", "utf-8")


        consonant.add(Consonant(R.drawable.rounded_corner1, "ㄱ", Color.parseColor("#6200EE"), "1", 1))
        consonant.add(Consonant(R.drawable.rounded_corner2, "ㄴ", Color.parseColor("#FE9A2E"), "2", 2))
        consonant.add(Consonant(R.drawable.rounded_corner3, "ㄷ", Color.parseColor("#FFBD12"), "3", 3))
        consonant.add(Consonant(R.drawable.rounded_corner4, "ㄹ", Color.parseColor("#03DAC5"), "4", 1))
        consonant.add(Consonant(R.drawable.rounded_corner5, "ㅁ", Color.parseColor("#FF89BB"), "5", 2))
        consonant.add(Consonant(R.drawable.rounded_corner2, "ㅂ", Color.parseColor("#6200EE"), "6", 3))
        consonant.add(Consonant(R.drawable.rounded_corner3, "ㅅ", Color.parseColor("#FFBD12"), "7", 1))
        consonant.add(Consonant(R.drawable.rounded_corner4, "ㅇ", Color.parseColor("#03DAC5"), "8", 2))
        consonant.add(Consonant(R.drawable.rounded_corner5, "ㅈ", Color.parseColor("#6200EE"), "9", 3))
        consonant.add(Consonant(R.drawable.rounded_corner1, "ㅊ", Color.parseColor("#FF89BB"), "10", 1))
        consonant.add(Consonant(R.drawable.rounded_corner3, "ㅋ", Color.parseColor("#FFBD12"), "11", 2))
        consonant.add(Consonant(R.drawable.rounded_corner4, "ㅌ", Color.parseColor("#03DAC5"), "12", 3))
        consonant.add(Consonant(R.drawable.rounded_corner5, "ㅍ", Color.parseColor("#FE9A2E"), "13", 1))
        consonant.add(Consonant(R.drawable.rounded_corner1, "ㅎ", Color.parseColor("#FF89BB"), "14", 2))

        var animclick = AnimationUtils.loadAnimation(this@MainActivity, R.anim.click)

        var recycler_view: RecyclerView = findViewById(R.id.recycler_view);
        recycler_view.apply {
            adapter = ConsonantAdapter(consonant){ consonant, idx ->
                val nextIntent = Intent(this@MainActivity, DetailActivity::class.java)
                soundPool?.play(soundId1, 1.0f, 1.0f, 0, 0, 1.0f)
                mediaPlayer.release()
                Handler().postDelayed(Runnable {
                    //nextIntent.putExtra("Title", consonant.title.toString())

                    Log.d("tttttt", "code "+consonant.code.toString())

                    nextIntent.putExtra("Code", consonant.code.toString())
                    startActivity(nextIntent)
                    finish()
                }, 200L)


                //nextIntent.putExtra("Code", consonant.code)
                //nextIntent.putExtra("Title", consonant.title)
//                nextIntent.putExtra("Latitude", Latitude.toString())
//                nextIntent.putExtra("Longitude", Longitude.toString())
//                nextIntent.putExtra("type", place.type.toString())
//                nextIntent.putExtra("idx", idx.toString())

            }
        }
//
//        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recycler_view.layoutManager = lm

        val layoutManager =  StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.setLayoutManager(layoutManager)
        //recycler_view.addItemDecoration(SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.space)));

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
        Log.d("tttttt", "onRestart1")
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.release()
        Log.d("tttttt", "onPause1")
    }
}



class ConsonantAdapter(val items: List<Consonant>, private val clickListener: (consonant: Consonant, idx: Int) -> Unit):
    RecyclerView.Adapter<ConsonantAdapter.ConsonantViewHolder>() {

    class ConsonantViewHolder(val binding: ItemConsonantBinding): RecyclerView.ViewHolder(binding.root)
    //val list: ArrayList<View> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsonantViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consonant, parent, false)
        val viewHolder = ConsonantViewHolder(ItemConsonantBinding.bind(view))

        view.setOnClickListener{

            var txtitem = it.findViewById<View>(R.id.txtItem)
            var animclick = AnimationUtils.loadAnimation(parent.context, R.anim.click)
            txtitem.startAnimation(AnimationUtils.loadAnimation(parent.context, R.anim.click))

            //선택뷰 영역에서 아이템 찾기
//            adapterPosition = viewHolder.adapterPosition
//            for (i in list){
//                if(i == it)
//                    it.findViewById<View>(R.id.txtItem).setVisibility(View.VISIBLE)
//                else
//                    i.findViewById<View>(R.id.vie_select).setVisibility(View.INVISIBLE)
//            }
            clickListener.invoke(items[viewHolder.adapterPosition], viewHolder.adapterPosition)
        }
        return viewHolder
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