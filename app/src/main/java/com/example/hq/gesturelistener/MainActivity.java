package com.example.hq.gesturelistener;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 第一种方法，就是在要实现滑动的View中，实现OnTouchListener监听事件，
 * 然后判断KeyDonw和KeyUp 直接的位置距离来判断滑动方向
 * */
public class MainActivity extends AppCompatActivity {

    private MyImageView imgv;
    private View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgv=(MyImageView)findViewById(R.id.imgv);
       // imgv.setOnTouchListener(onTouchListener);

    }
    View.OnTouchListener onTouchListener=new View.OnTouchListener() {
        //该方法是实时的监听手势，直到离开触摸屏，注意必须为返回值必须为true，
        //第二种监听方法里也必须为true
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    Log.v("按下：","x="+event.getX()+"y="+event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.v("移动：","x="+event.getX()+"y="+event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                    Log.v("提起：","x="+event.getX()+"y="+event.getY());
                    break;
            }
            return true;//必须为true，表示不停继续监听该touch的event,直到离开触摸屏
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
