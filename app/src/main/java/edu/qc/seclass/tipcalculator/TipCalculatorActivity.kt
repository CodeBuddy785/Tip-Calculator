package edu.qc.seclass.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import kotlin.math.roundToInt

class TipCalculatorActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip_calculator)

        // Now paste the logic block we worked on earlier below this line
        val checkAmountInput = findViewById<EditText>(R.id.CheckAmountValue)
        val partySizeInput   = findViewById<EditText>(R.id.PartySizeValue)
        val computeBtn       = findViewById<Button>(R.id.buttonCompute)

        val tip15View   = findViewById<TextView>(R.id.fifteenPercentTipValue)
        val tip20View   = findViewById<TextView>(R.id.twentyPercentTipValue)
        val tip25View   = findViewById<TextView>(R.id.twentyfivePercentTipValue)
        val total15View = findViewById<TextView>(R.id.fifteenPercentTotalValue)
        val total20View = findViewById<TextView>(R.id.twentyPercentTotalValue)
        val total25View = findViewById<TextView>(R.id.twentyfivePercentTotalValue)

        computeBtn.setOnClickListener {
            val checkStr = checkAmountInput.text.toString().trim()
            val partyStr = partySizeInput.text.toString().trim()

            val check = checkStr.toDoubleOrNull()
            val party = partyStr.toIntOrNull()

            if (check == null || party == null || check <= 0.0 || party <= 0) {
                Toast.makeText(this, "Please enter valid positive numbers.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fun compute(rate: Double): Pair<Int, Int> {
                val tipTotal = check * rate
                val perPersonTip = (tipTotal / party).roundToInt()
                val perPersonTotal = ((check + tipTotal) / party).roundToInt()
                return perPersonTip to perPersonTotal
            }

            val (tip15, total15) = compute(0.15)
            val (tip20, total20) = compute(0.20)
            val (tip25, total25) = compute(0.25)

            tip15View.text   = tip15.toString()
            tip20View.text   = tip20.toString()
            tip25View.text   = tip25.toString()

            total15View.text = total15.toString()
            total20View.text = total20.toString()
            total25View.text = total25.toString()
        }
    }
}
