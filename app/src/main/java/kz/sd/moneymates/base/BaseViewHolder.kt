package kz.sd.moneymates.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kz.sd.moneymates.firebase.Learning
import kz.sd.moneymates.firebase.Saving
import kz.sd.moneymates.gpt.model.Choice
import kz.sd.moneymates.models.Shopping

abstract class BaseViewHolder<VB : ViewBinding, T>(protected open val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bindView(item: T)
}

abstract class BaseShoppingViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Shopping>(binding)

abstract class BaseSavingViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Saving>(binding)

abstract class BaseLearningViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Learning>(binding)
abstract class BaseMessageViewHolder<VB : ViewBinding>(override val binding: VB) :
    BaseViewHolder<VB, Choice>(binding)