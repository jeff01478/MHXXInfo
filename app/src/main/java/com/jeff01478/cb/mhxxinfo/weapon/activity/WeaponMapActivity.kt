package com.jeff01478.cb.mhxxinfo.weapon.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.weapon.fragment.WeaponMapFragment
import com.jeff01478.cb.mhxxinfo.weapon.fragment.WeaponMapNoDerivativeFragment

class WeaponMapActivity : AppCompatActivity() {

    private lateinit var weaponFrag: FrameLayout
    private lateinit var toolbar: Toolbar
    private lateinit var derivativeCheckBox: CheckBox
    private lateinit var weaponName: TextView

    private lateinit var noDerivativeFragment: WeaponMapNoDerivativeFragment
    private lateinit var derivativeFragment: WeaponMapFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapon_map)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        val weaponType = intent.getStringExtra("type")
        if (savedInstanceState == null) {
            noDerivativeFragment = WeaponMapNoDerivativeFragment(weaponType)
            replaceFragment(noDerivativeFragment)
        }
        initObject()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        weaponName.text = weaponType

        derivativeCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val position = noDerivativeFragment.savePosition()
                derivativeFragment = WeaponMapFragment(weaponType, savePosition = position)
                replaceFragment(derivativeFragment)
            } else {
                val position = derivativeFragment.savePosition()
                noDerivativeFragment = WeaponMapNoDerivativeFragment(weaponType, savePosition = position)
                replaceFragment(noDerivativeFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // 返回按钮被点击，执行返回操作
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initObject() {
        weaponFrag = findViewById(R.id.weaponFrag)
        toolbar = findViewById(R.id.toolbar)
        derivativeCheckBox = findViewById(R.id.derivativeCheckBox)
        weaponName = findViewById(R.id.appbarName)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.weaponFrag, fragment)
            .commit()
    }
}