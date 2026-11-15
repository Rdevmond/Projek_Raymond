package com.testing.projek_raymond.Katalog

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class KatalogTabsAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    // Jumlah total tab yang ada
    override fun getItemCount(): Int = 3

    // Menentukan Fragment yang ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AquariumFragment()
            1 -> MakananFragment()
            2 -> AksesorisFragment()
            else -> throw IllegalStateException("Posisi tidak valid: $position")
        }
    }
}
