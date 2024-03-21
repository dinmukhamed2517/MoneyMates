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

    fun getShoppingList():List<Shopping>{
        return listOf(
            Shopping(1, "Iphone 13", 290000.0, R.drawable.product_1),
            Shopping(2, "Bicycle", 200000.0, R.drawable.product_2),
            Shopping(3, "Logitech G102", 10792.0, R.drawable.product_3),
            Shopping(4, "Acer Nitro 5", 399990.0, R.drawable.product_4),
            Shopping(5, "MacBook Air 15 2023", 587468.0, R.drawable.product_5),
        )
    }
}