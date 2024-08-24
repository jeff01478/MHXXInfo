package com.jeff01478.cb.mhxxinfo.weapon.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.weapon.MakeWeaponSharpness
import com.jeff01478.cb.mhxxinfo.weapon.data.WeaponMap

class WeaponMapNoDerivativeAdapter(private val weaponMapList: List<WeaponMap>) :
    RecyclerView.Adapter<WeaponMapNoDerivativeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val attack: TextView = view.findViewById(R.id.attack)
        val affinity: TextView = view.findViewById(R.id.affinity)
        val elemental: TextView = view.findViewById(R.id.elemental)
        val defense: TextView = view.findViewById(R.id.defense)
        val special: TextView = view.findViewById(R.id.special)
        val holo: TextView = view.findViewById(R.id.holo)
        val sharpnessLinearLayout: LinearLayout = view.findViewById(R.id.sharpnessLinearLayout)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeaponMapNoDerivativeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weapon_map_no_derivative, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaponMapNoDerivativeAdapter.ViewHolder, position: Int) {
        val weaponMap = weaponMapList[position]
        holder.name.text = weaponMap.name
        holder.attack.text = "攻擊${weaponMap.attack}"
        holder.affinity.text = weaponMap.affinity
        holder.elemental.text = weaponMap.elemental
        val elementalColor = ContextCompat.getColor(holder.itemView.context, setElementalColor(weaponMap.elemental))
        holder.elemental.setTextColor(elementalColor)
        holder.defense.text = weaponMap.defense
        holder.special.text = weaponMap.special
        holder.holo.text = weaponMap.holo

        val makeWeaponSharpness = MakeWeaponSharpness(holder.itemView.context)
        val linearLayout = LinearLayout(holder.itemView.context)
        linearLayout.orientation = LinearLayout.VERTICAL
        weaponMap.sharpness.values.forEach {
            makeWeaponSharpness.setSharpnessLayout(linearLayout, it)
            Log.d("GOOD", it.toString())
        }

        holder.sharpnessLinearLayout.removeAllViews()
        holder.sharpnessLinearLayout.addView(linearLayout)
    }

    override fun getItemCount(): Int = weaponMapList.size

    private fun setElementalColor(elemental: String): Int =
        if ("火" in elemental) R.color.fire
        else if ("水" in elemental) R.color.water
        else if ("雷" in elemental) R.color.thunder
        else if ("氷" in elemental) R.color.ice
        else if ("龍" in elemental) R.color.dragon
        else if ("麻痺" in elemental) R.color.paralysis
        else if ("睡眠" in elemental) R.color.sleep
        else if ("爆" in elemental) R.color.bomb
        else if ("毒" in elemental) R.color.poison
        else R.color.white
}