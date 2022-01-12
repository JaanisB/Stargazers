package com.example.stargazers.ui.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast

class MyService : Service() {

    // Declare media player variable
    private lateinit var player: MediaPlayer


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Creating media player with default ringtone
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)

        // Set looping
        player.isLooping = true

        // Start player
        player.start()

        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy()

        player.stop()
        Toast.makeText(applicationContext, "Service destroyed", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}