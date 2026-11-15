package com.testing.projek_raymond.Katalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.testing.projek_raymond.databinding.FragmentMakananBinding

class MakananFragment : Fragment() {

    private var _binding: FragmentMakananBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        ProductModel(
            name = "Pelet Premium Channa Monster 250gr",
            price = "Rp 65.000",
            imageUrl = "https://picsum.photos/seed/fishfood1/400/300",
            description = "Pelet apung dengan spirulina tinggi & astaxanthin untuk mempertajam bunga dan warna bar channa."
        ),
        ProductModel(
            name = "Cacing Sutra Kering Tubifex Worms Cube",
            price = "Rp 35.000",
            imageUrl = "https://picsum.photos/seed/fishfood2/400/300",
            description = "Sumber protein murni 58% melalui proses freeze-dried higienis bebas bakteri dan parasit."
        ),
        ProductModel(
            name = "TetraBits Complete Granules 300ml",
            price = "Rp 98.000",
            imageUrl = "https://picsum.photos/seed/fishfood3/400/300",
            description = "Pakan perlahan tenggelam formula Jerman khusus ikan discus, tetra, dan angel fish."
        ),
        ProductModel(
            name = "Pelet Louhan Head Up & Color Booster",
            price = "Rp 85.000",
            imageUrl = "https://picsum.photos/seed/fishfood4/400/300",
            description = "Memacu pertumbuhan jenong louhan dan kilau mutiara secara optimal tanpa bahan kimia berbahaya."
        ),
        ProductModel(
            name = "Artemia Kista Shell-Free Decapsulated 50gr",
            price = "Rp 45.000",
            imageUrl = "https://picsum.photos/seed/fishfood5/400/300",
            description = "Pakan alami burayak ikan tanpa perlu dikultur, langsung tabur kaya nutrisi pertumbuhan."
        ),
        ProductModel(
            name = "Spirulina Flakes Herbivore Fish 100gr",
            price = "Rp 50.000",
            imageUrl = "https://picsum.photos/seed/fishfood6/400/300",
            description = "Serpihan kaya serat tumbuhan alami untuk ikan pemakan alga, molly, guppy, dan pleco."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakananBinding.inflate(inflater, container, false)
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
