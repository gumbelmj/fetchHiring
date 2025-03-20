package com.fetch.hiring.ui

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.fetch.hiring.R
import com.fetch.hiring.model.Item

class ExpandableItemListAdapter(private val layoutInflater: LayoutInflater) :
    BaseExpandableListAdapter() {

    private val itemMap = HashMap<Int, List<Item>>()

    override fun getChild(listPosition: Int, expandedListPosition: Int): Item? {
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
        textView.text = getChild(listPosition, expandedListPosition)?.name

        return itemView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return itemGroup(listPosition)?.size ?: 0
    }

    override fun getGroup(listPosition: Int): Int? {
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

    fun update(items: Map<Int, List<Item>>) {
        itemMap.clear()
        itemMap.putAll(items)
        notifyDataSetChanged()
    }

    private fun itemGroup(listPosition: Int): List<Item>? {
        val group = getGroup(listPosition)
        return itemMap[group]
    }

}