package kz.sd.moneymates.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kz.sd.moneymates.base.BaseLearningViewHolder
import kz.sd.moneymates.base.BaseShoppingViewHolder
import kz.sd.moneymates.databinding.ItemLearningBinding
import kz.sd.moneymates.databinding.ItemShoppingBinding
import kz.sd.moneymates.firebase.Learning
import kz.sd.moneymates.models.Shopping

class LearningAdapter:ListAdapter<Learning, BaseLearningViewHolder<*>>(LearningDiffUtils()){


    var itemClick:((Learning) -> Unit)? = null

    class LearningDiffUtils:DiffUtil.ItemCallback<Learning>(){
        override fun areItemsTheSame(oldItem: Learning, newItem: Learning): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Learning, newItem: Learning): Boolean {
            return  oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseLearningViewHolder<*> {
        return LearningViewHolder(
            ItemLearningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseLearningViewHolder<*>, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class LearningViewHolder(binding: ItemLearningBinding):
        BaseLearningViewHolder<ItemLearningBinding>(binding){
        override fun bindView(item: Learning) {
            with(binding){
                title.text = item.title
                description.text = item.reward.toString()+" KZT"
                img.setImageResource(item.img)

                itemView.setOnClickListener {
                    itemClick?.invoke(item)
                }
            }
        }

    }

}