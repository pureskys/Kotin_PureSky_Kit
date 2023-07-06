package com.example.pureskykit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [gongneng_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class gongneng_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_gongneng_, container, false)
        val siyoulujing = activity?.getExternalFilesDir(null).toString()//私有路径地址
        Log.d("a", "本地私有路径: $siyoulujing")
        //获取每日一图方法解析
        val image_meiriyitu = rootview.findViewById<ImageView>(R.id.imageView)
        val meriyitu_url = "https://api.oioweb.cn/api/bing"
        val thread_meiritu = Thread {
            val str_all = net(meriyitu_url)
            try {
                val json_all = JSONObject(str_all)
                val result = json_all.getJSONArray("result")
                val range = (0..result.length()).random()
                val array_1 = result.getJSONObject(range)
                val copyright = array_1.getString("copyright")
                val title = array_1.getString("title")
                val url = array_1.getString("url")
                activity?.runOnUiThread {
                    Glide.with(this)
                        .load(url)
                        .error(R.drawable.meriyitu)
                        .placeholder(R.drawable.meriyitu)
                        .into(image_meiriyitu)
                    Log.d("a", "onCreateView: $copyright$title$url")
                }
            } catch (e: Exception) {
                Log.d("a", "onCreateView:$e")
            }
        }
        thread_meiritu.start()
        //按钮——每日图片切换方法
        val meiritupianqiehuan_anniu = rootview.findViewById<CardView>(R.id.meiritupianqiehuan)
            meiritupianqiehuan_anniu.setOnClickListener {
                val thread_meiritu_annniu = Thread {
                    val str_all = net(meriyitu_url)
                    try {
                        val json_all = JSONObject(str_all)
                        val result = json_all.getJSONArray("result")
                        val range = (0..result.length()).random()
                        val array_1 = result.getJSONObject(range)
                        val copyright = array_1.getString("copyright")
                        val title = array_1.getString("title")
                        val url = array_1.getString("url")
                        activity?.runOnUiThread {
                            Glide.with(this)
                                .load(url)
                                .error(R.drawable.meriyitu)
                                .placeholder(R.drawable.meriyitu)
                                .into(image_meiriyitu)
                            Log.d("a", "onCreateView: $copyright$title$url")
                        }
                    } catch (e: Exception) {
                        Log.d("a", "onCreateView:$e")
                    }
                }
                thread_meiritu_annniu.start()
            }

        return rootview
    }


    fun net(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().body.string()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment gongneng_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            gongneng_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}