package com.example.ballsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
/*
This is why the code would not run this morning, we need to load the bitmap before callint
the Animateor constructor otherwise it will be null.

 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 sprites = BitmapFactory.decodeResource(getResources(),R.drawable.sprites); //move up
 setContentView(R.layout.activity_main);
 b = new Animateor(this);
 setContentView(b);

 */



public class Control {

    int direction;
    boolean move;
    Canvas c;
    int x, y;
    Bitmap sprites;
    MainActivity.Animateor a;

    public Control(Bitmap sprites) {
        this.sprites = sprites;
        this.a = a;
    }

    int xIndicator = 1;

    public void update(Canvas c){
        if (x > c.getWidth()){
            xIndicator = -1;
        }else if(x < 0){
            xIndicator = 1;
        }

        if (xIndicator == 1){
            x+=7;
            y++;
        }
        if ( xIndicator == -1){
            x-=7;
            y++;
        }
        //System.out.println("testing 123");
    }
    public void draw(Canvas c){
        // System.out.println("testing 123");
        c.drawARGB(255,255,100,200);
        // c.drawBitmap(sprites,x,y,null);
        Rect src = new Rect(0,0,400,400);
        Rect dest = new Rect (x,y,x+100,y+120);
        c.drawBitmap(sprites,src,dest,null);

    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
