package com.example.getandpostrequestsbonus

import com.google.gson.annotations.SerializedName

class UsersDetails {
    @SerializedName("data")
    var data: List<Datum>? = null

    class Datum{
        @SerializedName("pk")
        var pk: String? = null

        @SerializedName("name")
        var name: String? = null

        constructor(name: String?) {
            this.name = name
        }
    }
}