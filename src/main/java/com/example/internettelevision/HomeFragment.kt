package com.example.internettelevision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    var slideshow : Slideshow  = Slideshow()

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

        instance = this


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        slideshow.start()


        return view
    }

    //slideshow changing the items offscreen kept breaking the app, using onStop to try to fix https://stackoverflow.com/questions/18375288/fragment-lifecycle-which-method-is-called-upon-show-hide
    override fun onStop() {
        super.onStop()

        //Thread didnt really want to work right, somehow this made it function correctly - turning off the while loop in the run method
        slideshow.running = false
    }

}