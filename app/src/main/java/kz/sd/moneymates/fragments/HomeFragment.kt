package kz.sd.moneymates.fragments

import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.R
import kz.sd.moneymates.adapters.SavingAdapter
import kz.sd.moneymates.adapters.ShoppingAdapter
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentHomeBinding
import kz.sd.moneymates.firebase.Saving
import kz.sd.moneymates.firebase.UserDao
import kz.sd.moneymates.models.Shopping
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment:BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao

    var savings: MutableList<Saving> = mutableListOf()

    override fun onBindView() {
        userDao.getData()
        super.onBindView()

        val adapter = ShoppingAdapter()
        val savingAdapter = SavingAdapter()
        with(binding){


            savingList.adapter =savingAdapter
            savingList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            shoppingList.adapter = adapter
            shoppingList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter.submitList(getShoppingList())

            addSpBtn.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeToCreateSavingFragment())
            }

            userDao.getDataLiveData.observe(this@HomeFragment){ userData ->
                savings.clear()
                name.text = "Welcome, "+userData?.name

                userData?.savings?.values?.let { savingList ->
                    savings.addAll(savingList)
                }
                savingAdapter.submitList(savings.toList())
                val isSavingsEmpty = savings.isEmpty()
                noSavingsText.isVisible = isSavingsEmpty
                savingList.isVisible = !isSavingsEmpty

            }
        }

        adapter.itemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeToShoppingDetailsFragment(it)
            )
        }
    }
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser == null){
            findNavController().navigate(R.id.action_home_to_loginFragment)
        }

    }

    fun getShoppingList():List<Shopping>{
        return listOf(
            Shopping(1, "Iphone 13", 290000.0, R.drawable.product_1, "The iPhone 13 display has rounded corners that follow a beautiful curved design, and these corners are within a standard rectangle. "),
            Shopping(2, "Bicycle", 200000.0, R.drawable.product_2, "The RUSH RX905 29-inch mountain bike with disc brakes is suitable for adults 180-195 cm tall. The 21-year hardtail frame with a 5-year factory warranty is made of high-alloy HI-TEN steel using technoforming technology and internal cable routing."),
            Shopping(3, "Logitech G102", 10792.0, R.drawable.product_3, "Make the most of play time with G203—a gaming mouse in a variety of vibrant colors. With LIGHTSYNC technology, a gaming-grade sensor and a classic 6-button design, you’ll light up your game and your desk"),
            Shopping(4, "Acer Nitro 5", 399990.0, R.drawable.product_4, "Acer Nitro 5 (2020) is a Windows 10 Home laptop with a 15.00-inch display that has a resolution of 1920x1080 pixels. It is powered by a Core i5 processor and it comes with 8GB of RAM. The Acer Nitro 5 (2020) packs 1TB of HDD storage and 256GB of SSD storage. Graphics are powered by Nvidia GeForce GTX 1650."),
            Shopping(5, "MacBook Air 15 2023", 587468.0, R.drawable.product_5, "Apple MacBook Air (M1, 2020) is a macOS laptop with a 13.30-inch display that has a resolution of 2560x1600 pixels. It is powered by a Apple M1 processor and it comes with 8GB of RAM. The Apple MacBook Air (M1, 2020) packs 512GB of SSD storage."),
        )
    }
}