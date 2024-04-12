package kz.sd.moneymates.onboarding

import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentViewPagerBinding

class ViewPagerFragment :
    BaseFragment<FragmentViewPagerBinding>(FragmentViewPagerBinding::inflate) {

    override var showBottomNavigation: Boolean = false
    override fun onBindView() {
        super.onBindView()
        val fragmentList = arrayListOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

    }

}