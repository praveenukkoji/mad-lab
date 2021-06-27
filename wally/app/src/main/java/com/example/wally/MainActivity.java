package com.example.wally;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int images[] = new int[]{
            R.drawable.wally1,
            R.drawable.wally2,
            R.drawable.wally3,
            R.drawable.wally4,
            R.drawable.wally5,
            R.drawable.wally6,
            R.drawable.wally7,
            R.drawable.wally8,
            R.drawable.wally9,
            R.drawable.wally10
    };

    Button button;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Timer().schedule(new ChangeWallpaper(),0, 3000);
            }
        });
    }

    class ChangeWallpaper extends TimerTask {
        @Override
        public void run() {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());
            try {
                wallpaperManager.setBitmap( BitmapFactory.decodeResource(getResources(),images[i]));
                i++;
                if(i==10)
                    i=0;
            } catch ( IOException e) {
                e.printStackTrace();
            }
        }
    }
}