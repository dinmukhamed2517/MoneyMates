package kz.sd.moneymates.firebase

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize


@Parcelize
data class Learning(
    var id:Int,
    var title:String,
    var reward:Double,
    var url:String,
    @DrawableRes
    var img:Int,
):Parcelable