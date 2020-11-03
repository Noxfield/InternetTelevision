package com.example.internettelevision

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import kotlinx.android.synthetic.main.fragment_video_player.view.*

//this class is related to playing the video (though it can work with any website)
class VideoPlayerFragment : Fragment() {

    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var webDelegate = Delegate()
        view.webViewObject.webViewClient = webDelegate

        //This will allow the tracing of links
        view.webViewObject.settings.javaScriptEnabled = true;
        view.webViewObject.settings.javaScriptCanOpenWindowsAutomatically = true;

        //Get the bundle
        var arguments = this.getArguments()
        url = arguments?.getSerializable("url") as String

        //plug in band and name
        view.webViewObject.loadUrl(url)

    }
}

class Delegate : WebViewClient()
{
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)
    {
        super.onPageStarted(view, url, favicon)
        println("started")
    }
    override fun onPageFinished(view: WebView, url: String)
    {
        super.onPageFinished(view,url)
        println("finish")
    }
    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError)
    {
        println("Issue")

    }
    override fun onReceivedHttpError(
        view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse
    )
    {
        println(errorResponse.data)
    }
    override fun onReceivedSslError(
        view: WebView, handler: SslErrorHandler,
        error: SslError
    )
    {
        println(error.primaryError)
    }
}