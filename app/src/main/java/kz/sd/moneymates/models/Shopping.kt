package kz.sd.moneymates.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize


@Parcelize
data class Shopping(
    var id:Int,
    var title:String,
    var price:Double,
    @DrawableRes
    var img:Int,
):Parcelable
