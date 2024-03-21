package kz.sd.moneymates.fragments

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentCreateSavingBinding
import kz.sd.moneymates.firebase.Saving
import kz.sd.moneymates.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint
class CreateSavingFragment:BaseFragment<FragmentCreateSavingBinding>(FragmentCreateSavingBinding::inflate) {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao
    override fun onBindView() {
        super.onBindView()

        showBottomNavigation = false

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.createBtn.setOnClickListener {
            var ok = true
            val planName = binding.nameInput.text.toString()
            val amount = binding.amountInput.text.toString()
            val note = binding.noteInput.text.toString()

            if (planName.isEmpty()){
                binding.nameLayout.error = "Fill up"
                binding.nameLayout.isErrorEnabled = true
                ok = false
            }
            if(amount.isEmpty()){
                binding.amountLayout.error = "Fill up"
                binding.amountLayout.isErrorEnabled = true
                ok = false
            }
            if(note.isEmpty()){
                binding.noteLayout.error = "Fill up"
                binding.noteLayout.isErrorEnabled = true
                ok = false
            }
            if(ok){
                val saving = Saving(planName, amount.toDouble(), note)
                userDao.saveSavingPlanToList(saving)
                showCustomDialog("Success", "You created saving")
                findNavController().navigate(
                    CreateSavingFragmentDirections.actionCreateSavingFragmentToHome()
                )
            }
        }
    }

}