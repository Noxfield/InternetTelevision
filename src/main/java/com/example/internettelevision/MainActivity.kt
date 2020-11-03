package com.example.internettelevision

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    companion object
    {
        private var instance : MainActivity? = null
        public fun getInstance() : MainActivity
        {
            return instance!!
        }
    }

    lateinit var toolbar: ActionBar

    //to convert from array to list https://stackoverflow.com/questions/46662513/convert-array-to-list-in-kotlin
    private var callLetters  = arrayOf("France24","Weather","CBS","Free Speech", "Travel", "SECDN", "Daytona", "HouseTV","ROKU","Orange County", "Seminole County",
        "University of Texas","University of California").toMutableList()
    private var urls  = arrayOf("http://static.france24.com/live/F24_EN_LO_HLS/live_web.m3u8", "http://weather-lh.akamaihd.net/i/twc_1@92006/master.m3u8", "http://cbsnewshd-lh.akamaihd.net/i/CBSNHD_7@199302/master.m3u8", "https://edge.free-speech-tv-live.top.comcast.net/out/u/fstv.m3u8",
        "http://media4.tripsmarter.com:1935/LiveTV/ACVBHD/chucklist.m3u8", "http://na-all15.secdn.net/pegstream3-live/play/c3e1e4c4-7f11-4a54-8b8f-c590a95b4ade/playlist.m3u8", "http://oflash.dfw.swagit.com/live/daytonabeachfl/smil:std-4x3-1-a/chucklist.m3u8", "http://video.oct.dc.gov/out/u/DCN.m3u8",
        "http://d3ktuc8v2sjk6m.cloudfront.net/livetv/ngrp:HouseChannel_all/chucklist.m3u8", "http://173.199.158.79:1935/roku/myStream/playlist.m3u8", "http://otv3.ocfl.net:1936/OrangeTV/smil:OrangeTV.smil/chunklist_w1007974604_b894100_sleng.m3u8", // "http://video.seminolecountyfl.gov:1935/live/SGTV/chunklist.m3u8",
        "http://tstv-stream.tsm.utexas.edu/hls/livestream_hi/index.m3u8", "http://diffusionm4.assnat.qc.ca/canal9/250.sdp/playlist.m3u8").toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up the bottom navigation
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = Navigation.findNavController(this, R.id.fragment)
        bottomNavigation.setupWithNavController(navController)
        instance = this
    }

    //function to add a new pair of call letters and url to the lists
    public fun addChannel(name : String, newUrl : String){
        //checks if there is already a call letter with that name, this doesnt check for aliases of urls though
        if (!callLetters.contains(name)) {
            //because there isnt a "name" call letter yet, add it and the url in
            callLetters.add(name)
            urls.add(newUrl)
        }
    }

    //for getting the callLetters list
    public fun getCallLetters(): MutableList<String> {
        return this.callLetters
    }

    //for getting the url list
    public fun getUrls(): MutableList<String> {
        return this.urls
    }
}