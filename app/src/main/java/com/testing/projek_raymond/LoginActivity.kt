package com.testing.projek_raymond

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.testing.projek_raymond.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi SharedPreferences untuk menyimpan status login user
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Isi otomatis form jika username & password sudah pernah disimpan lewat Snackbar
        val savedUser = sharedPref.getString("saved_username", null)
        val savedPass = sharedPref.getString("saved_password", null)
        if (!savedUser.isNullOrEmpty()) {
            binding.inptUser.setText(savedUser)
        }
        if (!savedPass.isNullOrEmpty()) {
            binding.inptPass.setText(savedPass)
        }

        // Event klik tombol Login: validasi input username & password
        binding.btnLogin.setOnClickListener {
            val user = binding.inptUser.text.toString()
            val pass = binding.inptPass.text.toString()
            Log.e("Hasil", "Username: $user Password: $pass")

            // Logika validasi: username tidak boleh kosong dan harus sama dengan password
            if (user.isNotEmpty() && user == pass) {
                // Simpan status isLogin = true ke SharedPreferences
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply()

                // Pindah ke MainActivity setelah login berhasil dengan membawa data username & password (seperti di praktikumMobile)
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("username", user)
                    putExtra("password", pass)
                }
                startActivity(intent)
                finish() // Tutup LoginActivity agar user tidak bisa kembali ke halaman login via tombol back
            } else {
                // Tampilkan popup AlertDialog pemberitahuan jika username/password tidak cocok
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }
}