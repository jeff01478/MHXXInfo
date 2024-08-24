package com.jeff01478.cb.mhxxinfo.weapon

import android.content.Context
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jeff01478.cb.mhxxinfo.R

class MakeWeaponSharpness(private val context: Context) {

    private val colorList = listOf(R.color.redSharpness, R.color.orangeSharpness,
        R.color.yellowSharpness, R.color.greenSharpness, R.color.blueSharpness,
        R.color.whiteSharpness, R.color.purpleSharpness, R.color.black)

    fun setSharpnessLayout(sharpnessLinearLayout: LinearLayout,
                           sharpnessList: List<String>) {
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        var color = 0
        sharpnessList.forEach { sharpness ->
            linearLayout.addView(setTextView(sharpness, colorList[color]))
            color++
        }
        linearLayout.setPadding(0,10,10,10)
        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            0, 1f)
        sharpnessLinearLayout.addView(linearLayout)
    }

    private fun setTextView(sharpness: String, color: Int): TextView {
        val textView = TextView(context)
        textView.apply {
            setBackgroundColor(ContextCompat.getColor(context, color))
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT,
                sharpness.length.toFloat())
        }
        return textView
    }
}