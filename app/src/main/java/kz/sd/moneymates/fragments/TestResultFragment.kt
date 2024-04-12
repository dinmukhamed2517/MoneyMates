package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentTestResultsBinding
import kz.sd.moneymates.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint
class TestResultFragment:BaseFragment<FragmentTestResultsBinding>(FragmentTestResultsBinding::inflate) {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao
    override fun onBindView() {
        super.onBindView()

        binding.claimRewardBtn.setOnClickListener {
            showCustomDialog("Success!", "You gained the reward")
            userDao.changeTotalBalance(2000.0)
        }
        binding.returnBtn.setOnClickListener {
            findNavController().navigate(R.id.action_testResultFragment_to_learning)
        }
    }
    
}