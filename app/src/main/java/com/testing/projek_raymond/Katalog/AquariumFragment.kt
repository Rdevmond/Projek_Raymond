package com.testing.projek_raymond.Katalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.testing.projek_raymond.databinding.FragmentAquariumBinding

class AquariumFragment : Fragment() {

    private var _binding: FragmentAquariumBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        ProductModel(
            name = "Aquarium Optic Clear 60cm",
            price = "Rp 450.000",
            imageUrl = "https://picsum.photos/seed/aqua1/400/300",
            description = "Kaca ultra-clear 8mm minim distorsi warna, cocok untuk aquascape tingkat lanjut."
        ),
        ProductModel(
            name = "Aquarium Rimless Nano Tank 30cm",
            price = "Rp 185.000",
            imageUrl = "https://picsum.photos/seed/aqua2/400/300",
            description = "Desain tanpa bingkai lem rapi transparan, ideal untuk ikan cupang atau udang hias."
        ),
        ProductModel(
            name = "Aquarium Kaca Lengkung Bending 40cm",
            price = "Rp 275.000",
            imageUrl = "https://picsum.photos/seed/aqua3/400/300",
            description = "Sudut depan melengkung mulus tanpa sambungan lem, sudut pandang panoramik."
        ),
        ProductModel(
            name = "Mega Tank Predator 150cm (Kaca 12mm)",
            price = "Rp 2.800.000",
            imageUrl = "https://picsum.photos/seed/aqua4/400/300",
            description = "Konstruksi kokoh dengan sabuk penguat ganda khusus ikan predator arwana & channa."
        ),
        ProductModel(
            name = "Paludarium Terarium Kaca Miring 50cm",
            price = "Rp 350.000",
            imageUrl = "https://picsum.photos/seed/aqua5/400/300",
            description = "Kombinasi ekosistem darat dan air, dilengkapi ventilasi anti embun."
        ),
        ProductModel(
            name = "Aquarium Mini Desktop USB LED",
            price = "Rp 120.000",
            imageUrl = "https://picsum.photos/seed/aqua6/400/300",
            description = "Ukuran kompak bertenaga USB dengan pompa sirkulasi mini terintegrasi untuk meja kerja."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAquariumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productAdapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Anda memilih: ${selectedItem.name}\nHarga: ${selectedItem.price}\n${selectedItem.description}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvProducts.apply {
            // Mode Grid 2 kolom
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productAdapter
        }

        /*

        setupCardClickListeners(view)
        */
    }

    /*
    private fun setupCardClickListeners(view: View) {
        val cards = mutableListOf<com.google.android.material.card.MaterialCardView>()
        findAllCards(view, cards)

        cards.forEachIndexed { index, card ->
            card.setOnClickListener {
                val textViews = mutableListOf<android.widget.TextView>()
                findAllTextViews(card, textViews)
                val itemName = textViews.getOrNull(0)?.text ?: "Item ${index + 1}"
                val itemSize = textViews.getOrNull(1)?.text ?: ""
                Toast.makeText(
                    requireContext(),
                    "$itemName\n$itemSize",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun findAllCards(view: View, result: MutableList<com.google.android.material.card.MaterialCardView>) {
        if (view is com.google.android.material.card.MaterialCardView) {
            result.add(view)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findAllCards(view.getChildAt(i), result)
            }
        }
    }

    private fun findAllTextViews(view: View, result: MutableList<android.widget.TextView>) {
        if (view is android.widget.TextView) {
            result.add(view)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findAllTextViews(view.getChildAt(i), result)
            }
        }
    }
    */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
