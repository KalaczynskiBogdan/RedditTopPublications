package com.example.reddittoppublications.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reddittoppublications.R
import com.example.reddittoppublications.ui.image.ImageFullFragment
import com.example.reddittoppublications.ui.image.ImageFullViewModel
import com.example.reddittoppublications.ui.main.TopPublicationsFragment
import com.example.reddittoppublications.ui.main.TopPublicationsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val topPublicationsViewModel: TopPublicationsViewModel by viewModels()
    private val imageFullViewModel: ImageFullViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            if (imageFullViewModel.wasFragmentActive()) {
                val savedPhotoId = imageFullViewModel.getSavedPhotoId()
                savedPhotoId?.let {
                    openImageFullFragment(it)
                }
            } else {
                openDefaultFragment()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun openImageFullFragment(photoId: String) {
        val fragment = ImageFullFragment.newInstance(photoId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()
    }

    private fun openDefaultFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, TopPublicationsFragment())
            .commit()
    }
}