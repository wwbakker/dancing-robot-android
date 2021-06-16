package nl.wwbakker.dancingrobot

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import nl.wwbakker.dancingrobot.robot.DancingRobotView

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