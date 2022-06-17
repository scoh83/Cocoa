package com.www.cocoastudio
import android.animation.AnimatorInflater
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import org.json.JSONArray
import org.json.JSONObject


class DetailActivity : AppCompatActivity() {

    lateinit var txtViewArray: Array<TextView>
    lateinit var txtViewArray2: Array<TextView>
    lateinit var frameArray: Array<FrameLayout>
    lateinit var frameArray2: Array<FrameLayout>
    lateinit var lottieArray: Array<LottieAnimationView>
    lateinit var lottieArray2: Array<LottieAnimationView>
    lateinit var txtanswer: Array<TextView>
    lateinit var detailView: ConstraintLayout
    lateinit var congratulations_lottie: LottieAnimationView
    lateinit var detailLogo: LottieAnimationView

    lateinit var gameOver: LottieAnimationView
    lateinit var main_lottie: LottieAnimationView
    lateinit var main_lottie2: LottieAnimationView
    lateinit var main_Image: ImageView
    lateinit var webView: WebView
    var mainHandler: Handler = Handler()
    var mainRunnable: Runnable = Runnable {}

    lateinit var jsonString: String
    var stepIdx: Int = 1
    var stepArrayLen: Int = 0
    var gameidx: Int = 0
    var objMovetime: Long = 7000L
    lateinit var jsonStepArray: JSONArray

    private var soundPool: SoundPool? = null
    var btnSound : Int = 0
    var btnSound2 : Int = 0

    var gameover : Boolean = false
    var checkStatus = arrayOf(true, false, false, false, false, false, false, false, false, false, false, false, false)
    var itemCount = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    lateinit var mediaPlayer2 :MediaPlayer

    lateinit var score : TextView
    lateinit var title2 : TextView
    lateinit var title4 : TextView
    lateinit var gameOverMsg : TextView

