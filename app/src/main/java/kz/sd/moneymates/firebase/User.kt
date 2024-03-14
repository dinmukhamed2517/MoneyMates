package kz.sd.moneymates.firebase

data class User(
    var name:String? = null,
    var lastname:String?= null,
    var age:Int? = null,
    var phoneNumber:Long? =null,
    var pictureUrl: String? = null,
    var savingList: Map<String, Saving> = emptyMap(),
    var totalBalance:Double = 0.0,
)
