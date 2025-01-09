package com.example.goalplus.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.goalplus.R
import com.example.goalplus.data.dto.Score

@BindingAdapter("load_image_url")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let {
        imageView.loadImageUrl(url)
    } ?: run {
        imageView.setImageResource(R.drawable.ballpholder)
    }
}

@BindingAdapter("load_score")
fun loadScore(textView: TextView, score: Int?) {
    score?.let {
        textView.text = score.toString()
    } ?: run {
        textView.text = ""
    }
}

@BindingAdapter("txt_visibility")
fun setVisibility(textView: TextView,homeScore: Int?){
    if (homeScore==null ){
      textView.visible()
    }else{
        textView.invisible()
    }
}



