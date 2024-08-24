package com.jeff01478.cb.mhxxinfo.weapon.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.FrameLayout
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

    private var noDerivativeFragment = WeaponMapNoDerivativeFragment()
    private var derivativeFragment = WeaponMapFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapon_map)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        if (savedInstanceState == null) {
            replaceFragment(noDerivativeFragment)
        }
        initObject()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        derivativeCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val position = noDerivativeFragment.savePosition()
                derivativeFragment = WeaponMapFragment(position)
                replaceFragment(derivativeFragment)
            } else {
                val position = derivativeFragment.savePosition()
                noDerivativeFragment = WeaponMapNoDerivativeFragment(position)
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
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.weaponFrag, fragment)
            .commit()
    }
}