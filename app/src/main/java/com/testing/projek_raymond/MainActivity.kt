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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Cari item.."
            setDisplayHomeAsUpEnabled(false)
        }

        replaceFragment(AquariumFragment(), addToBackStack = false)

        binding.btnFragment1.setOnClickListener {
            replaceFragment(AquariumFragment())
        }
        binding.btnFragment2.setOnClickListener {
            replaceFragment(MakananFragment())
        }
        binding.btnFragment3.setOnClickListener {
            replaceFragment(AksesorisFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_katalog -> {
                    binding.tabKategori.visibility = android.view.View.VISIBLE
                    replaceFragment(AquariumFragment())
                    true
                }
                R.id.nav_keranjang -> {
                    binding.tabKategori.visibility = android.view.View.GONE
                    replaceFragment(KeranjangFragment())
                    true
                }
                R.id.nav_status_pesanan -> {
                    binding.tabKategori.visibility = android.view.View.GONE
                    replaceFragment(StatusPesananFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
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
