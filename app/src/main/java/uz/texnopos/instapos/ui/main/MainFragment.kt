package uz.texnopos.instapos.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import uz.texnopos.instapos.data.Settins
import org.koin.android.ext.android.inject
import uz.texnopos.instapos.R
import uz.texnopos.instapos.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val settings: Settins by inject()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings.signedIn = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainer)
        binding.bnv.setupWithNavController(navController)
    }
}