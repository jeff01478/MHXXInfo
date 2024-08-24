package com.jeff01478.cb.mhxxinfo.monster.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.jeff01478.cb.mhxxinfo.GlobalVariable
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.monster.fragment.ArmorFragment
import com.jeff01478.cb.mhxxinfo.monster.fragment.MonsterFragment
import com.jeff01478.cb.mhxxinfo.weapon.fragment.WeaponsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var toolBar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var mainFrag: View
    private lateinit var search: ImageButton
    private lateinit var appbarName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        initObject()

        if (savedInstanceState == null) {
            replaceFragment(MonsterFragment())
        }

        setSupportActionBar(toolBar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).apply {
            drawerArrowDrawable.color = ContextCompat.getColor(this@MainActivity, R.color.white)
        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_monster -> {
                    appbarName.text = "魔物"
                    replaceFragment(MonsterFragment())
                }
                R.id.nav_weapons -> {
                    appbarName.text = "武器"
                    replaceFragment(WeaponsFragment())
                }
                R.id.nav_armor -> {
                    appbarName.text = "防具"
                    replaceFragment(ArmorFragment())
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        replaceFragment(MonsterFragment())
    }

    override fun onRestart() {
        super.onRestart()
        GlobalVariable.isDev = false
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrag, fragment)
            .commit()
    }

    private fun initObject() {
        toolBar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        mainFrag = findViewById(R.id.mainFrag)
        search = findViewById(R.id.search)
        appbarName = findViewById(R.id.appbarName)
    }
}