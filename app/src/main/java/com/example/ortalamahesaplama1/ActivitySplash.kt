package com.example.ortalamahesaplama1

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        var asagidanGelenButon = AnimationUtils.loadAnimation(this,R.anim.asagidangelenbuton)
        var yukarıdanGelenBalon = AnimationUtils.loadAnimation(this,R.anim.yukaridangelenbalon)

        var asagiyaGeriDonenButon = AnimationUtils.loadAnimation(this,R.anim.asagigidenbuton)
        var yukariyaGeriDonenBalon = AnimationUtils.loadAnimation(this,R.anim.yukariyagidenbalon)


        btnOrtalamaHesaplaAnim.animation =  asagidanGelenButon
        yukarıdanGelenBalonAnim.animation = yukarıdanGelenBalon


        btnOrtalamaHesaplaAnim.setOnClickListener {

            btnOrtalamaHesaplaAnim.startAnimation(asagiyaGeriDonenButon)
            yukarıdanGelenBalonAnim.startAnimation (yukariyaGeriDonenBalon)

            object : CountDownTimer(1000,1000){
                override fun onFinish() {
                    var intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(millisUntilFinished: Long) {
                }

            }.start()


        }


    }

}