package com.example.dailyvideocalling

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.example.dailyvideocalling.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)//R.layout.activity_main)
        PermissionUtil.checkCallingPermissions(
            this,
            {  },
            {  }
        )
        binding.btnLoad.setOnClickListener{
           load()
        }
        binding.btnCancel.setOnClickListener{
            cancel()
        }
    }
    private fun load(){
        binding.group.visibility= View.GONE
        binding.webView.visibility= View.VISIBLE
        loadWebView(binding.etUrl.text.toString())

    }
    private fun cancel(){

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView(url: String) {
        binding.webView.visibility = View.VISIBLE
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.mediaPlaybackRequiresUserGesture = false
        binding.webView.settings.domStorageEnabled = true

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources);
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)

            }
        }
        binding.webView.loadUrl(url)
        binding.webView.setOnLongClickListener { v: View? -> true }
    }
}