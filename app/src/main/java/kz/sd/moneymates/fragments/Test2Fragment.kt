package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentTest2Binding

class Test2Fragment:BaseFragment<FragmentTest2Binding>(FragmentTest2Binding::inflate) {
    override fun onBindView() {
        super.onBindView()
        binding.answerA.setOnClickListener {
            showCustomCancelDialog("Incorrect", "Try again")

        }
        binding.answerB.setOnClickListener {
            showCustomCancelDialog("Incorrect", "Try again")

        }
        binding.answerC.setOnClickListener {
            findNavController().navigate(R.id.action_test2Fragment_to_test2ResultFragment)
        }
    }

}