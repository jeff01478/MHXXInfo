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
    private lateinit var elementTableLayout: TableLayout
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
        elementTableLayout = findViewById(R.id.elementTableLayout)
    }

    private fun setWeaknessTableLayout() {
        val headerRow = TableRow(this)
        headerRow.addView(createTextView("弱點", true))
        headerRow.addView(createImageView(R.drawable.great_sword))
        headerRow.addView(createImageView(R.drawable.hammer))
        headerRow.addView(createImageView(R.drawable.light_bowgun))
        weaknessTableLayout.addView(headerRow)
        setWeaknesssDataRow()
    }

    private fun setWeaknesssDataRow() {
        for ((bodyPart, weaknessMap) in monsterData[index].weakness) {
            val dataRow = TableRow(this)
            dataRow.addView(createTextView(bodyPart, isPart = true))
            dataRow.addView(createTextView(weaknessMap["cutting"] ?: ""))
            dataRow.addView(createTextView(weaknessMap["impack"] ?: ""))
            dataRow.addView(createTextView(weaknessMap["shot"] ?: ""))
            weaknessTableLayout.addView(dataRow)
        }
    }

    private fun setElementTableLayout() {
        val headerRow = TableRow(this)
        headerRow.addView(createTextView("屬性弱點", true))
        headerRow.addView(createImageView(R.drawable.fire))
        headerRow.addView(createImageView(R.drawable.water))
        headerRow.addView(createImageView(R.drawable.thunder))
        headerRow.addView(createImageView(R.drawable.ice))
        headerRow.addView(createImageView(R.drawable.dragon))
        elementTableLayout.addView(headerRow)
        setElementDataRow()
    }

    private fun setElementDataRow() {
        for ((bodyPart, weaknessMap) in monsterData[index].weakness) {
            val dataRow = TableRow(this)
            dataRow.addView(createTextView(bodyPart, isPart = true))
            dataRow.addView(createTextView(weaknessMap["fire"] ?: "", textColor = Color.RED))
            dataRow.addView(createTextView(weaknessMap["water"] ?: "", textColor = Color.BLUE))
            dataRow.addView(createTextView(weaknessMap["thunder"] ?: "", textColor = Color.YELLOW))
            dataRow.addView(createTextView(weaknessMap["ice"] ?: "", textColor = Color.CYAN))
            dataRow.addView(createTextView(weaknessMap["dragon"] ?: "", textColor = Color.MAGENTA))
            elementTableLayout.addView(dataRow)
        }
    }

    private fun createTextView(text: String, isHeader: Boolean = false, isPart: Boolean = false,
                                textColor: Int = Color.WHITE): TextView {
        val textView = TextView(this)
        textView.apply {
            setText(text)
            setPadding(16, 24, 16, 24)
            gravity = Gravity.CENTER
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            if (isHeader) {
                setBackgroundColor(Color.GRAY)
                textSize = 24f
                gravity = left
            }
            if (isPart) {
                gravity = left
                gravity = Gravity.CENTER_VERTICAL
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 4f)
            }
            if (isHeader && isPart) {
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 4f)
            }
            setTextColor(Color.WHITE)
            setTextColor(textColor)
        }
        return textView
    }

    private fun createImageView(resourceId: Int): ImageView {
        val imageView = ImageView(this)
        imageView.apply {
            setImageResource(resourceId)
            setBackgroundColor(Color.GRAY)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.25f)
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
            setElementTableLayout()
            setWeaknessTableLayout()
        }
    }
}