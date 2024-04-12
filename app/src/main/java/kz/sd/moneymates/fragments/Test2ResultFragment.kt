package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentTest2ResultBinding

class Test2ResultFragment:BaseFragment<FragmentTest2ResultBinding>(FragmentTest2ResultBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_test2ResultFragment_to_test3Fragment)
        }
    }

}