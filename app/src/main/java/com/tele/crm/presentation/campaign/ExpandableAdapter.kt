package com.tele.crm.presentation.campaign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tele.crm.R
import com.tele.crm.data.network.model.campaign.ParentItem
import ir.mahozad.android.PieChart

class ExpandableAdapter(private val data: List<ParentItem>) :
    RecyclerView.Adapter<ExpandableAdapter.ParentViewHolder>() {

    private val expandedPositions = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_list_title, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val item = data[position]
        holder.headerTitle.text = item.title
        holder.childLayout.visibility = if (expandedPositions.contains(position)) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            if (expandedPositions.contains(position)) expandedPositions.remove(position)
            else expandedPositions.add(position)
            notifyItemChanged(position)
        }

        if (expandedPositions.contains(position)) {
            holder.bindPieChart(item.chartValues, item.labels)
        }
    }

    override fun getItemCount(): Int = data.size

    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTitle: TextView = itemView.findViewById(R.id.listTitle)
        val childLayout: View = itemView.findViewById(R.id.childLayout)

        fun bindPieChart(values: List<Float>, labels: List<String>) {
            val pieChart = itemView.findViewById<PieChart>(R.id.pieChart)
         /*   pieChart.values = values
            pieChart.labels = labels*/
            pieChart.invalidate() // Refresh the chart
        }
    }
}
