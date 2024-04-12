package kz.sd.moneymates.fragments

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentRegisterBinding
import javax.inject.Inject


@AndroidEntryPoint
class RegisterFragment:BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override var showBottomNavigation: Boolean = false

    override fun onBindView() {
        super.onBindView()

        binding.backBtn.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.registerBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val confirmPassword = binding.passwordAgainInput.text.toString()



            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                showCustomDialog("Success!", "You've successfully registered")
                                binding.emailLayout.isErrorEnabled = false
                                findNavController().navigate(
                                    R.id.action_registerFragment_to_userDetailsFragment
                                )
                            } else {
                                binding.emailLayout.isErrorEnabled = true
                                binding.passwordAgainLayout.isErrorEnabled = true
                                binding.passwordLayout.isErrorEnabled = true
                                binding.passwordAgainLayout.error = "Something is wrong"
                                binding.passwordLayout.error = "Something is wrong"
                                binding.emailLayout.error = "Something is wrong"
                            }
                        }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Passwords are not matching",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "Enter something", Toast.LENGTH_SHORT).show()
            }
        }
        binding.haveAccountBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)
        }
    }

}