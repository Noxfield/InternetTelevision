package com.example.internettelevision

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_config.view.*

class ConfigFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_config, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //sets the listener for the button
        var handler = ConfigFragmentButtonHandler()
        view.buttonSubmit.setOnClickListener(handler)

        //add the textInputLayouts for easier access
        handler.setInputCallLetters(view.textInputCallLetters)
        handler.setInputUrl(view.textInputUrl)
    }

}

//listener for config button, it looks at the text from config's 2 text inputs and tries to add them to the lists in main activity
class ConfigFragmentButtonHandler : View.OnClickListener
{
    private lateinit var inputCallLetters : TextInputLayout
    private lateinit var inputUrl : TextInputLayout

    public fun setInputCallLetters(newInputCallLetters: TextInputLayout) {
        inputCallLetters = newInputCallLetters
    }

    public fun setInputUrl(newInputUrl: TextInputLayout) {
        inputUrl = newInputUrl
    }

    override fun onClick(p0: View?)
    {
        if (p0 != null){

            //get the new url and call letter
            var callLetter = inputCallLetters.editText!!.text.toString()
            var url : String = inputUrl.editText!!.text.toString()

            //update the list in main
            MainActivity.getInstance().addChannel(callLetter, url)
        }
    }
}