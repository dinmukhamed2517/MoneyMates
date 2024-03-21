package kz.sd.moneymates.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Saving(
    var title:String? = null,
    var amountOfMoney:Double? = null,
    var note:String? = null,
):Parcelable
