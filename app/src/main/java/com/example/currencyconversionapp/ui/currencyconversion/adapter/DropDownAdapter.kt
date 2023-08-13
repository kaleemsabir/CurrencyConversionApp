package com.example.currencyconversionapp.ui.currencyconversion.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.currencyconversionapp.R
import com.example.currencyconversionapp.data.local.db.models.ConversionRatesDbModel


class DropDownAdapter(
    private val list: List<ConversionRatesDbModel>,
    context: Activity,
    private val listener: (ConversionRatesDbModel) -> Unit
) : ArrayAdapter<ConversionRatesDbModel>(context, 0, list) {

    private var mInflater: LayoutInflater? = null
    private val noFilter = NoFilter()

    init {
        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = mInflater?.inflate(R.layout.item_list_drop_down, null)
        }
        val model: ConversionRatesDbModel? = list[position]
        if (model != null) {
            val itemText = view?.findViewById(R.id.item) as TextView?
            itemText?.text = model.currencyName
            itemText?.setOnClickListener {
                listener.invoke(model)
            }
        }
        return view!!
    }

    override fun getFilter(): Filter {
        return noFilter
    }

    inner class NoFilter : Filter() {
        override fun performFiltering(arg0: CharSequence): FilterResults {
            val result = FilterResults()
            result.values = list
            result.count = list.size
            return result
        }

        override fun publishResults(arg0: CharSequence, arg1: FilterResults) {
            notifyDataSetChanged()
        }
    }
}

