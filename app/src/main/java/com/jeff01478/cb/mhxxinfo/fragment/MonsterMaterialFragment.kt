package com.jeff01478.cb.mhxxinfo.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.data.MonsterMaterial
import com.jeff01478.cb.mhxxinfo.viewModel.MonsterMaterialViewModel

class MonsterMaterialFragment(activity: FragmentActivity, private val position: Int) : Fragment() {

    private lateinit var monsterMaterialViewModel: MonsterMaterialViewModel
    private lateinit var monsterMaterialData: List<MonsterMaterial>

    private lateinit var materialTableLayout: TableLayout
    private lateinit var materialFrameLayout: FrameLayout
    private lateinit var nodataTextView: TextView
    private lateinit var view: View

    private val id = activity.intent.getIntExtra("id", 0)
    private val largeMonster = activity.intent.getBooleanExtra("largeMonster", true)
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
        if (largeMonster)
            monsterMaterialViewModel.loadMonstersMaterial()
        else
            monsterMaterialViewModel.loadSmailMonstersMaterial()
        return view
    }

    private fun initObject() {
        materialTableLayout = view.findViewById(R.id.materialTableLayout)
        materialFrameLayout = view.findViewById(R.id.materialFrameLayout)
        nodataTextView = view.findViewById(R.id.noDataTextView)
    }

    private fun setGRankTableLayout(): Boolean {
        if (monsterMaterialData[index].material.getValue("GRank").isEmpty())
            return true
        val headerRow = TableRow(context)
        headerRow.addView(createHeader("G級"))
        headerRow.addView(createHeader("素材", isMaterial = true))
        materialTableLayout.addView(headerRow)
        for ((method, material) in monsterMaterialData[index].material.getValue("GRank")) {
            val dataRow = TableRow(context)
            dataRow.apply {
                addView(createMethodRow(method))
                addView(createMaterialRow(material))
            }
            materialTableLayout.addView(dataRow)
            if (isDropTiming) {
                val dropTimingRow = TableRow(context)
                dropTimingRow.addView(createDropTimingRow(dropTiming))
                isDropTiming = false
                materialTableLayout.addView(dropTimingRow)
            }
        }
        return false
    }

    private fun setHighTableLayout(): Boolean {
        if (monsterMaterialData[index].material.getValue("highRank").isEmpty())
            return true
        val headerRow = TableRow(context)
        headerRow.addView(createHeader("上位"))
        headerRow.addView(createHeader("素材", isMaterial = true))
        materialTableLayout.addView(headerRow)
        for ((method, material) in monsterMaterialData[index].material.getValue("highRank")) {
            val dataRow = TableRow(context)
            dataRow.apply {
                addView(createMethodRow(method))
                addView(createMaterialRow(material))
            }
            materialTableLayout.addView(dataRow)
            if (isDropTiming) {
                val dropTimingRow = TableRow(context)
                dropTimingRow.addView(createDropTimingRow(dropTiming))
                isDropTiming = false
                materialTableLayout.addView(dropTimingRow)
            }
        }
        return false
    }

    private fun setLowTableLayout(): Boolean {
        if (monsterMaterialData[index].material.getValue("lowRank").isEmpty())
            return true
        val headerRow = TableRow(context)
        headerRow.addView(createHeader("下位"))
        headerRow.addView(createHeader("素材", isMaterial = true))
        materialTableLayout.addView(headerRow)
        for ((method, material) in monsterMaterialData[index].material.getValue("lowRank")) {
            val dataRow = TableRow(context)
            dataRow.apply {
                addView(createMethodRow(method))
                addView(createMaterialRow(material))
            }
            materialTableLayout.addView(dataRow)
            if (isDropTiming) {
                val dropTimingRow = TableRow(context)
                dropTimingRow.addView(createDropTimingRow(dropTiming))
                isDropTiming = false
                materialTableLayout.addView(dropTimingRow)
            }
        }
        return false
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
            when (position) {
                1 -> if (setGRankTableLayout()) {
                    materialFrameLayout.visibility = View.GONE
                    nodataTextView.visibility = View.VISIBLE
                }
                2 -> if (setHighTableLayout()) {
                    materialFrameLayout.visibility = View.GONE
                    nodataTextView.visibility = View.VISIBLE
                }
                3 -> if (setLowTableLayout()) {
                    materialFrameLayout.visibility = View.GONE
                    nodataTextView.visibility = View.VISIBLE
                }
            }
        }
    }
}