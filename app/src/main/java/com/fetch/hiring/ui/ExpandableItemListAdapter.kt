package com.fetch.hiring.ui

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.fetch.hiring.R

class ExpandableItemListAdapter(private val layoutInflater: LayoutInflater) :
    BaseExpandableListAdapter() {

    private val itemMap = HashMap<String, List<String>>()

    override fun getChild(listPosition: Int, expandedListPosition: Int): String? {
        return itemGroup(listPosition)?.get(expandedListPosition)
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var itemView = convertView ?: layoutInflater.inflate(R.layout.list_item, null)

        val textView = itemView.findViewById<View?>(R.id.expandedListItem) as TextView
        textView.text = getChild(listPosition, expandedListPosition)

        return itemView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return itemGroup(listPosition)?.size ?: 0
    }

    override fun getGroup(listPosition: Int): String? {
        return itemMap.keys.sorted()[listPosition]
    }

    override fun getGroupCount(): Int {
        return itemMap.keys.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var groupView = convertView ?: layoutInflater.inflate(R.layout.list_group, null)

        val textView = groupView.findViewById<View?>(R.id.listTitle) as TextView
        textView.setTypeface(null, Typeface.BOLD)
        textView.text = getGroup(listPosition).toString()

        return groupView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    fun update(items: Map<String, List<String>>) {
        itemMap.clear()
        itemMap.putAll(items)
        notifyDataSetChanged()
    }

    private fun itemGroup(listPosition: Int): List<String>? {
        val group = getGroup(listPosition)
        return itemMap[group]
    }

}