import android.content.Context
import androidx.navigation.fragment.findNavController
import kz.sd.moneymates.R
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override var showBottomNavigation: Boolean = false

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onBindView() {
        super.onBindView()
        coroutineScope.launch {
            delay(3000)
            if (onBoardingFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_home)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel() 
    }
}
