package kz.sd.moneymates.fragments

import android.webkit.WebView
import androidx.navigation.fragment.navArgs
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentVideoPlayerBinding

class VideoPlayerFragment:BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {
    private val args:VideoPlayerFragmentArgs by navArgs()
    override fun onBindView() {
        super.onBindView()
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(args.videoUrl)
    }
}