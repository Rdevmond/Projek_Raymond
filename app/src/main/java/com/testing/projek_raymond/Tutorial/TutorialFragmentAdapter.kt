package com.testing.projek_raymond.Tutorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter ViewPager2 menggunakan FragmentStateAdapter untuk menampilkan slide tutorial secara dinamis.
 */
class TutorialFragmentAdapter(
    activity: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(activity) {

    // Mengembalikan jumlah total halaman/slide tutorial
    override fun getItemCount(): Int = fragments.size

    // Mengembalikan fragment yang sesuai dengan posisi index slide saat ini
    override fun createFragment(position: Int): Fragment = fragments[position]
}
