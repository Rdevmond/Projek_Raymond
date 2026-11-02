package com.testing.projek_raymond

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.testing.projek_raymond.Katalog.AksesorisFragment
import com.testing.projek_raymond.Katalog.AquariumFragment
import com.testing.projek_raymond.Katalog.MakananFragment
import com.testing.projek_raymond.Pengaturan.PengaturanActivity
import com.testing.projek_raymond.Pesanan.KeranjangFragment
import com.testing.projek_raymond.Pesanan.StatusPesananFragment
import com.testing.projek_raymond.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ==========================================
        // 1. IMPLEMENTASI TOOLBAR (ACTION BAR)
        // ==========================================
        // Menetapkan widget Toolbar dari layout sebagai ActionBar resmi untuk activity ini
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Cari item.."
            setDisplayHomeAsUpEnabled(false) // false karena ini adalah halaman utama (tidak ada tombol back)
        }

        // ==========================================
        // 2. IMPLEMENTASI FRAGMENT DEFAULT
        // ==========================================
        // Memuat fragment awal ke dalam container (FrameLayout: fragment_container) saat aplikasi pertama kali dibuka
        replaceFragment(AquariumFragment(), addToBackStack = false)

        // ==========================================
        // 3. IMPLEMENTASI CHIP GROUP (FILTER KATALOG)
        // ==========================================
        // Mendengarkan perubahan pilihan chip filter (Aquarium, Makanan, Aksesoris)
        // Setiap chip akan mengganti isi fragment_container dengan fragment katalog yang sesuai
        binding.chipGroupKategori.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                R.id.chipAquarium -> replaceFragment(AquariumFragment())
                R.id.chipMakanan -> replaceFragment(MakananFragment())
                R.id.chipAksesoris -> replaceFragment(AksesorisFragment())
            }
        }

        // ==========================================
        // 4. IMPLEMENTASI BOTTOM NAVIGATION BAR
        // ==========================================
        // Mengatur navigasi antar modul halaman utama via menu BottomNavigationView:
        // - nav_katalog: Menampilkan filter chip dan memuat AquariumFragment
        // - nav_keranjang: Menyembunyikan filter chip dan memuat KeranjangFragment
        // - nav_status_pesanan: Menyembunyikan filter chip dan memuat StatusPesananFragment
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_katalog -> {
                    binding.chipGroupKategori.visibility = android.view.View.VISIBLE
                    binding.chipAquarium.isChecked = true
                    replaceFragment(AquariumFragment())
                    true
                }
                R.id.nav_keranjang -> {
                    binding.chipGroupKategori.visibility = android.view.View.GONE
                    replaceFragment(KeranjangFragment())
                    true
                }
                R.id.nav_status_pesanan -> {
                    binding.chipGroupKategori.visibility = android.view.View.GONE
                    replaceFragment(StatusPesananFragment())
                    true
                }
                else -> false
            }
        }

        // ==========================================
        // 5. IMPLEMENTASI SNACKBAR SIMPAN AKUN (DARI LOGIN)
        // ==========================================
        // Menerima data username & password yang dikirim via Intent Extra dari LoginActivity
        val user = intent.getStringExtra("username")
        val pass = intent.getStringExtra("password")

        // Jika user baru saja login (data ada), tampilkan notifikasi Snackbar
        if (!user.isNullOrEmpty() && !pass.isNullOrEmpty()) {
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            Snackbar.make(binding.root, "Simpan username dan password?", Snackbar.LENGTH_LONG)
                .setAction("Simpan") {
                    // Menyimpan kredensial ke SharedPreferences agar bisa autofill saat login berikutnya
                    val editor = sharedPref.edit()
                    editor.putString("saved_username", user)
                    editor.putString("saved_password", pass)
                    editor.apply()
                    Toast.makeText(this, "Username dan password berhasil disimpan!", Toast.LENGTH_SHORT).show()
                }
                .setAnchorView(binding.bottomNavView) // Menempatkan posisi Snackbar tepat di atas BottomNavView
                .show()
        }
    }

    /**
     * ==========================================
     * FUNGSI PENGELOLA TRANSAKSI FRAGMENT
     * ==========================================
     * Bertanggung jawab menukar (replace) Fragment di dalam FrameLayout (binding.fragmentContainer.id).
     * @param fragment Objek fragment tujuan yang ingin ditampilkan
     * @param addToBackStack Apakah transaksi dicatat di backstack (agar bisa kembali via tombol back)
     */
    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * ==========================================
     * INFLATE MENU PADA TOOLBAR
     * ==========================================
     * Menghubungkan file menu XML (res/menu/main_menu.xml) ke dalam Toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * ==========================================
     * PENANGANAN KLIK MENU TOOLBAR
     * ==========================================
     * Menangani event klik untuk setiap icon/item di Toolbar
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                // Berpindah ke PengaturanActivity menggunakan Intent
                val intent = Intent(this, PengaturanActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_profile -> {
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
