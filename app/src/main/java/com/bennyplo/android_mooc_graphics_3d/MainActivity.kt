package com.bennyplo.android_mooc_graphics_3d

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bennyplo.android_mooc_graphics_3d.robot.DancingRobotView

class MainActivity : AppCompatActivity() {
    private var mMyView: View? = null //a custom view for drawing
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main);
        supportActionBar!!.hide() //hide the title bar
        mMyView = DancingRobotView(this)
        setContentView(mMyView)
    }
}