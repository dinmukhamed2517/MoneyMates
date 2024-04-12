package kz.sd.moneymates.onboarding

import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentSecondBinding

class SecondFragment:BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {
    override var showBottomNavigation: Boolean = false

    override fun onBindView() {
        super.onBindView()
        binding.skipBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_viewPagerFragment_to_home
            )
        }
    }
}