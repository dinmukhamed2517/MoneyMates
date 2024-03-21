package kz.sd.moneymates.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import kz.sd.moneymates.base.BaseShoppingViewHolder
import kz.sd.moneymates.databinding.ItemShoppingBinding
import kz.sd.moneymates.models.Shopping

class ShoppingAdapter: ListAdapter<Shopping, BaseShoppingViewHolder<*>>(ShoppingDiffUtils()) {


    var itemClick:((Shopping) -> Unit)? = null
    class ShoppingDiffUtils:DiffUtil.ItemCallback<Shopping>(){
        override fun areItemsTheSame(oldItem: Shopping, newItem: Shopping): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Shopping, newItem: Shopping): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseShoppingViewHolder<*> {
        return ShoppingViewHolder(
            ItemShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseShoppingViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ShoppingViewHolder(binding: ItemShoppingBinding):BaseShoppingViewHolder<ItemShoppingBinding>(binding){
        override fun bindView(item: Shopping) {
            with(binding){
                title.text = item.title
                price.text = item.price.toString()+ " KZT"
                img.setImageResource(item.img)
            }
            itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }

    }
}