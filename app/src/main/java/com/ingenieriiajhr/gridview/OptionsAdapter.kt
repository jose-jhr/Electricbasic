package com.ingenieriiajhr.gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ingenieriiajhr.electric.R

class OptionsAdapter(val context: Context,val listNames:ArrayList<String>,val listImg:ArrayList<Int>): BaseAdapter() {

    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return listNames.size
    }

    override fun getItem(p0: Int): String {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 0L
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var view = p1

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (view == null) {
            view = layoutInflater!!.inflate(R.layout.view_options, null)
        }

        val txts = view?.findViewById<TextView>(R.id.txtOptions)
        val imgOption = view?.findViewById<ImageView>(R.id.imgOption)

        txts!!.text = listNames[p0]
        imgOption?.setBackgroundResource(listImg[p0])

        return view

    }


}