package com.mo2a.example.catchthemoney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var score: Int = 0
    private var imagesArray = ArrayList<ImageView>()
    private var handler = Handler()
    private var runnable = Runnable { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesArray = arrayListOf(
            imageView0,
            imageView1,
            imageView2,
            imageView3,
            imageView4,
            imageView5,
            imageView6,
            imageView7,
            imageView8,
            imageView9,
            imageView10,
            imageView11,
            imageView12,
            imageView13,
            imageView14,
            imageView15
        )


    }

    fun startGame(view: View){
        startButton.visibility= View.GONE
        restartButton.visibility= View.GONE
        score= 0
        scoreView.text= "Score: 0"
        gridLayout.visibility= View.VISIBLE
        hideImages()
        play()
        countDown()
    }

    private fun hideImages(){
        for (image in imagesArray) {
            image.visibility = View.INVISIBLE
        }
    }

    private fun play() {
        runnable = Runnable {
            hideImages()
            val random= Random()
            val index= random.nextInt(15)
            imagesArray[index].visibility= View.VISIBLE
            handler.postDelayed(runnable, 500)
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score++
        scoreView.text = "Score: $score"
    }

    fun decreaseScore(view: View) {
        score--
        scoreView.text = "Score: $score"
    }

    private fun countDown() {
        object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                timeView.text = "Time's up!"
                handler.removeCallbacks(runnable)
                gridLayout.visibility= View.GONE
                restartButton.visibility= View.VISIBLE
            }

            override fun onTick(millisUntilFinished: Long) {
                timeView.text = "Time: ${millisUntilFinished / 1000}"
            }
        }.start()
    }

}
