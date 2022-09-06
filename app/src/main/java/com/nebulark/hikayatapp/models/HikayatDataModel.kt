package com.nebulark.hikayatapp.models

import java.io.Serializable

data class HikayatDataModel(var author:String="",var body:String="",var book:String="",var category:String="",
                            var keywords:ArrayList<String>,
                            var short_description:String="",var title:String =""):Serializable {


}