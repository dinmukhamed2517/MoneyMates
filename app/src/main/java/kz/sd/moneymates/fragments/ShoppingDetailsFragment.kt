package kz.sd.moneymates.fragments

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentShoppingDetailsBinding
import kz.sd.moneymates.firebase.UserDao
import javax.inject.Inject


@AndroidEntryPoint
class ShoppingDetailsFragment:BaseFragment<FragmentShoppingDetailsBinding>(FragmentShoppingDetailsBinding::inflate) {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao
    private val args:ShoppingDetailsFragmentArgs by navArgs()

    private var availableMoney:Double = 0.0
    override fun onBindView() {
        super.onBindView()
        userDao.getData()
        val shopping = args.shopping

        with(binding){
            title.text = shopping.title
            price.text = shopping.price.toString() + " KZT"
            img.setImageResource(shopping.img)
            description.text = shopping.description

            backBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            buyBtn.setOnClickListener {
                if(availableMoney >= shopping.price){
                    showCustomDialog("Success!", "You successfully purchased this item")
                }
                else{
                    showCustomCancelDialog("Oops", "You don't have enough money")
                }
            }
        }
        userDao.getDataLiveData.observe(this){
            availableMoney = it?.totalBalance!!
        }

    }

}