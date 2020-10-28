package com.ayy.wan_android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ayy.wan_android.MyFragment
import com.ayy.wan_android.bean.ArticleChannel

class ViewPagerAdapter(var fragmentManager: FragmentManager, var tabs: List<ArticleChannel>) :
    FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    var fragments: MutableList<Fragment?> = ArrayList(tabs.size)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        if (fragments.size > position) {
            fragment = fragments[position]
        }
        if (fragment == null) {
            fragment = MyFragment.newInstance(60)
            fragments.add(position, fragment)
        }
        return fragment
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].name
    }
}