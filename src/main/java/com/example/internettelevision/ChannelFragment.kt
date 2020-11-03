package com.example.internettelevision

import kotlinx.android.synthetic.main.fragment_channel.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.Space
import androidx.navigation.findNavController

class ChannelFragment : Fragment() {

    //stores a copy of the lists in this class
    private lateinit var callLetters : MutableList<String>
    private lateinit var url : MutableList<String>

    companion object
    {
        private var instance : HomeFragment? = null
        public fun getInstance() : HomeFragment
        {
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //actually sets the lists to be the same as the ones from mainactivity
        callLetters = MainActivity.getInstance().getCallLetters()
        url = MainActivity.getInstance().getUrls()

        var space: Space

        //loops over the callLetters list and adds their buttons to the view
        var itter = 0   //easier to keep track of this itter for the url, should be a minor less processing than letters.getindexof()
        for (name in callLetters) {
            var button1 = inflate(MainActivity.getInstance(), R.layout.button, null) as Button

            //sets the button name
            button1.setText(name)

            //sets up the listener and its url
            var handler = ChannelFragmentButtonHandler()
            button1.setOnClickListener(handler)
            handler.setUrl(url.get(itter))  //quicker/easier access later on

            //adds the button
            linearLayout.addView(button1)

            var space = Space(MainActivity.getInstance())
            space.minimumHeight = 15

            linearLayout.addView(space)

            itter++
        }

    }
}

//class for handling channels buttons
class ChannelFragmentButtonHandler : View.OnClickListener
{
    //holds the url
    private var url = ""

    //actually gets the url
    fun setUrl(newUrl : String){
        url = newUrl
    }

    override fun onClick(p0: View?)
    {
        if (p0 != null) {
            //create a bundle for the url
            var argBundle = Bundle()
            argBundle.putSerializable("url", url)

            //moves us to the video player with the correct url
            p0.findNavController().navigate(R.id.channel_to_video, argBundle)
        }
    }
}