package com.example.pureskykit

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [wode_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class wode_Fragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_wode_, container, false)

//        自定义函数事件填写处
        val zhutiqiehuan = rootView.findViewById<CardView>(R.id.zhutiqiehuan)//绑定主题切换
        val juanzeng = rootView.findViewById<CardView>(R.id.juanzengmindan)//绑定捐赠名单
        val shiyongbangzhu =rootView.findViewById<CardView>(R.id.shiyongbangzhu)//绑定使用帮助
        val yijianfankui = rootView.findViewById<CardView>(R.id.yijianfankui)//绑定意见反馈
        val fenxiangruanjian = rootView.findViewById<CardView>(R.id.fenxiangruanjian)//绑定分享软件
        val guanyuruanjian = rootView.findViewById<CardView>(R.id.guanyuruanjian)//绑定关于软件
        /////........................................................................
        zhutiqiehuan.setOnClickListener {
            Toast.makeText(rootView.context,"施工中...",Toast.LENGTH_SHORT).show()
            //TODO:主题切换
        }
        /////........................................................................
        juanzeng.setOnClickListener {
            Toast.makeText(rootView.context,"施工中...",Toast.LENGTH_SHORT).show()
            //TODO：捐赠
        }
        /////........................................................................
        shiyongbangzhu.setOnClickListener {
            Toast.makeText(rootView.context,"施工中...",Toast.LENGTH_SHORT).show()
            //TODO：使用帮助
        }
        /////........................................................................
        yijianfankui.setOnClickListener {
            val url_fankui = "https://f.wps.cn/ksform/w/write/vXOAfagm"//反馈表单地址
            //意见反馈方法弹窗
            val dialog = AlertDialog.Builder(rootView.context)
                .setTitle("意见反馈")
                .setMessage("遇到bug或者有什么好的意见请反馈给我吧！！")
                .setIcon(R.drawable.renwu)
                .setNeutralButton("点错了...") { _, _ ->
                    Toast.makeText(
                        rootView.context,
                        "好嘞！有什么问题记得联系我哦~~",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setPositiveButton("出发->反馈") { _, _ ->
                    val clipboard =
                        requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData = ClipData.newPlainText("url_text", url_fankui)
                    clipboard.setPrimaryClip(clipData)
                    val uri = Uri.parse(url_fankui)
                    val intent = Intent()
                    intent.action = "android.intent.action.VIEW"
                    intent.data = uri
                    startActivity(intent)
                    Toast.makeText(
                        requireContext(),
                        "已复制反馈地址并跳转到浏览器，可以反馈噜~",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .create()
            dialog.show()
        }
        /////........................................................................
        fenxiangruanjian.setOnClickListener {
            Toast.makeText(rootView.context,"施工中...",Toast.LENGTH_SHORT).show()
//            TODO：分享软件
        }
        /////........................................................................
        guanyuruanjian.setOnClickListener {
            Toast.makeText(rootView.context,"施工中...",Toast.LENGTH_SHORT).show()
//            TODO：关于软件
        }
        /////........................................................................


        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment wode_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            wode_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}

