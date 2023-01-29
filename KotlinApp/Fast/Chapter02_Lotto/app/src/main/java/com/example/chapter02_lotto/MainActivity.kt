package com.example.chapter02_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import java.util.IllegalFormatCodePointException

class MainActivity : AppCompatActivity() {

    private val clearButton: Button by lazy {
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton: Button by lazy {
        findViewById<Button>(R.id.addButton)
    }
    private val runButton: Button by lazy {
        findViewById<Button>(R.id.runButton)
    }
    private val numberPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private val numberTextviewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById<TextView>(R.id.textView1),
            findViewById<TextView>(R.id.textView2),
            findViewById<TextView>(R.id.textView3),
            findViewById<TextView>(R.id.textView4),
            findViewById<TextView>(R.id.textView5),
            findViewById<TextView>(R.id.textView6),


            )
    }

    private var didRun = false

    private val pickerNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }

    private fun initRunButton(){
        runButton.setOnClickListener{
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed{ index, number ->
                val textView = numberTextviewList[index]

                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }


            Log.d("MainActivity", list.toString())
        }
    }

    private fun initAddButton(){
        addButton.setOnClickListener{

            if (didRun) {
                Toast.makeText(this,"초기화 후에 시도해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickerNumberSet.size >= 5){
                Toast.makeText(this, "번호는 5개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pickerNumberSet.contains(numberPicker.value)){

                Toast.makeText(this, "이미 선택된 번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textview = numberTextviewList[pickerNumberSet.size]
            textview.isVisible = true
            textview.text = numberPicker.value.toString()

            when(numberPicker.value){
                in 1..10 -> textview.background = ContextCompat.getDrawable(this, R.drawable.circle_yello)
                in 11..20 -> textview.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
                in 21..30 -> textview.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
                in 31..40 -> textview.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
                else -> textview.background = ContextCompat.getDrawable(this, R.drawable.circle_green)


            }


            pickerNumberSet.add(numberPicker.value)
        }
    }

    private fun setNumberBackground(number: Int, textView: TextView){
        when(number){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_yello)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }

    private fun initClearButton(){
        clearButton.setOnClickListener{
            pickerNumberSet.clear()
            numberTextviewList.forEach{
                it.isVisible = false
            }
        }
    }

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for(i in 1..45){

                if (pickerNumberSet.contains(i)){
                    continue
                }
                this.add(i)
            }
        }
        numberList.shuffle()
        val newList = pickerNumberSet.toList() +  numberList.subList(0, 6 - pickerNumberSet.size)

        return  newList.sorted()
    }
}