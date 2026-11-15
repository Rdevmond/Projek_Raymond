package com.testing.projek_raymond.Katalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.testing.projek_raymond.R
import com.testing.projek_raymond.databinding.FragmentKatalogBinding

class KatalogFragment : Fragment() {

    private var _binding: FragmentKatalogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Inisialisasi adapter
        val tabsAdapter = KatalogTabsAdapter(this)

        // 2. Pasang adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout dan ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Aquarium"
                    // Tambah ikon
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_aquarium)
                    // Badge dengan angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.text ="new item!!"
                }
                1 -> {
                    tab.text = "Makanan"
                    // Tambah ikon
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_makanan)
                    // Badge dengan angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 6
                }
                2 -> {
                    tab.text = "Aksesoris"
                    // Tambah ikon
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_acessories)
                    // Badge tanpa angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = false
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
