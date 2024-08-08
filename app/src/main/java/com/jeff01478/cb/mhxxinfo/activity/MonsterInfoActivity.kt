package com.jeff01478.cb.mhxxinfo.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.data.Monster
import com.jeff01478.cb.mhxxinfo.viewModel.MonsterInfoViewModel

class MonsterInfoActivity : AppCompatActivity() {

    private lateinit var monsterInfoViewModel: MonsterInfoViewModel
    private lateinit var monsterData: List<Monster>

    private lateinit var weaknessTableLayout: TableLayout
    private lateinit var monsterImageView: ImageView
    private lateinit var monsterNameTextView: TextView

    private var position = 0
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monster_info)

        initObject()
        position = intent.getIntExtra("position", 0)

        monsterInfoViewModel = ViewModelProvider(this).get(MonsterInfoViewModel::class.java)
        observeViewModel()
        monsterInfoViewModel.loadMonsters()
    }

    private fun initObject() {
        monsterImageView = findViewById(R.id.monsterImageView)
        monsterNameTextView = findViewById(R.id.monsterNameTextView)
        weaknessTableLayout = findViewById(R.id.weaknessTableLayout)
    }

    private fun setTableLayout() {
        val headerRow = TableRow(this)
        headerRow.addView(createTextView("部位", true))
        headerRow.addView(createImageView(R.drawable.fire))
        headerRow.addView(createImageView(R.drawable.water))
        headerRow.addView(createImageView(R.drawable.thunder))
        headerRow.addView(createImageView(R.drawable.ice))
        headerRow.addView(createImageView(R.drawable.dragon))
//        headerRow.addView(createTextView("火", true))
//        headerRow.addView(createTextView("水", true))
//        headerRow.addView(createTextView("雷", true))
//        headerRow.addView(createTextView("冰", true))
//        headerRow.addView(createTextView("龍", true))
        weaknessTableLayout.addView(headerRow)
        setDataRow()
    }

    private fun setDataRow() {
        for ((bodyPart, weaknessMap) in monsterData[index].weakness) {
            val dataRow = TableRow(this)
            dataRow.addView(createTextView(bodyPart, isPart = true))
            dataRow.addView(createTextView(weaknessMap["fire"] ?: ""))
            dataRow.addView(createTextView(weaknessMap["water"] ?: ""))
            dataRow.addView(createTextView(weaknessMap["thunder"] ?: ""))
            dataRow.addView(createTextView(weaknessMap["ice"] ?: ""))
            dataRow.addView(createTextView(weaknessMap["dragon"] ?: ""))
            weaknessTableLayout.addView(dataRow)
        }
    }

    private fun createTextView(text: String, isHeader: Boolean = false, isPart: Boolean = false): TextView {
        val textView = TextView(this)
        textView.apply {
            setText(text)
            setPadding(16, 24, 16, 24)
            gravity = Gravity.CENTER
            if (isHeader) {
                setBackgroundColor(Color.GRAY)
            }
            if (isPart) {
                gravity = left
            }
            setTextColor(Color.WHITE)
        }
        return textView
    }

    private fun createImageView(resourceId: Int): ImageView {
        val imageView = ImageView(this)
        imageView.apply {
            setImageResource(resourceId)
            //setPadding(16, 24, 16, 24)
        }
        return imageView
    }

    private fun setMonsterLayout() {
        val monsterName = monsterData[index].name
        val monsterImageResource = monsterData[index].imageResource
        val resourceId = resources.getIdentifier(
            monsterImageResource,
            "drawable",
            packageName
        )
        monsterNameTextView.text = monsterName
        monsterImageView.setImageResource(resourceId)
    }

    private fun observeViewModel() {
        monsterInfoViewModel.monsters.observe(this) { monsters ->
            monsterData = monsters
            index = monsterData.indexOfFirst { it.id == position }
            setMonsterLayout()
            setTableLayout()
        }
    }
}