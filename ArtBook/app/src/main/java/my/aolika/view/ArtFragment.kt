package my.aolika.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import my.aolika.artbook.R
import my.aolika.artbook.databinding.FragmentArtsBinding

class ArtFragment : Fragment(R.layout.fragment_arts) {

    private var fragmentBinding : FragmentArtsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding
        binding.floatingAdd.setOnClickListener {
            findNavController()
                .navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }

    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}