package com.jeff01478.cb.mhxxinfo.weapon.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.weapon.activity.WeaponMapActivity
import com.jeff01478.cb.mhxxinfo.weapon.data.Weapon

class WeaponAdapter(private val weaponList: List<Weapon>) : RecyclerView.Adapter<WeaponAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weaponView: LinearLayout = view.findViewById(R.id.itemWeaponView)
        val weaponName: TextView = view.findViewById(R.id.itemWeaponName)
        val weaponImage: ImageView = view.findViewById(R.id.itemWeaponImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weapon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weapon = weaponList[position]
        holder.weaponName.text = weapon.name
        val resourceId = holder.itemView.context.resources.getIdentifier(
            weapon.imageResource,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.weaponImage.setImageResource(resourceId)
        holder.weaponView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WeaponMapActivity::class.java)
            intent.putExtra("type", weaponList[position].type)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = weaponList.size
}