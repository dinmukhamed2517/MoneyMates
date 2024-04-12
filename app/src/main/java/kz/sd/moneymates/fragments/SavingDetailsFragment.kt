package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentSavingDetailsBinding
import kz.sd.moneymates.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint
class SavingDetailsFragment:BaseFragment<FragmentSavingDetailsBinding>(FragmentSavingDetailsBinding::inflate) {

    private val args:SavingDetailsFragmentArgs by navArgs()
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao
    override fun onBindView() {

        userDao.getData()
        super.onBindView()
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.title.text = args.saving?.title
        userDao.getDataLiveData.observe(this@SavingDetailsFragment){
            val savingsMap = it?.savings
            savingsMap?.forEach { item ->
                if(item.value == args.saving){
                    binding.etAmount.hint = item.value.amountOfMoney.toString()+" KZT"
                    binding.etNote.hint = item.value.note

                    binding.saveBtn.setOnClickListener {
                        showCustomDialog("Success", "Information saved")
                        userDao.changeAmountOfMoney(binding.etAmount.text.toString().toDouble(), item.key)
                        userDao.changeNotes(binding.etNote.text.toString(), item.key)
                    }
                }
            }
        }



    }

}