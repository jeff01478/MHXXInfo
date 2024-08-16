package com.jeff01478.cb.mhxxinfo

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.jeff01478.cb.mhxxinfo.data.DevMonsterMaterial

class MakeDevMonsterMaterial(private val context: Context?,
                             private val id: Int,
                             private val monsterMaterialData: List<DevMonsterMaterial>) {

    private var index = 0
    private var isDropTiming = false
    private var dropTiming = ""

    init {
        index = monsterMaterialData.indexOfFirst { it.id == id }
    }

    fun setTableLayout(tableLayout: TableLayout, rank: String, rankKey: String): Boolean {
        if (monsterMaterialData[index].material.getValue(rankKey).isEmpty())
            return true
        val headerRow = TableRow(context)
        headerRow.addView(createHeader(rank))
        headerRow.addView(createHeader("素材", isMaterial = true))
        tableLayout.addView(headerRow)
        for ((method, material) in monsterMaterialData[index].material.getValue(rankKey)) {
            val dataRow = TableRow(context)
            dataRow.apply {
                addView(createMethodRow(method))
                addView(createTableRow(material))
            }
            tableLayout.addView(dataRow)
            if (isDropTiming) {
                val dropTimingRow = TableRow(context)
                dropTimingRow.addView(createDropTimingRow())
                isDropTiming = false
                tableLayout.addView(dropTimingRow)
            }
        }
        return false
    }

    private fun createHeader(text: String, isMaterial: Boolean = false, isLevel: Boolean = false): TextView {
        val textView = TextView(context)
        textView.apply {
            setText(text)
            setPadding(16, 24, 16, 24)
            layoutParams = TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            setBackgroundColor(Color.GRAY)
            gravity = left
            textSize = 24f
            setTextColor(Color.WHITE)
            if (isMaterial) {
                gravity = Gravity.CENTER
                layoutParams = TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 1.1f)
            }
            if (isLevel) {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT)
                setBackgroundColor(Color.TRANSPARENT)
                gravity = Gravity.CENTER
                textSize = 18f
            }
        }
        return textView
    }

    private fun createMethodRow(text: String): TextView {
        val textView = TextView(context)
        textView.apply {
            setText(text)
            setPadding(16, 24, 16, 24)
            layoutParams = TableRow.LayoutParams(0,
                TableRow.LayoutParams.MATCH_PARENT, 1f)
            textSize = 20f
            gravity = Gravity.CENTER_VERTICAL
            setTextColor(Color.GREEN)
        }
        return textView
    }

    private fun createTableRow(materialMap: Map<String, Any>): TableLayout {
        val tableLayout = TableLayout(context)
        tableLayout.apply {
            layoutParams = TableRow.LayoutParams(0,
                TableRow.LayoutParams.MATCH_PARENT, 1f)
            for ((level, material) in materialMap) {
                if (level.contains("Lv")) {
                    addView(createHeader(level, isLevel = true))
                    if (material is List<*>)
                        addView(createMaterialRow(material as List<String>))
                } else {
                    isDropTiming = true
                    dropTiming = material.toString()
                }
            }
        }
        return tableLayout
    }

    private fun createMaterialRow(materialList: List<String>): TextView {
        var material = ""
        materialList.forEach {
            material += it + "\n"
        }
        val textView = TextView(context)
        textView.apply {
            text = material
            setPadding(16, 16, 16, 16)
            layoutParams = TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 1.1f)
            gravity = Gravity.CENTER
            setTextColor(Color.YELLOW)
        }
        return textView
    }

    private fun createDropTimingRow(): TextView {
        val textView = TextView(context)
        textView.apply {
            text = dropTiming
            setPadding(16, 16, 16, 16)
            layoutParams = TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER
            setTextColor(Color.CYAN)
        }
        return textView
    }
}