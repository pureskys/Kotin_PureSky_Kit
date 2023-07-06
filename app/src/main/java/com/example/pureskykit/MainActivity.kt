package com.example.pureskykit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gongnengFragment = gongneng_Fragment()
        val wodeFragment = wode_Fragment()
        val tab_gongneng = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewpager2)
        val fragmentlist = listOf(gongnengFragment, wodeFragment)
        val textList = listOf("功能", "我的")
//        vp的适配器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentlist[position]
            }
        }
//        tablayout的指示器
        val tabLayoutMediator =
            TabLayoutMediator(tab_gongneng, viewPager2) { tab: TabLayout.Tab, position: Int ->
                tab.text = textList[position]
            }
        tabLayoutMediator.attach()
    }
}
