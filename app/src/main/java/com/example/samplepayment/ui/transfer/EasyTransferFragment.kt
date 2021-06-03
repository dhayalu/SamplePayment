package com.example.samplepayment.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.samplepayment.R
import com.example.samplepayment.databinding.FragmentEasyTransferBinding
import kotlinx.android.synthetic.main.fragment_easy_transfer.*

class EasyTransferFragment : Fragment() {

    private lateinit var easyTransferViewModel: EasyTransferViewModel
    private var _binding: FragmentEasyTransferBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        easyTransferViewModel =
            ViewModelProvider(this).get(EasyTransferViewModel::class.java)

        _binding = FragmentEasyTransferBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_frame.setOnClickListener {

            view.findNavController().navigate(R.id.action_nav_easy_transfer_to_nav_contact)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}