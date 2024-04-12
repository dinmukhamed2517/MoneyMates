package kz.sd.moneymates.onboarding

import android.content.Context
import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentThirdBinding

class ThirdFragment:BaseFragment<FragmentThirdBinding>(FragmentThirdBinding::inflate) {
    override var showBottomNavigation: Boolean = false

    override fun onBindView() {
        super.onBindView()
        binding.finishBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_viewPagerFragment_to_home

            )
            onBoardingFinished()
        }

    }
    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}