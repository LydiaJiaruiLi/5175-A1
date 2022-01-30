package com.example.assignment1;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundMusic extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.canon);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();

    }

    @Override
    public void onDestroy(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }


}
