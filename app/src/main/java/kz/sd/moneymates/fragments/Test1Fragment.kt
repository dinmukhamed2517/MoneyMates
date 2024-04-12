package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentTest1Binding

class Test1Fragment:BaseFragment<FragmentTest1Binding>(FragmentTest1Binding::inflate) {
    override fun onBindView() {
        super.onBindView()
        with(binding){
            answerA.setOnClickListener{
                showCustomCancelDialog("Incorrect", "Try again")
            }
            answerB.setOnClickListener {
                findNavController().navigate(R.id.action_test1Fragment_to_test1ResultFragment)
            }
            answerC.setOnClickListener {
                showCustomCancelDialog("Incorrect", "Try again")
            }
        }
    }

}