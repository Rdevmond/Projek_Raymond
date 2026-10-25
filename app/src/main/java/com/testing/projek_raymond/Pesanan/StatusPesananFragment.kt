package com.testing.projek_raymond.Pesanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.testing.projek_raymond.databinding.FragmentStatusPesananBinding

class StatusPesananFragment : Fragment() {
    private var _binding: FragmentStatusPesananBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentStatusPesananBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"), // key yang diambil dari data
            intArrayOf(android.R.id.text1, android.R.id.text2) // tempat tampilnya
        )
        binding.listViewItems.adapter = adapter

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
        _binding = null
    }
}
