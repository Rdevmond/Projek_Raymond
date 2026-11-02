package com.testing.projek_raymond.Tutorial

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.testing.projek_raymond.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Siapkan daftar 4 fragment yang menjadi slide tutorial
        val fragments = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment(),
            Tutorial4Fragment()
        )

        // 2. Pasang TutorialFragmentAdapter ke ViewPager2 untuk mengelola slide
        val adapter = TutorialFragmentAdapter(this, fragments)
        binding.tutorialViewPager.adapter = adapter

        // 3. Hubungkan indikator dot (WormDotsIndicator) dengan ViewPager2 agar sinkron saat digeser
        binding.dotIndicator.attachTo(binding.tutorialViewPager)
    }
}
