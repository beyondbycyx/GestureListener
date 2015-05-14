package com.example.hq.gesturelistener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by hq on 2015/5/14.
 * 第二种方法：实现OnTouchListener和OnGestureListener，其实是通过TouchListener交给OnGestureListener里的方法处理
 * 就是构建手势探测器，如GestureDetector mygesture = new GestureDetector(this);，
 * 然后在onFling方法中根据MotionEvent的两个参数的 按下和滑动以及放开的位置和距离来判断滑动方向以及滑动速度等的。
 * 要构建GestureDetector，必须要和OnTouchListener一起使用,因为必须设置Touch监听
 */
//直接在该类的内部实现touchlistener和gesturelistener，无需外界传进来
public class MyImageView  extends ImageView  implements View.OnTouchListener ,GestureDetector.OnGestureListener{

    private GestureDetector gestureDetector;
    public MyImageView(Context context,AttributeSet attrs)
    {
        super(context,attrs);
        gestureDetector=new GestureDetector(context,this);
        //在此传入监听器
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //调用了封装手势监听器，即将event事件交给手势监听器处理
         gestureDetector.onTouchEvent(event);
        return true;//切记这里必须返回true,否则上一句无效
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    //它只监听手势“滑动”的动作，注意必须有移动，而且当滑动的动作完成了即离开触摸屏，该方法才执行
    //因为只有这用它才能获取到两个event的参数，只针对“一次滑动”执行一次方法
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //e1为按下时的event,e2为移动后的event,后面为速度
       if(e1.getX()-e2.getX()>100&& Math.abs(velocityX)>0)
            Toast.makeText(getContext(),"左滑了",Toast.LENGTH_SHORT).show();

        return false;
    }
   //该动作也是监听“滑动”，必须有移动了而且它的监听是实时的，不停地给出参数,+",speedX="+velocityX+",speedY="+velocityY
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.v("e1-----","x="+e1.getX()+",y="+e1.getY());
        Log.v("e2-----","x="+e2.getX()+",y="+e2.getY());

        Log.v("onscroll-----","X距离="+distanceX+",Y距离="+distanceY);
        if(distanceX>10)
            Toast.makeText(getContext(),"X轴方向移动了大于10",Toast.LENGTH_SHORT).show();

        return false;
    }
}
