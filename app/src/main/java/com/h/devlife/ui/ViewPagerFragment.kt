package com.h.devlife.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.h.devlife.MainActivity
import com.h.devlife.R
import com.h.devlife.adapters.PageAdapter
import com.h.devlife.extensions.dpToIntPx
import kotlinx.android.synthetic.main.fragment_view_pager.*


class ViewPagerFragment : Fragment() {
    private lateinit var gifsPageAdapter: PageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gifsPageAdapter = PageAdapter(childFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        view_pager.adapter = gifsPageAdapter
        tabs.setupWithViewPager(view_pager)
    }

    private fun setupToolbar() {
        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        val logo = if(toolbar.childCount == 2)
            toolbar.getChildAt(1) as? ImageView
        else null
        logo?.scaleType = ImageView.ScaleType.CENTER_CROP
        val lp = logo?.layoutParams as Toolbar.LayoutParams
        lp?.let {
            it.width = this.dpToIntPx(40)
            it.height = this.dpToIntPx(40)
            it.marginEnd = this.dpToIntPx(16)
            logo.layoutParams = it
        }
    }

}