package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentTest3Binding

class Test3Fragment:BaseFragment<FragmentTest3Binding>(FragmentTest3Binding::inflate) {
    override fun onBindView() {
        super.onBindView()

        binding.answerA.setOnClickListener {
            findNavController().navigate(R.id.action_test3Fragment_to_test3ResultFragment)
        }
        binding.answerB.setOnClickListener {
            showCustomCancelDialog("Incorrect", "Try again")

        }
        binding.answerC.setOnClickListener {
            showCustomCancelDialog("Incorrect", "Try again")
        }
    }

}