package my.aolika.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import my.aolika.artbook.R
import my.aolika.artbook.databinding.FragmentArtDetailsBinding



class ArtDetailsFragment : Fragment(R.layout.fragment_art_details) {
    private var fragmentBinding : FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding = binding
        binding.artImageDetail.setOnClickListener {
            findNavController()
                .navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                     findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}

