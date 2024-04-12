package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.R
import kz.sd.moneymates.adapters.LearningAdapter
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentLearningBinding
import kz.sd.moneymates.firebase.Learning


@AndroidEntryPoint
class LearningFragment:BaseFragment<FragmentLearningBinding>(FragmentLearningBinding::inflate) {
    override fun onBindView() {
        super.onBindView()
        val adapter = LearningAdapter()
        with(binding){
            learningList.adapter = adapter
            learningList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        adapter.submitList(getList())

        adapter.itemClick = {
            findNavController().navigate(LearningFragmentDirections.actionLearningToLearningDetailsFragment(it))
        }

        binding.vaBtn.setOnClickListener {
            findNavController().navigate(R.id.action_learning_to_chatFragment)
        }
    }

    fun getList():List<Learning>{
        return listOf(
            Learning(1,"Where's money from?", 2000.0, "https://www.youtube.com/watch?v=fTTGALaRZoc", R.drawable.placeholder),

        )
    }
}