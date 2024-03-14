package kz.sd.moneymates.fragments

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentLoginBinding
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment:BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override var showBottomNavigation: Boolean = false
    override fun onBindView() {
        super.onBindView()

        binding.loginBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showCustomDialog("Success!", "You've successfully logged in")
                        findNavController().navigate(
                            R.id.action_loginFragment_to_home
                        )
                    } else {
                        binding.emailLayout.isErrorEnabled = true
                        binding.passwordLayout.isErrorEnabled = true
                        binding.passwordLayout.error = "Something is wrong"
                        binding.emailLayout.error = "Something is wrong"
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Enter something", Toast.LENGTH_SHORT).show()
            }
        }
        binding.noAccount.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }

    }

}