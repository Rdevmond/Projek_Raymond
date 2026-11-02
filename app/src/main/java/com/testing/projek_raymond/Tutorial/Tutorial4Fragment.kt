package com.testing.projek_raymond.Tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.testing.projek_raymond.LoginActivity
import com.testing.projek_raymond.MainActivity
import com.testing.projek_raymond.databinding.FragmentTutorial4Binding

class Tutorial4Fragment : Fragment() {

    private var _binding: FragmentTutorial4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorial4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLewati.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("isTutorialFinished", true).apply()

            val isLogin = sharedPref.getBoolean("isLogin", false)
            val intent = if (isLogin) {
                Intent(requireContext(), MainActivity::class.java)
            } else {
                Intent(requireContext(), LoginActivity::class.java)
            }
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
