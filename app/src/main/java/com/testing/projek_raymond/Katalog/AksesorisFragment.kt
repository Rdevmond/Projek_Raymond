package com.testing.projek_raymond.Katalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.testing.projek_raymond.R

class AksesorisFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_aksesoris, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCardClickListeners(view)
    }

    private fun setupCardClickListeners(view: View) {
        val cards = mutableListOf<MaterialCardView>()
        findAllCards(view, cards)

        cards.forEachIndexed { index, card ->
            card.setOnClickListener {
                val textViews = mutableListOf<TextView>()
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

    private fun findAllCards(view: View, result: MutableList<MaterialCardView>) {
        if (view is MaterialCardView) {
            result.add(view)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findAllCards(view.getChildAt(i), result)
            }
        }
    }

    private fun findAllTextViews(view: View, result: MutableList<TextView>) {
        if (view is TextView) {
            result.add(view)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findAllTextViews(view.getChildAt(i), result)
            }
        }
    }
}
