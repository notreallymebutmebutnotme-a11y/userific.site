package com.onefocus.app.stats

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.Calendar

class CalendarView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val calendar = Calendar.getInstance()
    private val days = mutableListOf<Boolean>()

    init {
        paint.color = Color.parseColor("#7A5FFF")
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..daysInMonth) {
            days.add(false)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val dayWidth = width / 7f
        val dayHeight = height / 5f
        var day = 1
        for (i in 0..4) {
            for (j in 0..6) {
                if (day > days.size) {
                    break
                }
                if (days[day - 1]) {
                    canvas.drawCircle(
                        j * dayWidth + dayWidth / 2f,
                        i * dayHeight + dayHeight / 2f,
                        dayWidth / 4f,
                        paint
                    )
                }
                day++
            }
        }
    }

    fun setStreaks(streaks: List<Boolean>) {
        days.clear()
        days.addAll(streaks)
        invalidate()
    }
}
