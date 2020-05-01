package com.example.ballsprite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    Animateor b;
    Bitmap sprites;
    int x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = new Animateor(this);
        sprites = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        x = 0; y = 0;
        setContentView(b);

    }
    @Override
    protected void onPause(){
        super.onPause();
        b.pause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        b.resume();
    }


    public class Animateor extends SurfaceView implements Runnable {

        Thread t = null;
        SurfaceHolder sh;
        boolean isSafe = false;
        int indicator = 1;

        public Animateor(Context context) {
            super(context);
            sh = getHolder();
        }

        @Override
        public void run() {
            while(isSafe){
                if(!sh.getSurface().isValid()){
                    continue;
                }
                Canvas c = sh.lockCanvas();
                c.drawARGB(255,255,100,200);
                // c.drawBitmap(sprites,x,y,null);
                Rect src = new Rect(0,0,200,200);
                Rect dest = new Rect(0,0,0,0);
                if (indicator == 1)
                    dest = new Rect(x, y, x + 100, y + 120);
                if (indicator == -1)
                    dest = new Rect(x - 100, y - 100, x, y);
                c.drawBitmap(sprites,src,dest,null);
                if (indicator == 1)
                    x+=10;
                if (indicator == -1)
                    x-=10;
                if ( x > c.getWidth())
                    x = c.getWidth(); indicator = 1;
                if ( x < 0)
                    x = 0; indicator = -1;

                sh.unlockCanvasAndPost(c);
            }
        }
        public void pause() {
            isSafe = false;
            while (true){
                try{
                    t.join();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            t = null;

        }
        public void resume() {
            isSafe = true;
            t = new Thread(this);
            t.start();
        }
    }
}