package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentLearningDetailsBinding

class LearningDetailsFragment:BaseFragment<FragmentLearningDetailsBinding>(FragmentLearningDetailsBinding::inflate) {

    private val args:LearningDetailsFragmentArgs by navArgs()
    override fun onBindView() {
        super.onBindView()

        with(binding){
            watchVideoBtn.setOnClickListener {
                findNavController().navigate(LearningDetailsFragmentDirections.actionLearningDetailsFragmentToVideoPlayerFragment(args.learning.url))
            }
        }
    }

}