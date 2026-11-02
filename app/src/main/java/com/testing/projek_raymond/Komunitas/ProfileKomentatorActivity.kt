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

        // 1. Setup Toolbar dengan judul dan tombol kembali
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Profil Komentator"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // 2. Menerima data yang dikirim melalui Intent Extra dari MessageAdapter
        val nama = intent.getStringExtra("SENDER")
        val pesan = intent.getStringExtra("PESAN")
        val bio = intent.getStringExtra("BIO")
        val avatar = intent.getStringExtra("AVATAR")

        // 3. Menampilkan data ke komponen UI (TextView)
        binding.txtNama.text = nama
        binding.txtPesan.text = pesan
        if (!bio.isNullOrEmpty()) {
            binding.txtBio.text = bio
        }

        // 4. Memuat foto profil pengirim dari URL menggunakan Glide
        if (!avatar.isNullOrEmpty()) {
            Glide.with(this)
                .load(avatar)
                .into(binding.imgAvatar)
        }
    }

    // 5. Handler tombol Back pada toolbar (kembali ke KomunitasActivity)
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
