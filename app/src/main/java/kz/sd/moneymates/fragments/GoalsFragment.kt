package kz.sd.moneymates.fragments

import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.adapters.SavingAdapter
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentGoalsBinding
import kz.sd.moneymates.firebase.Saving
import kz.sd.moneymates.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint
class GoalsFragment:BaseFragment<FragmentGoalsBinding>(FragmentGoalsBinding::inflate) {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao

    var savings: MutableList<Saving> = mutableListOf()


    override fun onBindView() {
        userDao.getData()
        super.onBindView()


        with(binding){
            val adapter = SavingAdapter()


            adapter.itemClick = {
                findNavController().navigate(GoalsFragmentDirections.actionGoalToSavingDetailsFragment(it))
            }
            savingList.adapter= adapter
            savingList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            userDao.getDataLiveData.observe(this@GoalsFragment){
                val map = it?.savings
                map?.forEach{ item ->
                    savings.add(item.value)
                }
                adapter.submitList(savings)
                Log.e("List:", savings.toString())
            }

            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}