    var bgStatus :Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        btnSound = soundPool!!.load(this, R.raw.intro2, 1)
        btnSound2 = soundPool!!.load(this, R.raw.click, 1)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.bg)
        initPage();     // 페이지 오브젝트 초기화

        game()

        var btnBack :TextView  = findViewById(R.id.btnback)
        btnBack.setOnClickListener {
            soundPool?.play(btnSound, 1.0f, 1.0f, 0, 0, 1.0f)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        var btnreplay :TextView  = findViewById(R.id.btnreplay)
        btnreplay.setOnClickListener {

            var detailbg: View =  findViewById(R.id.detailbg)
            detailbg.visibility =  View.GONE
            gameidx = 0;
            mediaPlayer2 = MediaPlayer.create(this, R.raw.bg)
            mediaPlayer2.start()
            bgStatus = 1

            for (index in 0 until 11) { checkStatus[index] = false }
            checkStatus[gameidx] = true
            gameover = false

            score.text = ""
            title2.text = ""
            title4.text = ""

            game()
        }

    }

    fun game(){

        // 1초에 한번씩 상태체크 해라
        val millisTime: Long = 1000
        mainRunnable = object : Runnable {
            override fun run() {

                if (checkStatus[gameidx] == true && stepArrayLen > gameidx) {
                    if(gameidx == 0) {
                        mediaPlayer2.start()
                    }

                    itemCount[gameidx] = 0
                    gameStart(gameidx)
                    gameidx++
                    checkStatus[gameidx] = false    // 다음판의 상태
                }

                mainHandler.postDelayed(this, millisTime) // millisTiem 이후 다시

                // 타임어 종료
                if (stepArrayLen == gameidx)
                {
                    gameover = true
                    mainHandler.removeCallbacks(mainRunnable) // handler task 등록
                }

            }
        }
        mainHandler.post(mainRunnable);
    }

    override fun onResume() {
        super.onResume()
        Log.d("tttttt", "onResume")
        mediaPlayer2.start()
        //mediaPlayer2.release()
    }

    override fun onPause() {
        super.onPause()
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer2.release()
        mainHandler.removeCallbacks(mainRunnable) // handler task 등록
    }

    // 페이지 초기화
    fun initPage() {
        var textView1: TextView = findViewById(R.id.textView1);
        var textView2: TextView = findViewById(R.id.textView2);
        var textView3: TextView = findViewById(R.id.textView3);
        var textView4: TextView = findViewById(R.id.textView4);
        //var textView5: TextView = findViewById(R.id.textView5);

        var textView6: TextView = findViewById(R.id.textView6);
        var textView7: TextView = findViewById(R.id.textView7);
        var textView8: TextView = findViewById(R.id.textView8);
        var textView9: TextView = findViewById(R.id.textView9);
        //var textView10: TextView = findViewById(R.id.textView10);

        var txtFrame1: FrameLayout = findViewById(R.id.txtFrame1);
        var txtFrame2: FrameLayout = findViewById(R.id.txtFrame2);
        var txtFrame3: FrameLayout = findViewById(R.id.txtFrame3);
        var txtFrame4: FrameLayout = findViewById(R.id.txtFrame4);
        //var txtFrame5: FrameLayout = findViewById(R.id.txtFrame5);

        var txtFrame6: FrameLayout = findViewById(R.id.txtFrame6);
        var txtFrame7: FrameLayout = findViewById(R.id.txtFrame7);
        var txtFrame8: FrameLayout = findViewById(R.id.txtFrame8);
        var txtFrame9: FrameLayout = findViewById(R.id.txtFrame9);
        //var txtFrame10: FrameLayout = findViewById(R.id.txtFrame10);

        var textLottie1: LottieAnimationView = findViewById(R.id.textLottie1);
        var textLottie2: LottieAnimationView = findViewById(R.id.textLottie2);
        var textLottie3: LottieAnimationView = findViewById(R.id.textLottie3);
        var textLottie4: LottieAnimationView = findViewById(R.id.textLottie4);
        //var textLottie5: LottieAnimationView = findViewById(R.id.textLottie5);

        var textLottie6: LottieAnimationView = findViewById(R.id.textLottie6);
        var textLottie7: LottieAnimationView = findViewById(R.id.textLottie7);
        var textLottie8: LottieAnimationView = findViewById(R.id.textLottie8);
        var textLottie9: LottieAnimationView = findViewById(R.id.textLottie9);
        //var textLottie10: LottieAnimationView = findViewById(R.id.textLottie10);

        var textView11: TextView = findViewById(R.id.textView11);
        var textView12: TextView = findViewById(R.id.textView12);
        var textView13: TextView = findViewById(R.id.textView13);
        var textView14: TextView = findViewById(R.id.textView14);
        //var textView15: TextView = findViewById(R.id.textView15);
        var textView16: TextView = findViewById(R.id.textView16);
        var textView17: TextView = findViewById(R.id.textView17);
        var textView18: TextView = findViewById(R.id.textView18);
        var textView19: TextView = findViewById(R.id.textView19);
        //var textView20: TextView = findViewById(R.id.textView20);

        var txtFrame11: FrameLayout = findViewById(R.id.txtFrame11);
        var txtFrame12: FrameLayout = findViewById(R.id.txtFrame12);
        var txtFrame13: FrameLayout = findViewById(R.id.txtFrame13);
        var txtFrame14: FrameLayout = findViewById(R.id.txtFrame14);
        //var txtFrame15: FrameLayout = findViewById(R.id.txtFrame15);
        var txtFrame16: FrameLayout = findViewById(R.id.txtFrame16);
        var txtFrame17: FrameLayout = findViewById(R.id.txtFrame17);
        var txtFrame18: FrameLayout = findViewById(R.id.txtFrame18);
        var txtFrame19: FrameLayout = findViewById(R.id.txtFrame19);
        //var txtFrame20: FrameLayout = findViewById(R.id.txtFrame20);

        var textLottie11: LottieAnimationView = findViewById(R.id.textLottie11);
        var textLottie12: LottieAnimationView = findViewById(R.id.textLottie12);
        var textLottie13: LottieAnimationView = findViewById(R.id.textLottie13);
        var textLottie14: LottieAnimationView = findViewById(R.id.textLottie14);
        //var textLottie15: LottieAnimationView = findViewById(R.id.textLottie15);
        var textLottie16: LottieAnimationView = findViewById(R.id.textLottie16);
        var textLottie17: LottieAnimationView = findViewById(R.id.textLottie17);
        var textLottie18: LottieAnimationView = findViewById(R.id.textLottie18);
        var textLottie19: LottieAnimationView = findViewById(R.id.textLottie19);
        //var textLottie20: LottieAnimationView = findViewById(R.id.textLottie20);


        var txtanswer1: TextView = findViewById(R.id.txtanswer1)
        var txtanswer2: TextView = findViewById(R.id.txtanswer2)
        var txtanswer3: TextView = findViewById(R.id.txtanswer3)
        var txtanswer4: TextView = findViewById(R.id.txtanswer4)

        txtViewArray = arrayOf(textView1, textView2, textView3, textView4, textView6, textView7, textView8, textView9)
        frameArray = arrayOf(txtFrame1, txtFrame2, txtFrame3, txtFrame4, txtFrame6, txtFrame7, txtFrame8, txtFrame9)
        lottieArray = arrayOf(textLottie1, textLottie2, textLottie3, textLottie4, textLottie6, textLottie7, textLottie8, textLottie9)

        txtViewArray2 = arrayOf(textView11, textView12, textView13, textView14, textView16, textView17, textView18, textView19)
        frameArray2 = arrayOf(txtFrame11, txtFrame12, txtFrame13, txtFrame14, txtFrame16, txtFrame17, txtFrame18, txtFrame19)
        lottieArray2 = arrayOf(textLottie11, textLottie12, textLottie13, textLottie14, textLottie16, textLottie17, textLottie18, textLottie19)

        txtanswer = arrayOf(txtanswer1, txtanswer2, txtanswer3, txtanswer4)

        gameOver = findViewById(R.id.gameOver)
        detailLogo = findViewById(R.id.detailLogo)
        main_lottie = findViewById(R.id.main_lottie)
        main_lottie2 = findViewById(R.id.main_lottie2)
        main_Image = findViewById(R.id.main_Image)
        congratulations_lottie = findViewById(R.id.congratulations_lottie);


        jsonString = assets.open("step.json").reader().readText();
        var jsonObject = JSONObject(jsonString)                            // Json 데이터를 가져와서

        if(intent.hasExtra("Code"))
        {
            var code = intent.getStringExtra("Code").toString()
            if(code == "1" || code == "2" ||  code == "3" ||  code == "4")
                jsonStepArray = jsonObject.getJSONArray("step$code")
            else
                jsonStepArray = jsonObject.getJSONArray("step$stepIdx")
        }
        else
            jsonStepArray = jsonObject.getJSONArray("step$stepIdx")

        //jsonStepArray = jsonObject.getJSONArray("step$stepIdx")     // 스탭정보에 맞게 데이저를 조회 한다
        stepArrayLen = jsonStepArray.length()

        detailView = findViewById(R.id.detailView)
        webView = findViewById(R.id.webViewSound)
        score =  findViewById(R.id.score)
        title2 =  findViewById(R.id.title2)
        title4 =  findViewById(R.id.title4)
        gameOverMsg =  findViewById(R.id.gameOverMsg)

    }

    // 게임 시작
    fun gameStart(idx: Int) {

        // Json 게임 아이템 정보
        var jsonStep = jsonStepArray.getJSONObject(idx);
        var postion = jsonStep.getString("postion")                         // 표현되는 텍스트 위치
        var answer = jsonStep.getString("answer")                           // 정답
        val jsonGameArray = jsonStep.getJSONArray("questions")              // 떨어질 한글목록
        val jsondelayMillisArray = jsonStep.getJSONArray("delayMillis")     // 떨어질 한글목록의 딜레이 시간

        SetQuiz(jsonStep.getString("lottiejson"), jsonStep.getString("image"))  //퀴즈영역셋팅
        SetBgColor(jsonStep.getString("bgColor"))                           // 배경색상 변경
        SetAnswer(jsonStep.getString("example"), postion, answer )                  // 정답 셋팅

        webView.webViewClient = WebViewClient()
        webView.loadUrl(jsonStep.getString("url"))

        // 한글 아이템의 수만큼 돌아서
        for (index in 0 until jsonGameArray.length()) {
            val jsonObj = jsonGameArray.getJSONObject(index)                // 한글 과
            val jsondelayObj = jsondelayMillisArray.getJSONObject(index)    // 딜레이 시간정보를 가져온다
            val txtObj: TextView = if (idx % 2 == 0) txtViewArray[index] else txtViewArray2[index]
            txtObj.text = jsonObj.getString((index + 1).toString()).toString()
            val frameObj: FrameLayout = if (idx % 2 == 0) frameArray[index] else frameArray2[index]
            val lottieObj: LottieAnimationView = if (idx % 2 == 0) lottieArray[index] else lottieArray2[index]

            objCreate(txtObj, frameObj, jsondelayObj.getString((index + 1).toString()).toLong(), idx)      // 떨어지는 글자 생성
            objClick(txtObj, lottieObj, postion, answer, idx,jsonStep.getString("url"))                                               // 클릭이벤트 연결
        }
    }

    // 한글 오브젝트 생성
    fun objCreate(txtObj : TextView, frameLayoutObj : FrameLayout, delayMillis : Long, idx:Int)
    {
        // 생성하고
        Handler().postDelayed(Runnable {
            if(congratulations_lottie.visibility != View.VISIBLE) {
                frameLayoutObj.visibility = View.VISIBLE
                txtObj.visibility = View.VISIBLE
                //한글을 떨어트려라
                objMove(frameLayoutObj)
            }
        }, delayMillis)

        // 특정시간이 지난뒤 위치를 초기화 해라
        Handler().postDelayed(Runnable {
            frameLayoutObj.visibility = View.INVISIBLE
            txtObj.visibility = View.INVISIBLE

            Log.d("tttttt1", "txtObj : "+txtObj.text)
            //objMove(frameLayoutObj, 0f, 0L)
            itemCount[idx] = itemCount[idx] +1
            //Log.d("tttttt", "$idx  itemCount[idx] : "+itemCount[idx] + " checkStatus[idx] : "+checkStatus[idx])
            if(itemCount[idx] >= 7) {
                Log.d("tttttt1", "idx+1 : "+(idx+1))
                checkStatus[idx+1] = true
                if((idx+1) == 10)
                    gameOver()

            }

        }, delayMillis + objMovetime )  // 떨어지는 시간과 생성시점의 시간 더하고 위치를 초기화 한다

    }

    // 게임 종료처리
    fun gameOver()
    {
        if(gameover == true)
        {
            score.text = ""
            main_Image.visibility = View.INVISIBLE
            main_lottie.visibility = View.INVISIBLE
            main_lottie2.visibility = View.INVISIBLE

            for (index in 0 until 4) {
                txtanswer[index].visibility = View.GONE
                txtanswer[index].text = ""
            }

            var detailbg: View =  findViewById(R.id.detailbg)
            detailbg.visibility =  View.VISIBLE
            SetBgColor("#ffbd11")
            mediaPlayer2.release()

            var lottie : String = "bg.json"
            webView.webViewClient = WebViewClient()

            var cnt : Int = if(title2.text.toString() == "") 0 else title2.text.toString().toInt()
            if(cnt >= 5)
            {
                detailLogo.setAnimation(lottie)
                detailLogo.loop(true);
                detailLogo.playAnimation();

                gameOver.setAnimation(lottie)
                gameOver.loop(true);
                gameOver.playAnimation();

                gameOverMsg.text  = "참! 잘했어요"
                webView.loadUrl("https://papago.naver.com/apis/tts/c_lt_kyuri_2.2.2_274-nvoice_kyuri_2.2.2_1ff68b6b93505089a3882debb2c99cd2-1655259981950")

            }
            else
            {
                lottie = if(cnt == 0)
                    "bg5.json"
                else if(cnt == 1)
                    "bg4.json"
                else if(cnt == 2)
                    "bg3.json"
                else
                    "bg2.json"

                detailLogo.setAnimation(lottie)
                detailLogo.loop(true);
                detailLogo.playAnimation();

                gameOver.setAnimation(lottie)
                gameOver.loop(true);
                gameOver.playAnimation();
                gameOverMsg.text  = "다시 도전해보세요!"
                webView.loadUrl("https://papago.naver.com/apis/tts/c_lt_kyuri_2.2.2_191-nvoice_kyuri_2.2.2_85f8a9bef00d4232637d1c265622eb62-1655299992894")
            }

        }
    }

    // 정답 영역 셋팅
    fun SetAnswer(example:String, postion:String, answer: String)
    {
        // 정답영역 셋팅팅
        for (index in 0 until 4) {
            txtanswer[index].visibility = View.GONE
            txtanswer[index].text = ""
        }

        // 정답 표시 영역
        var chars = example
        var charsArray = CharArray(chars.length)
        for (i in chars.indices) {
            charsArray[i] = chars[i]
        }

        var i = 0
        var s = ""
        for (index in 0 until charsArray.count() + 1) {
            txtanswer[index].visibility = View.VISIBLE
            if (postion.toInt() == index)
                s += answer

            if (postion.toInt() != index) {
                txtanswer[index].text = chars[i].toString()
                s += chars[i].toString()
                i++
            }

            score.text = s
        }
    }

    fun Setface()
    {
        var lottie : String = "bg.json"
        if(bgStatus == 1)
            lottie = "bg.json"
        else if(bgStatus < 10)
            lottie = "bg2.json"
        else if(bgStatus < 20)
            lottie = "bg3.json"
        else if(bgStatus < 30)
            lottie = "bg4.json"
        else
            lottie = "bg5.json"

        detailLogo.setAnimation(lottie)
        detailLogo.loop(true);
        detailLogo.playAnimation();
    }

    // 퀴즈 변경
    fun SetQuiz(lottiejson : String, image: String )
    {
        // 퀴즈 이미지 변경
        if (lottiejson != "") {

            if(lottiejson == "raccoon.json" || lottiejson == "trumpet.json" || lottiejson == "summer.json"
                || lottiejson == "squirrel.json"
                || lottiejson == "note.json" ||lottiejson == "travel.json" || lottiejson == "grandfather.json" )
            {
                main_Image.visibility = View.INVISIBLE
                main_lottie.visibility = View.INVISIBLE
                main_lottie2.visibility = View.VISIBLE
                main_lottie2.setAnimation(lottiejson)
                main_lottie2.loop(true);
                main_lottie2.playAnimation();

            }
            else
            {
                main_lottie2.visibility = View.INVISIBLE    //조금더 큰 사이즈
                main_Image.visibility = View.INVISIBLE
                main_lottie.visibility = View.VISIBLE
                main_lottie.setAnimation(lottiejson)
                main_lottie.loop(true);
                main_lottie.playAnimation();

            }
        } else {
            // 이미지 리소스 가져오기
            var img = this.getResources().getIdentifier("drawable/$image", "drawable", this.getPackageName());
            main_lottie.visibility = View.INVISIBLE
            main_lottie2.visibility = View.INVISIBLE    //조금더 큰 사이즈
            main_Image.visibility = View.VISIBLE

            main_Image.setImageResource(img)

        }
    }

    // 배경색 변경
    fun SetBgColor(bgColor :String) {
        detailView.setBackgroundColor(Color.parseColor(bgColor))
        getWindow().setStatusBarColor(Color.parseColor(bgColor))        // set color only status bar
        getWindow().setNavigationBarColor(Color.parseColor(bgColor))    // set color only navigation bar
    }

    // 한글 오브젝트 이동
    fun objMove(obj1 : FrameLayout)
    {
        var anim1 = AnimatorInflater.loadAnimator(this, R.anim.translation);
        anim1.setTarget(obj1);
        anim1.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun objClick(txtObj : TextView, lottieObj : LottieAnimationView, postion: String, answer : String, idx:Int, url :String)
    {
        // 클릭해라
        txtObj.setOnClickListener{
            fnvibrator()    //클릭이 되면 진동을

            if(txtObj.text.toString() == answer)
            {
                bgStatus = 1
                soundPool?.play(btnSound2, 0.6f, 0.6f, 0, 0, 1.0f)
                // 정답인경우 (8 초의 시간흐름 필요)
                txtanswer[postion.toInt()].text = answer
                congratulations_lottie.visibility = View.VISIBLE            // 축하 이미지 노출
                congratulations_lottie.playAnimation();
                Handler().postDelayed(Runnable {
//                    webView.webViewClient = WebViewClient()
//                    webView.loadUrl(url)
                    congratulations_lottie.visibility = View.INVISIBLE      // 축하 이미지 삭제    (3초)
                }, 3000)

                Handler().postDelayed(Runnable {
//                    gameidx++
//                    gameStart(gameidx)
                    checkStatus[idx+1] = true;
//                    var toast = Toast.makeText(this, "다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음다음", Toast.LENGTH_LONG)
//                    toast.show()
                }, 3000)

                // 모든 한글 오브젝트를 숨기고
                for (index in 0 until 8){
                    val FrameLayoutObj : FrameLayout  =if(idx % 2 == 0) frameArray[index] else frameArray2[index]
                    FrameLayoutObj.visibility = View.INVISIBLE
                }

                title2.text = if( title2.text == "") "1" else (title2.text.toString().toInt() +1).toString()
                //마지막 인경우
                gameOver()
            }
            else
            {
                title4.text = if( title4.text == "") "1" else (title4.text.toString().toInt() +1).toString()
                bgStatus++
                soundPool?.play(btnSound, 1.0f, 1.0f, 0, 0, 1.0f)
                txtObj.visibility = View.INVISIBLE
                lottieObj.visibility =  View.VISIBLE        // 터지는 이미지 노출
                Handler().postDelayed(Runnable {            // 0.5초뒤에 이미지는 사라저라
                    lottieObj.visibility = View.INVISIBLE
                }, 500L)
            }

            Setface()
        }
    }

    //진동
    fun fnvibrator()
    {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator;
        vibrator.vibrate(VibrationEffect.createOneShot(100, 100));
    }
}

