package com.backendpapa.fightertime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
//    game variables
    private var score=0
    private lateinit var countDownTimer: CountDownTimer
    private var gameStarted=false

    private var initialCountdown:Long=60000
    private var countDownInterval:Long=1000
    private var timeLeft=60


    private lateinit var gameScoreTextView: TextView
    private lateinit var timeLeftTextView: TextView
    private lateinit var tapMeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Connect views to variables
        gameScoreTextView=findViewById(R.id.game_score_text_view)
        timeLeftTextView=findViewById(R.id.time_left_text_view)
        tapMeButton=findViewById(R.id.tap_me_button)

//        set the tap Me button to listen to clicks
        tapMeButton.setOnClickListener{

            if(!gameStarted){
                startGame()
            }
            incrementScore()
        }
        resetGame()

    }
    private fun incrementScore(){
//        Logic to increment score
        score++
        val newScore=getString(R.string.your_score,score)
        gameScoreTextView.text=newScore
    }
    private fun resetGame(){
//        Reset game logic
        score=0
        val initialScore=getString(R.string.your_score,score)
        gameScoreTextView.text=initialScore

        val initialTimeLeft=getString(R.string.time_left,timeLeft)
        timeLeftTextView.text=initialTimeLeft

//        countdown initialized
        countDownTimer=object :CountDownTimer(initialCountdown,countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                timeLeft=millisUntilFinished.toInt()/1000
                val timeleftString=getString(R.string.time_left,timeLeft)
                timeLeftTextView.text=timeleftString
            }

            override fun onFinish() {
                endGame()
            }

        }
        gameStarted=false
    }
    private fun startGame(){
//        Start game logic
        countDownTimer.start()
        gameStarted=true
    }
    private fun endGame(){
//        End game logic
        Toast.makeText(this,getString(R.string.game_over_message,score),Toast.LENGTH_LONG).show()
        resetGame()
    }
}