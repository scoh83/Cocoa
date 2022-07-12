package com.www.cocoastudio

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Step  (
    @PrimaryKey
    var id: String? = null,
    var name: String? = null

) : RealmObject()