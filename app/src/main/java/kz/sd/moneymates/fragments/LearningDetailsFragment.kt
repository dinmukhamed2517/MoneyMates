package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentLearningDetailsBinding

class LearningDetailsFragment:BaseFragment<FragmentLearningDetailsBinding>(FragmentLearningDetailsBinding::inflate) {

    private val args:LearningDetailsFragmentArgs by navArgs()
    override fun onBindView() {
        super.onBindView()

        with(binding){
            img.setImageResource(args.learning.img)
            title.text = args.learning.title
            watchVideoBtn.setOnClickListener {
                findNavController().navigate(LearningDetailsFragmentDirections.actionLearningDetailsFragmentToVideoPlayerFragment(args.learning.url))
            }
            backBtn.setOnClickListener{
                findNavController().popBackStack()
            }
            startBtn.setOnClickListener {
                findNavController().navigate(R.id.action_learningDetailsFragment_to_test1Fragment)
            }
        }
    }

}