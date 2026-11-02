package com.testing.projek_raymond.Pesanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.testing.projek_raymond.databinding.FragmentStatusPesananBinding

/**
 * =========================================================================
 * IMPLEMENTASI LISTVIEW MENGGUNAKAN SIMPLEADAPTER (STATUS PESANAN)
 * =========================================================================
 * Fragment ini menampilkan riwayat pesanan menggunakan komponen ListView.
 * ListView diisi dengan SimpleAdapter bawaan Android tanpa perlu membuat file adapter terpisah.
 */
class StatusPesananFragment : Fragment() {
    private var _binding: FragmentStatusPesananBinding? = null
    private val binding get() = _binding!!

    // Sumber Data (Data Source):
    // Kumpulan data berupa List of Map. Setiap Map mewakili 1 baris item dengan key "title" dan "desc".
    private val dataListWithDesc = listOf(
        mapOf("title" to "Pesanan #AQ-9821: Aquarium Kaca 2cm", "desc" to "Status: Sedang Dikirim - Kurir Ekspedisi Kargo (Estimasi 2 Hari)"),
        mapOf("title" to "Pesanan #PK-4412: Pelet Ikan Predator 250gr", "desc" to "Status: Selesai - Diterima pada 24 Oktober 2026"),
        mapOf("title" to "Pesanan #AK-3301: Lampu LED & Filter Aquascape", "desc" to "Status: Dalam Proses Packing - Gudang AquaMarine"),
        mapOf("title" to "Pesanan #JR-1120: Joran Carbon Custom Batik", "desc" to "Status: Menunggu Konfirmasi Penjual"),
        mapOf("title" to "Pesanan #FL-7765: Keramik Ring & Bio Ball 1kg", "desc" to "Status: Selesai - Diterima pada 20 Oktober 2026")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout fragment_status_pesanan.xml yang berisi widget <ListView android:id="@+id/listViewItems" />
        _binding = FragmentStatusPesananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // =====================================================================
        // CARA KERJA ADAPTER LISTVIEW (SimpleAdapter):
        // Adapter berfungsi sebagai "jembatan" antara sumber data dan ListView.
        // Parameter:
        // 1. Context: requireContext()
        // 2. Data: dataListWithDesc (kumpulan Map data)
        // 3. Layout baris: android.R.layout.simple_list_item_2 (layout bawaan Android dengan 2 TextView: text1 dan text2)
        // 4. Array String "from": key pada Map yang akan diambil datanya ("title", "desc")
        // 5. Array Int "to": ID TextView tujuan di layout baris (text1 untuk title, text2 untuk desc)
        // =====================================================================
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        // Pasang adapter ke widget ListView agar item ter-render di layar
        binding.listViewItems.adapter = adapter

        // =====================================================================
        // PENANGANAN KLIK ITEM LISTVIEW (setOnItemClickListener):
        // Ketika salah satu baris di ListView diklik oleh user:
        // - 'position': index urutan baris yang diklik (mulai dari 0)
        // - Ambil Map data sesuai index, lalu tampilkan info detailnya lewat Toast
        // =====================================================================
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Bersihkan binding saat view dihancurkan untuk mencegah memory leak
        _binding = null
    }
}
