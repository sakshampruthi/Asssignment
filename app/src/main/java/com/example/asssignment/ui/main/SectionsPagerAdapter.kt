package com.example.asssignment.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.asssignment.R
import com.example.asssignment.homeui.Contactus
import com.example.asssignment.homeui.Images
import com.example.asssignment.homeui.ViewImages

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    val tab1 = Contactus()
    val tab2 = Images()
    val tab3 = ViewImages()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> tab1
            1 -> tab2
            2 -> tab3
            else -> Contactus()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }
}