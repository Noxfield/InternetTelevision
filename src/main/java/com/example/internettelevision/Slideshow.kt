package com.example.internettelevision

//A class that is used to update the ui to make a psudo slideshow
class Slideshow : Thread {

    //holds the count position
    var itter = 2

    var running = true

    constructor()
    {

    }


    public override fun run()
    {
        while(running)
        {

            //makes the list loopable
            itter %= 3

            //create the filename
            var filename = "image" + (itter+1) // + ".png

            //create the handler to change the image
            var handler = SlideshowHandler(filename)
            MainActivity.getInstance().runOnUiThread(handler)
            try {
                Thread.sleep(3000) //3 sec Delay
            }
            catch (e : InterruptedException){
            }

            itter++
        }
    }

}