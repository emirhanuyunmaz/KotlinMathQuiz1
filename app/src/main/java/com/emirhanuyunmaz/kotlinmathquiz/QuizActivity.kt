package com.emirhanuyunmaz.kotlinmathquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import com.emirhanuyunmaz.kotlinmathquiz.databinding.ActivityQuizBinding
import java.sql.Timestamp
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {

    private lateinit var binding:ActivityQuizBinding
    private lateinit var answerQuestionArray:ArrayList<MathQuestionAnswer>
    private lateinit var buttonList:ArrayList<Button>
    private lateinit var sharedPreferences: SharedPreferences

    private var s1:Int=0
    private var s2:Int=0
    private var s3:Int=0

    private var s1s2:String=""

    private var wa1:Int=0
    private var wa2:Int=0
    private var wa3:Int=0

    private var trueQuestion=0
    private var questionNumber:Int=0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences=this.getSharedPreferences("com.emirhanuyunmaz.kotlinmathquiz",
            MODE_PRIVATE)

        answerQuestionArray= ArrayList()
        questionCreate()

        buttonList= ArrayList()

        buttonList.add(binding.buttonA)
        buttonList.add(binding.buttonB)
        buttonList.add(binding.buttonC)
        buttonList.add(binding.buttonD)

        questionAnswer(questionNumber)

        object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.textViewTime.setText("Time:" + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                //Quiz finished fonk.
                binding.textViewTime.setText("done!")

                if(trueQuestion>sharedPreferences.getInt("score",0)){
                    sharedPreferences.edit().putInt("topScore",trueQuestion).commit()
                }

                var intent= Intent(this@QuizActivity,FinishActivity::class.java)
                intent.putExtra("score",trueQuestion)
                startActivity(intent)
                finish()
            }
        }.start()


    }

    private fun questionAnswer(questionNumber:Int){
        binding.textViewQuestion.text=answerQuestionArray[questionNumber].question
        //0th value answer
        var designList= arrayListOf(answerQuestionArray[questionNumber].answer,answerQuestionArray[questionNumber].wrongAnswer1,answerQuestionArray[questionNumber].wrongAnswer2,answerQuestionArray[questionNumber].wrongAnswer3)
        var counterList= arrayListOf(0,1,2,3)
        var counter=3
        var dd=Random.nextInt(0,counter)
        while (counter>=0){
            if(counter!=0)
            {
                dd=Random.nextInt(0,counter)
            }
            else{
                dd=0

            }
            if(counter==0){
                buttonList[counter].setText("A)"+designList[counterList[dd]])
                if(counterList[dd]==0){
                    control(buttonList[0])
                }
                counterList.remove(counterList[dd])

            }else if (counter==1){
                buttonList[counter].setText("B)"+designList[counterList[dd]])
                if(counterList[dd]==0){
                    control(buttonList[counter])
                }
                counterList.remove(counterList[dd])

            }else if(counter==2){
                buttonList[counter].setText("C)"+designList[counterList[dd]])
                if(counterList[dd]==0){
                    control(buttonList[counter])
                }
                counterList.remove(counterList[dd])

            }else if(counter==3){
                buttonList[counter].setText("D)"+designList[counterList[dd]])
                if(counterList[dd]==0){
                    control(buttonList[counter])
                }
                counterList.remove(counterList[dd])
            }
            counter--

        }
    }

    private fun questionCreate(){
        var random= Random(System.currentTimeMillis())

        for(a in 0..15){
            s1=random.nextInt(100)
            s2=random.nextInt(100)
            s1s2="$s1+$s2"

            var wacounter=0

            while (wacounter!=1){

                var i1=random.nextInt(100)
                var i2=random.nextInt(100)
                var i3=random.nextInt(100)

                if ((s1+s2)!=i1 && (s1+s2)!=i2 && (s1+s2)!=i3){
                    wa1=i1
                    wa2=i2
                    wa3=i3
                    wacounter=1
                }
            }
            var question=MathQuestionAnswer(s1s2,s1+s2,wa1,wa2,wa3)
            answerQuestionArray.add(question)
        }
    }

    fun control(trueButton:Button){
        binding.textViewScore.text="Score:${trueQuestion}"
        questionNumber++
        if(trueButton.equals(binding.buttonA)){

            binding.buttonA.setOnClickListener {
                trueQuestion++
                questionAnswer(questionNumber)
            }

        }else if( trueButton.equals(binding.buttonB)){
            binding.buttonB.setOnClickListener {
                trueQuestion++
                questionAnswer(questionNumber)
            }
        }else if(trueButton.equals(binding.buttonC)){
            binding.buttonC.setOnClickListener {
                trueQuestion++
                questionAnswer(questionNumber)
            }

        }else if(trueButton.equals(binding.buttonD)){
            binding.buttonD.setOnClickListener {
                trueQuestion++
                questionAnswer(questionNumber)
            }

        }
    }

    fun otherQuestion(view:View){
        binding.textViewScore.text="Score:${trueQuestion}"
        questionNumber++
        questionAnswer(questionNumber)
    }

}