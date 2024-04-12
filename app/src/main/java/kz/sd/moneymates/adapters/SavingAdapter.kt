package kz.sd.moneymates.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.sd.moneymates.base.BaseSavingViewHolder
import kz.sd.moneymates.databinding.ItemSavingPlanBinding
import kz.sd.moneymates.firebase.Saving

class SavingAdapter:ListAdapter<Saving, BaseSavingViewHolder<*>>(SavingDiffUtils()) {

    var itemClick:((Saving)->Unit)? = null
    class SavingDiffUtils:DiffUtil.ItemCallback<Saving>(){
        override fun areItemsTheSame(oldItem: Saving, newItem: Saving): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Saving, newItem: Saving): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSavingViewHolder<*> {
        return SavingViewHolder(
            ItemSavingPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseSavingViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }
    inner class SavingViewHolder(binding:ItemSavingPlanBinding):BaseSavingViewHolder<ItemSavingPlanBinding>(binding){
        override fun bindView(item: Saving) {
            with(binding){
                title.text = item.title
                amount.text  = item.amountOfMoney.toString()+" KZT"
            }
            itemView.setOnClickListener {
                itemClick?.invoke(item)
            }
        }

    }

}