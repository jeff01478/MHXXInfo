package com.jeff01478.cb.mhxxinfo.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.data.MonsterMaterial
import com.jeff01478.cb.mhxxinfo.viewModel.MonsterMaterialViewModel

class MonsterMaterialFragment(activity: FragmentActivity) : Fragment() {

    private lateinit var monsterMaterialViewModel: MonsterMaterialViewModel
    private lateinit var monsterMaterialData: List<MonsterMaterial>

    private lateinit var gRankTableLayout: TableLayout
    private lateinit var highRankTableLayout: TableLayout
    private lateinit var lowRankTableLayout: TableLayout
    private lateinit var view: View

    private val id = activity.intent.getIntExtra("id", 0)
    private var index = 0
    private var dropTiming = ""
    private var isDropTiming = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("id", id.toString())
        view = inflater.inflate(R.layout.fragment_monster_material, container, false)
        initObject()
        monsterMaterialViewModel = ViewModelProvider(this).get(MonsterMaterialViewModel::class.java)
        observeViewModel()
        monsterMaterialViewModel.loadMonstersMaterial()
        return view
    }

    private fun initObject() {
        gRankTableLayout = view.findViewById(R.id.gRankTableLayout)
        highRankTableLayout = view.findViewById(R.id.highRankTableLayout)
        lowRankTableLayout = view.findViewById(R.id.lowRankTableLayout)
    }

    private fun setGRankTableLayout() {
        val headerRow = TableRow(context)
        headerRow.addView(createHeader("G級"))
        headerRow.addView(createHeader("素材", isMaterial = true))
        gRankTableLayout.addView(headerRow)
        for ((method, material) in monsterMaterialData[index].material.getValue("GRank")) {
            val dataRow = TableRow(context)
            dataRow.apply {
                addView(createMethodRow(method))
                addView(createMaterialRow(material))
            }
            gRankTableLayout.addView(dataRow)
            if (isDropTiming) {
                val dropTimingRow = TableRow(context)
                dropTimingRow.addView(createDropTimingRow(dropTiming))
                isDropTiming = false
                gRankTableLayout.addView(dropTimingRow)
            }
        }
    }

    private fun setHighTableLayout() {
        val headerRow = TableRow(context)
        headerRow.addView(createHeader("上位"))
        headerRow.addView(createHeader("素材", isMaterial = true))
        highRankTableLayout.addView(headerRow)
        for ((method, material) in monsterMaterialData[index].material.getValue("highRank")) {
            val dataRow = TableRow(context)
            dataRow.apply {
                addView(createMethodRow(method))
                addView(createMaterialRow(material))
            }
            highRankTableLayout.addView(dataRow)
            if (isDropTiming) {
                val dropTimingRow = TableRow(context)
                dropTimingRow.addView(createDropTimingRow(dropTiming))
                isDropTiming = false
                highRankTableLayout.addView(dropTimingRow)
            }
        }
    }

    private fun setLowTableLayout() {
        val headerRow = TableRow(context)
        headerRow.addView(createHeader("下位"))
        headerRow.addView(createHeader("素材", isMaterial = true))
        lowRankTableLayout.addView(headerRow)
        for ((method, material) in monsterMaterialData[index].material.getValue("lowRank")) {
            val dataRow = TableRow(context)
            dataRow.apply {
                addView(createMethodRow(method))
                addView(createMaterialRow(material))
            }
            lowRankTableLayout.addView(dataRow)
            if (isDropTiming) {
                val dropTimingRow = TableRow(context)
                dropTimingRow.addView(createDropTimingRow(dropTiming))
                isDropTiming = false
                lowRankTableLayout.addView(dropTimingRow)
            }
        }
    }

    private fun createHeader(text: String, isMaterial: Boolean = false): TextView {
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

    private fun createMaterialRow(materialList: List<String>): TextView {
        var material = "\n"
        materialList.forEach {
            if (!it.contains("%")) {
                dropTiming = it
                isDropTiming = true
                return@forEach
            }
            material += it + "\n\n"
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

    private fun createDropTimingRow(text: String): TextView {
        val textView = TextView(context)
        textView.apply {
            setText(text)
            setPadding(16, 24, 16, 24)
            layoutParams = TableRow.LayoutParams(0,
                TableRow.LayoutParams.MATCH_PARENT, 1f)
            gravity = Gravity.CENTER
            setTextColor(Color.CYAN)
        }
        return textView
    }

    private fun observeViewModel() {
        monsterMaterialViewModel.monsters.observe(viewLifecycleOwner) { monsters ->
            monsterMaterialData = monsters
            index = monsterMaterialData.indexOfFirst { it.id == id }
            setGRankTableLayout()
            if (monsterMaterialData[index].material["highRank"] != null) setHighTableLayout()
            if (monsterMaterialData[index].material["lowRank"] != null) setLowTableLayout()
        }
    }
}