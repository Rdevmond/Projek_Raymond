package com.testing.projek_raymond.Katalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.testing.projek_raymond.databinding.FragmentAksesorisBinding

class AksesorisFragment : Fragment() {

    private var _binding: FragmentAksesorisBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        ProductModel(
            name = "Lampu Aquascape WRGB Slim 60cm",
            price = "Rp 320.000",
            imageUrl = "https://picsum.photos/seed/acc1/400/300",
            description = "Spektrum warna penuh dengan kontrol dimmer untuk fotosintesis tanaman karpet aquascape."
        ),
        ProductModel(
            name = "Canister Filter Eksternal 800L/H",
            price = "Rp 540.000",
            imageUrl = "https://picsum.photos/seed/acc2/400/300",
            description = "Sistem filtrasi biologis 3 tahap dengan mesin hening dan konsumsi daya rendah 15 watt."
        ),
        ProductModel(
            name = "Joran Pancing Carbon Solid 180cm",
            price = "Rp 210.000",
            imageUrl = "https://picsum.photos/seed/acc3/400/300",
            description = "Bahan blank karbon padat kelenturan medium-heavy, cocok untuk casting predator muara."
        ),
        ProductModel(
            name = "Reel Spinning 3000 Aluminium Spool",
            price = "Rp 385.000",
            imageUrl = "https://picsum.photos/seed/acc4/400/300",
            description = "Sistem 9+1 ball bearing tahan air asin, drag power 8kg halus tanpa tersendat."
        ),
        ProductModel(
            name = "Set Pinset & Gunting Aquascape Stainless",
            price = "Rp 95.000",
            imageUrl = "https://picsum.photos/seed/acc5/400/300",
            description = "Baja tahan karat hitam matte 27cm, presisi untuk menanam bibit tanaman air mikro."
        ),
        ProductModel(
            name = "Diffuser CO2 Keramik Akrilik Ultra-Fine",
            price = "Rp 60.000",
            imageUrl = "https://picsum.photos/seed/acc6/400/300",
            description = "Menghasilkan gelembung kabut CO2 super halus yang mudah larut dalam air akuarium."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAksesorisBinding.inflate(inflater, container, false)
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
