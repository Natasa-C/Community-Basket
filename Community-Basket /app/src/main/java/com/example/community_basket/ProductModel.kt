package com.example.community_basket

import android.os.Parcel
import android.os.Parcelable

data class ProductModel(
    val product_name: String?,
    val product_location: String?,
    val product_price: Float,
    val product_unit: String?,
    val imageId: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeStringArray(
            arrayOf(
                product_name,
                product_location,
                product_price.toString(),
                product_unit,
                imageId.toString()
            )
        )
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }

}
