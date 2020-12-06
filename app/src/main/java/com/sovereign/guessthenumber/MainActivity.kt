package com.sovereign.guessthenumber


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    //Declare variables
    var number: Int = 0
    var guess: Int = 0

    //We use lateinit to initialize our views as we need to call the function findViewById() to get them
    lateinit var message: TextView
    lateinit var guessBox: EditText
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize all views
        message = findViewById(R.id.message)
        guessBox = findViewById(R.id.guess)
        submitButton = findViewById(R.id.submit)

        //Let's keep the message invisible at start
        message.visibility = View.INVISIBLE

        randomNumber() //Calls random number function


        //Called when user hits Submit
        submitButton.setOnClickListener(View.OnClickListener {

            /*  The app shouldn't crash on an empty string.
                So we use if else statement to ensure it returns -1 in case the string is empty
                Then convert the string to an integer value. */

            var num = (if (guessBox.text.isEmpty()) "-1" else guessBox.text)
            guess = num.toString().toInt()

            //Make the message visible
            message.visibility = View.VISIBLE



            if (guess != -1){
                if (guess>number){
                    message.text = "Guess a little lower" //Set Message Text
                    message.setTextColor(Color.RED) //Set Text Color

                } else if (guess<number){
                    message.text = "Guess a little higher"  //Set Message Text
                    message.setTextColor(Color.RED)  //Set Text Color

                } else {
                    message.text = "Hurray! You guessed the number correctly."  //Set Message Text
                    message.setTextColor(Color.GREEN)  //Set Text Color
                    guessBox.text = null    //Empty the guess box
                    randomNumber()  //generate a random number
                }
            } else {
                message.text = "Please enter a number" //Set Message Text
                message.setTextColor(Color.RED)  //Set Text Color
            }

        })
    }


    fun randomNumber(){ //function to generate our random number
        number = Random().nextInt(20) //Called java method to get random number up to 20
    }
}