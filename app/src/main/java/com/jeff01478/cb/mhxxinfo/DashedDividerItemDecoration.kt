package com.jeff01478.cb.mhxxinfo

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DashedDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = ContextCompat.getColor(context, R.color.white)
        paint.strokeWidth = 5f // 線的寬度
        paint.style = Paint.Style.STROKE
        paint.pathEffect = DashPathEffect(floatArrayOf(15f, 20f), 0f) // 虛線效果：5dp 線段，5dp 間隔
        paint.alpha = 100 // 設定透明度值 (0-255)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = 1 // 為分隔線留出空間
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + 1

            c.drawLine(left.toFloat(), bottom.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}