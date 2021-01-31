package com.h.devlife.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.h.devlife.ui.GifCategoryFragment
import com.h.devlife.ui.GifsType

class PageAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return GifCategoryFragment.newInstance(GifsType.values()[position].titleAPI)
    }

    override fun getCount(): Int {
        return GifsType.values().size
    }

    override fun getPageTitle(position: Int): String? {
        return GifsType.values()[position].title
    }
}