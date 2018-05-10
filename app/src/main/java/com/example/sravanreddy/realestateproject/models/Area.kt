package com.example.sravanreddy.realestateproject.models

import android.os.Parcel
import android.os.Parcelable

class Area {
    var area: List<AreaBean>? = null

    class AreaBean(private val area_size: String, private val area_unit: String, private val geo_key: String, private val area_id: String, private val area_name: String, private val area_type: String) : Parcelable {

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(area_size)
            dest.writeString(area_unit)
            dest.writeString(geo_key)
            dest.writeString(area_id)
            dest.writeString(area_name)
            dest.writeString(area_type)
        }

        companion object {
            //                 "area": "0.404499",
            //                 "area_unit": "sq. mi.",
            //                 "geo_key": "ND0001530215",
            //                 "id": "ND0001530215",
            //                 "name": "Central City Historic District",
            //                 "type": "ND"

            val CREATOR: Parcelable.Creator<Area> = object : Parcelable.Creator<Area> {
                override fun createFromParcel(source: Parcel): Area? {
                    return null
                }

                override fun newArray(size: Int): Array<Area?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
