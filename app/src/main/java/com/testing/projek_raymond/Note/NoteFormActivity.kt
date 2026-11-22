package com.testing.projek_raymond.Note

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.testing.projek_raymond.R
import com.testing.projek_raymond.data.AppDatabase
import com.testing.projek_raymond.data.entity.NoteEntity
import com.testing.projek_raymond.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Catatan Belanja"
            subtitle = "Tambahkan data belanjaan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val category = binding.etCategory.text.toString().trim()
            val quantity = binding.etQuantity.text.toString().trim()
            val price = binding.etPrice.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isBlank() || category.isBlank() || quantity.isBlank() || price.isBlank()) {
                Toast.makeText(
                    this, "Harap lengkapi semua data wajib!", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false
            lifecycleScope.launch {
                try {
                    db.noteDao().insert(
                        NoteEntity(
                            title = title,
                            category = category,
                            quantity = quantity,
                            price = price,
                            content = content
                        )
                    )
                    finish()
                } catch (e: Exception) {
                    binding.btnSaveNote.isEnabled = true
                    Toast.makeText(
                        this@NoteFormActivity,
                        "Gagal menyimpan catatan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
