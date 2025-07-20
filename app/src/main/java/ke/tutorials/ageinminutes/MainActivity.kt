package ke.tutorials.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import ke.tutorials.ageinminutes.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val yr = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(
                this,
                "DatePicker Works: $selectedDayOfMonth/${selectedMonth + 1}/$selectedYear",
                Toast.LENGTH_LONG
            ).show()

            val selectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            val selectedDateView = findViewById<TextView>(R.id.tvSelectedDate)
            selectedDateView.text = selectedDateText

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDateText)

            if (theDate != null) {
                // Convert selected date to minutes since epoch
                val selectedDateInMinutes = theDate.time / 60000

                // Get current time in minutes
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                if (currentDate != null) {
                    val currentDateInMinutes = currentDate.time / 60000

                    // Calculate the difference
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    // Display it in the TextView with id "minutes"
                    val minutesView = findViewById<TextView>(R.id.minutes)
                    minutesView.text = differenceInMinutes.toString()
                }
            }





        }, yr, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show() // Important!
    }
}
