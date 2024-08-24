package com.jeff01478.cb.mhxxinfo.monster.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeff01478.cb.mhxxinfo.GlobalVariable
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.monster.activity.MonsterInfoActivity
import com.jeff01478.cb.mhxxinfo.monster.data.Monster

class MonsterAdapter(private val monsterList: List<Monster>) : RecyclerView.Adapter<MonsterAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val monsterView: LinearLayout = view.findViewById(R.id.itemMonsterView)
        val monsterName: TextView = view.findViewById(R.id.itemMonsterName)
        val monsterImage: ImageView = view.findViewById(R.id.itemMonsterImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_monster, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monster = monsterList[position]
        holder.monsterName.text = monster.name
        val resourceId = holder.itemView.context.resources.getIdentifier(
            monster.imageResource,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.monsterImage.setImageResource(resourceId)
        holder.monsterView.setOnClickListener {
            GlobalVariable.isDev = monsterList[position].isDev
            val intent = Intent(holder.itemView.context, MonsterInfoActivity::class.java)
            intent.putExtra("id", monsterList[position].id)
            if (monsterList[position].id >= 94)
                intent.putExtra("largeMonster", false)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = monsterList.size
}