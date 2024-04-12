package kz.sd.moneymates.fragments

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kz.sd.moneymates.base.BaseFragment
import kz.sd.moneymates.databinding.FragmentVideoPlayerBinding

class VideoPlayerFragment:BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {
    private val args:VideoPlayerFragmentArgs by navArgs()
    private var isUrlLoaded = false

    override fun onBindView() {
        super.onBindView()

        if (!isUrlLoaded) {
            binding.webview.settings.javaScriptEnabled = true
            binding.webview.loadUrl(args.videoUrl)
            isUrlLoaded = true
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webview.canGoBack()) {
                    binding.webview.goBack()
                } else {
                    if (isAdded && fragmentManager != null) {
                        findNavController().navigateUp()
                    }
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webview.saveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            binding.webview.restoreState(it)
        }
    }
}