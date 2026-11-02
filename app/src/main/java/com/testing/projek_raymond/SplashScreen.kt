package com.testing.projek_raymond

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Ambil data sesi dari SharedPreferences "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)
        val isTutorialFinished = sharedPref.getBoolean("isTutorialFinished", false)

        // 2. Jika user sudah login sebelumnya, langsung alihkan ke MainActivity tanpa delay
        if (isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        // 3. Jika belum login, beri jeda splash screen 2 detik
        lifecycleScope.launch {
            delay(2000)
            // Cek apakah tutorial sudah pernah diselesaikan:
            // - Jika belum selesai -> diarahkan ke TutorialActivity
            // - Jika sudah selesai -> diarahkan ke LoginActivity
            val intent = if (!isTutorialFinished) {
                Intent(this@SplashScreen, com.testing.projek_raymond.Tutorial.TutorialActivity::class.java)
            } else {
                Intent(this@SplashScreen, LoginActivity::class.java)
            }
            startActivity(intent)
            finish() // Tutup SplashScreen agar tidak bisa kembali ke sini
        }
    }
}
