package com.testing.projek_raymond.Komunitas

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.testing.projek_raymond.R
import com.testing.projek_raymond.databinding.ActivityProfileKomentatorBinding

class ProfileKomentatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileKomentatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProfileKomentatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Profil Komentator"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        val nama = intent.getStringExtra("SENDER")
        val pesan = intent.getStringExtra("PESAN")
        val bio = intent.getStringExtra("BIO")
        val avatar = intent.getStringExtra("AVATAR")

        binding.txtNama.text = nama
        binding.txtPesan.text = pesan
        if (!bio.isNullOrEmpty()) {
            binding.txtBio.text = bio
        }

        if (!avatar.isNullOrEmpty()) {
            Glide.with(this)
                .load(avatar)
                .into(binding.imgAvatar)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
