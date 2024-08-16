package com.jeff01478.cb.mhxxinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.jeff01478.cb.mhxxinfo.MakeDevMonsterMaterial
import com.jeff01478.cb.mhxxinfo.R
import com.jeff01478.cb.mhxxinfo.viewModel.DevMonsterMaterialViewModel

class DevMonsterMaterialFragment(activity: FragmentActivity, private val position: Int) : Fragment() {

    private lateinit var materialTableLayout: TableLayout
    private lateinit var materialFrameLayout: FrameLayout
    private lateinit var nodataTextView: TextView

    private lateinit var devMonsterMaterialViewModel: DevMonsterMaterialViewModel
    private lateinit var view: View

    private var id = activity.intent.getIntExtra("id", 0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_dev_monster_material, container, false)
        initObject()
        devMonsterMaterialViewModel = ViewModelProvider(this).get(DevMonsterMaterialViewModel::class.java)
        observeViewModel()
        devMonsterMaterialViewModel.loadMonstersMaterial()
        return view
    }

    private fun initObject() {
        materialTableLayout = view.findViewById(R.id.materialTableLayout)
        materialFrameLayout = view.findViewById(R.id.materialFrameLayout)
        nodataTextView = view.findViewById(R.id.noDataTextView)
    }

    private fun observeViewModel() {
        devMonsterMaterialViewModel.monsters.observe(viewLifecycleOwner) { monsters ->
            val devMonsterMaterial = MakeDevMonsterMaterial(context, id, monsters)
            when (position) {
                1 -> if (devMonsterMaterial.setTableLayout(materialTableLayout, "G級", "GRank")) {
                    materialFrameLayout.visibility = View.GONE
                    nodataTextView.visibility = View.VISIBLE
                }
                2 -> if (devMonsterMaterial.setTableLayout(materialTableLayout, "上位", "highRank")) {
                    materialFrameLayout.visibility = View.GONE
                    nodataTextView.visibility = View.VISIBLE
                }
                3 -> if (devMonsterMaterial.setTableLayout(materialTableLayout, "下位", "lowRank")) {
                    materialFrameLayout.visibility = View.GONE
                    nodataTextView.visibility = View.VISIBLE
                }
            }
        }
    }
}