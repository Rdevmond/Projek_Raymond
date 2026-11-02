package com.testing.projek_raymond.Pengaturan

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.testing.projek_raymond.Komunitas.KomunitasActivity
import com.testing.projek_raymond.LoginActivity
import com.testing.projek_raymond.R
import com.testing.projek_raymond.databinding.ActivityPengaturanBinding

class PengaturanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengaturanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPengaturanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Setup Toolbar dengan tombol navigasi kembali (back button)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pengaturan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // 2. Ambil referensi SharedPreferences untuk manajemen sesi user
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // 3. Tombol Komunitas: Membuka KomunitasActivity untuk melihat diskusi ulasan produk
        binding.btnKomunitas.setOnClickListener {
            val intent = Intent(this, KomunitasActivity::class.java)
            startActivity(intent)
        }

        // 4. Tombol Zona Mancing: Membuka WebViewActivity untuk membuka link web luar
        binding.btnZonaMancing.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // 5. Tombol Logout: Konfirmasi dialog -> hapus sesi di SharedPreferences -> kembali ke LoginActivity
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus seluruh data sesi login
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()

                    // Arahkan kembali ke LoginActivity dan bersihkan tumpukan activity (backstack)
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    // Memuat menu opsi toolbar dari setting_menu.xml
    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return true
    }

    // Handler klik menu pada toolbar: tombol back dan tombol tutorial
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Tombol panah kembali (kembali ke halaman sebelumnya)
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_tutorial -> {
                // Membuka kembali halaman panduan/tutorial aplikasi
                val intent = Intent(this, com.testing.projek_raymond.Tutorial.TutorialActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
