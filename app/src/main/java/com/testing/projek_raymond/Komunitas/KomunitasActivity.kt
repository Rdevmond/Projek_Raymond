package com.testing.projek_raymond.Komunitas

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.testing.projek_raymond.R
import com.testing.projek_raymond.databinding.ActivityKomunitasBinding

class KomunitasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKomunitasBinding

    private val messageList = listOf(
        MessageModel(
            "Pak Joko - Pengrajin Umpan Kayu",
            "Pelampung dan umpan handmade kayu jati buatan saya sangat cocok dipadukan dengan aksesoris dari AquaMarine Store!",
            "https://i.pravatar.cc/150?img=11",
            "Seorang pengrajin umpan pancing tradisional asal pesisir yang telah berkarya lebih dari 15 tahun menciptakan minnow kayu handmade ramah lingkungan."
        ),
        MessageModel(
            "Budi Santoso - Aquascaper Pro",
            "Kaca akuarium 2cm di toko ini sangat jernih dan kokoh, rekomendasi terbaik untuk hardscape batu berat.",
            "https://i.pravatar.cc/150?img=12",
            "Penghobi dan desainer aquascape profesional, sering menjuarai kompetisi aquascape regional dan aktif membagikan tips filterisasi air."
        ),
        MessageModel(
            "Citra Lestari - Pengrajin Jaring Pancing",
            "Jaring rajut nilon custom saya pas banget dipakai untuk serokan ikan hias di etalase AquaMarine.",
            "https://i.pravatar.cc/150?img=5",
            "Pengrajin rajut tangan aksesoris pancing dan jaring ikan hias dengan sentuhan simpul etnik Nusantara."
        ),
        MessageModel(
            "Dika Anggara - Pemancing Liar",
            "Pengiriman pelet dan nutrisi ikannya cepat sampai. Ikan channa di rumah jadi makin aktif dan sehat warnanya.",
            "https://i.pravatar.cc/150?img=8",
            "Angler kawakan yang aktif menjelajah spot-spot mancing muara dan danau di seluruh Sumatera."
        ),
        MessageModel(
            "Fajar Pratama - Builder Rak Aquarium",
            "Desain toko dan variasi peralatannya lengkap banget. Salam sukses sesama pecinta biota laut!",
            "https://i.pravatar.cc/150?img=60",
            "Spesialis perakit meja dan rak akuarium hollow galvanis anti karat untuk skala rumahan maupun pameran."
        ),
        MessageModel(
            "Hendra Wijaya - Master Rod Custom",
            "Joran pancing carbon custom racikan saya sangat presisi saat dipasangkan dengan reel dari toko ini.",
            "https://i.pravatar.cc/150?img=33",
            "Spesialis pembungkus guide joran pancing turnamen dengan benang sutra motif batik."
        ),
        MessageModel(
            "Gita Permata - Nano Tank Enthusiast",
            "Lampu LED akuariumnya pas sekali spektrum cahayanya untuk tanaman karpet cuba.",
            "https://i.pravatar.cc/150?img=25",
            "Kolektor tanaman air langka dan penghobi nano tank mini di apartemen."
        ),
        MessageModel(
            "Irfan Maulana - Teknisi Pompa & Filter",
            "Media filter biologis keramik ring di sini pori-porinya padat dan awet.",
            "https://i.pravatar.cc/150?img=15",
            "Teknisi instalasi filter kolam koi dan sistem sirkulasi akuarium laut modern."
        ),
        MessageModel(
            "Joko Susilo - Pembuat Kail Handmade",
            "Kail pancing bahan baja karbon tinggi buatan saya sudah teruji mengangkat monster air tawar.",
            "https://i.pravatar.cc/150?img=52",
            "Pandai besi pengrajin kail mata pancing manual yang telah memasok ke berbagai komunitas angler nusantara."
        ),
        MessageModel(
            "Rian Akbar - Breeder Ikan Predator",
            "Pakan hidup dan pelet tenggelamnya sangat berkualitas, pertumbuhan ikan jadi jauh lebih pesat.",
            "https://i.pravatar.cc/150?img=68",
            "Pembudidaya ikan predator air tawar eksotis dan aktif mengedukasi pelestarian ikan endemik lokal."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityKomunitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Komunitas & Ulasan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        val adapter = MessageAdapter(this, messageList)
        binding.listMessageItems.adapter = adapter
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
