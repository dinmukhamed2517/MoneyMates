package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentTest1ResultBinding


class Test1ResultFragment :BaseFragment<FragmentTest1ResultBinding>(FragmentTest1ResultBinding::inflate){
    override fun onBindView() {
        super.onBindView()
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_test1ResultFragment_to_test2Fragment)
        }
    }

}