package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentTest3ResultBinding

class Test3ResultFragment:BaseFragment<FragmentTest3ResultBinding>(FragmentTest3ResultBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_test3ResultFragment_to_testResultFragment)
        }
    }
}