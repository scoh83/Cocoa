package com.www.cocoastudio

import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.www.cocoastudio.databinding.FragmentMainBinding
import io.realm.Realm
import io.realm.kotlin.where

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  var _binding : FragmentMainBinding? = null
    private  val binding get() = _binding!!
    private var realm: Realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.d("ttttttttttttttt","onCreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //.d("ttttttttttttttt","onCreateView")

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        //Log.d("ttttttttttttttt","onDestroy")
        _binding = null
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        //Log.d("ttttttttttttttt","onHiddenChanged")
    }

    // 특정 사용자 읽어오기
    fun getStep(id: String): Step? = realm.where<Step>()
        .equalTo("id", id)
        .findFirst()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ttttttttttttttt","onViewCreated : param1 " + param1)

        val consonant = arrayListOf<Consonant>()
//        requireActivity().window.setStatusBarColor(Color.parseColor(param2))        // set color only status bar
//        requireActivity().window.setNavigationBarColor(Color.parseColor(param2))    // set color only navigation bar

        if(param1 == "step" )
        {
            binding.title1.text = "일반글자"
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㄱ", Color.parseColor("#6200EE"), "1", 1, View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㄴ", Color.parseColor("#FE9A2E"), "2", 2, View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㄷ", Color.parseColor("#FFBD12"), "3", 3, View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㄹ", Color.parseColor("#03DAC5"), "4", 1,  if(getStep(param1+"4") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅁ", Color.parseColor("#FF89BB"), "5", 2,  if(getStep(param1+"5") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㅂ", Color.parseColor("#6200EE"), "6", 3,  if(getStep(param1+"6") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㅅ", Color.parseColor("#FFBD12"), "7", 1,  if(getStep(param1+"7") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㅇ", Color.parseColor("#03DAC5"), "8", 2,  if(getStep(param1+"8") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅈ", Color.parseColor("#6200EE"), "9", 3,  if(getStep(param1+"9") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㅊ", Color.parseColor("#FF89BB"), "10", 1, if(getStep(param1+"10") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㅋ", Color.parseColor("#FFBD12"), "11", 2, if(getStep(param1+"11") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㅌ", Color.parseColor("#03DAC5"), "12", 3, if(getStep(param1+"12") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅍ", Color.parseColor("#FE9A2E"), "13", 1, if(getStep(param1+"13") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㅎ", Color.parseColor("#FF89BB"), "14", 2, if(getStep(param1+"14") == null)  View.VISIBLE else View.GONE))
        }
        else if(param1 == "step2" )
        {
            binding.title1.text = "받침글자"
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㄱ", Color.parseColor("#6200EE"), "1", 1, if(getStep(param1+"1") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㄴ", Color.parseColor("#FE9A2E"), "2", 2, if(getStep(param1+"2") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㄷ", Color.parseColor("#FFBD12"), "3", 3, if(getStep(param1+"3") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㄹ", Color.parseColor("#03DAC5"), "4", 1, if(getStep(param1+"4") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅁ", Color.parseColor("#FF89BB"), "5", 2, if(getStep(param1+"5") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㅂ", Color.parseColor("#6200EE"), "6", 3, if(getStep(param1+"6") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㅅ", Color.parseColor("#FFBD12"), "7", 1, if(getStep(param1+"7") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㅇ", Color.parseColor("#03DAC5"), "8", 2, if(getStep(param1+"8") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅈ", Color.parseColor("#6200EE"), "9", 3, if(getStep(param1+"9") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㅊ\nㅋ\nㅌ", Color.parseColor("#FF89BB"), "10", 1, if(getStep(param1+"10") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㅎ", Color.parseColor("#FF89BB"), "11", 2, if(getStep(param1+"11") == null)  View.VISIBLE else View.GONE))
        }
        else if(param1 == "step3")
        {
            //Log.d("ttttttttttttttt","onViewCreated : step3 " + param1)
            binding.title1.text = "된소리글자"
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㄲ", Color.parseColor("#6200EE"), "1", 1, if(getStep(param1+"1") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㄸ", Color.parseColor("#FE9A2E"), "2", 2, if(getStep(param1+"2") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㅃ", Color.parseColor("#FFBD12"), "3", 3, if(getStep(param1+"3") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㅆ", Color.parseColor("#03DAC5"), "4", 1, if(getStep(param1+"4") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅉ", Color.parseColor("#FF89BB"), "5", 2, if(getStep(param1+"5") == null)  View.VISIBLE else View.GONE))
        }
        else if(param1 == "step4")
        {
            binding.title1.text = "겹모음글자"
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㅐ", Color.parseColor("#6200EE"), "1", 1,  if(getStep(param1+"1") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㅔ", Color.parseColor("#FE9A2E"), "2", 2,  if(getStep(param1+"2") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㅖ", Color.parseColor("#FFBD12"), "3", 3,  if(getStep(param1+"3") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㅘ", Color.parseColor("#03DAC5"), "4", 1,  if(getStep(param1+"4") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅙ\nㅞ", Color.parseColor("#FF89BB"), "5", 2, if(getStep(param1+"5") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㅝ", Color.parseColor("#6200EE"), "6", 3,  if(getStep(param1+"6") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㅚ", Color.parseColor("#FFBD12"), "7", 1,  if(getStep(param1+"7") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㅟ", Color.parseColor("#03DAC5"), "8", 2,  if(getStep(param1+"8") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅢ\nㅒ", Color.parseColor("#6200EE"), "9", 3, if(getStep(param1+"9") == null)  View.VISIBLE else View.GONE))

        }
        else
        {
            binding.title1.text = "겹받침글자"
            consonant.add(Consonant(R.drawable.rounded_corner1, "ㄲ", Color.parseColor("#6200EE"), "1", 1,  if(getStep(param1+"1") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㄳ\nㄵ", Color.parseColor("#FE9A2E"), "2", 2,  if(getStep(param1+"2") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㄶ\nㄾ\nㅆ", Color.parseColor("#FFBD12"), "3", 3, if(getStep(param1+"3") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner4, "ㅀ", Color.parseColor("#03DAC5"), "4", 1,       if(getStep(param1+"4") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner5, "ㅄ\nㄼ", Color.parseColor("#FF89BB"), "5", 2,    if(getStep(param1+"5") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner2, "ㄻ", Color.parseColor("#6200EE"), "6", 3,       if(getStep(param1+"6") == null)  View.VISIBLE else View.GONE))
            consonant.add(Consonant(R.drawable.rounded_corner3, "ㄺ", Color.parseColor("#FFBD12"), "7", 1,       if(getStep(param1+"7") == null)  View.VISIBLE else View.GONE))
        }

        var soundPool: SoundPool? = null
        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        val soundId1 = soundPool!!.load(context, R.raw.intro2, 1)

        var animclick = AnimationUtils.loadAnimation(context, R.anim.click)

        binding.fmMainView.setBackgroundColor(Color.parseColor(param2))
        //var recycler_view: RecyclerView = findViewById(R.id.);
        binding.recyclerView.apply {

            adapter = ConsonantAdapter(consonant){ consonant, idx, type ->

                soundPool?.play(soundId1, 1.0f, 1.0f, 0, 0, 1.0f)


                if(consonant.visibility != View.VISIBLE)
                {
                    var nextIntent = if(type == "txt") {
                        Intent(context, DetailActivity::class.java)
                    }
                    else {
                        Intent(context, SoundActivity::class.java)
                    }
                    Handler().postDelayed(Runnable {
                        nextIntent.putExtra("Code", consonant.code.toString())
                        nextIntent.putExtra("JsonName", param1.toString())
                        startActivity(nextIntent)

                    }, 210L)
                }


            }
            val layoutManager =  StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.recyclerView.setLayoutManager(layoutManager)
        }


        //binding.textview.text = param1
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

}

