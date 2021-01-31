package com.h.devlife.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.h.devlife.R

import kotlinx.android.synthetic.main.fragment_gif.*


class GifCategoryFragment : Fragment() {

    private lateinit var viewModel: GifCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gifCategory = arguments?.getString(GIF_CATEGORY) ?: GifsType.LATEST.title
        val viewModelFactory = GifCategoryViewModelFactory(activity?.application!!, gifCategory)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GifCategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_gif, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 50f
        circularProgressDrawable.start()

        viewModel.gifUrl.observe(viewLifecycleOwner, Observer {
            checkButtons()
            if(it.isNotEmpty()) {
                content_description.text =
                    viewModel.sortedListFromDb.value?.get(viewModel.gifIndex.value!!)?.description

                Glide.with(requireContext())
                    .load(it)
                    .placeholder(circularProgressDrawable)
                    .into(content_image)
            }
        })

        btn_repeat_download.setOnClickListener {
            viewModel.handleRepeatDownLoadClick()
        }

        checkButtons()
        imBtn_forward.setOnClickListener {
            viewModel.handleForwardClick()
            checkButtons()
        }
        imBtn_back.setOnClickListener {
            viewModel.handleBackClick()
            checkButtons()
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it) {
                ApiStatus.ERROR -> {
                    repeat.visibility = View.VISIBLE
                    checkButtons()
                }
                ApiStatus.DONE -> {
                    repeat.visibility = View.GONE
                    checkButtons()
                }
            }
        })
    }


    private fun checkButtons() {
        if(viewModel.gifIndex.value == (viewModel.listGifs.value?.size)?.minus(1)){
            imBtn_forward.isEnabled = false
            imBtn_forward.background = ResourcesCompat.getDrawable(resources, R.drawable.btn_bg_circle_dark, null)
        }else{
            imBtn_forward.isEnabled = true
            imBtn_forward.background = ResourcesCompat.getDrawable(resources, R.drawable.btn_bg_circle, null)
        }
        if(viewModel.gifIndex.value == 0){
            imBtn_back.isEnabled = false
            imBtn_back.background = ResourcesCompat.getDrawable(resources, R.drawable.btn_bg_circle_dark, null)
        }else {
            imBtn_back.isEnabled = true
            imBtn_back.background = ResourcesCompat.getDrawable(resources, R.drawable.btn_bg_circle, null)
        }
        if(viewModel.listGifs.value?.size == null || viewModel.listGifs.value?.size == 0){
            imBtn_forward.isEnabled = false
            imBtn_forward.background = ResourcesCompat.getDrawable(resources, R.drawable.btn_bg_circle_dark, null)
            imBtn_back.isEnabled = false
            imBtn_back.background = ResourcesCompat.getDrawable(resources, R.drawable.btn_bg_circle_dark, null)
        }

    }

    companion object {
        private const val GIF_CATEGORY = "gif_category"

        fun newInstance(gifCategory : String) : GifCategoryFragment {
            return GifCategoryFragment().apply {
                arguments = bundleOf(GIF_CATEGORY to gifCategory)
            }
        }
    }
}