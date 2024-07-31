package com.example.reddittoppublications.ui.main

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.reddittoppublications.R

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val imageView: ImageView = findViewById(R.id.ivImage)

        val imageUrl = intent.getStringExtra("IMAGE_URL")
        val urlCorrected = imageUrl?.replace("&amp;", "&")

        Glide.with(this)
            .load(urlCorrected)
            .into(imageView)
    }
}
