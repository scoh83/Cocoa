package com.www.cocoastudio

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application()  {
    override fun onCreate() {
        super.onCreate()

        // Realm  초기화
        // 응용 프로그램이 시작될 때 한 번만 수행
        Realm.init(this);

        // 초기 Realm DB 구성
        val config : RealmConfiguration = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .name("user.realm")             // 생성할 realm db 이름
            .deleteRealmIfMigrationNeeded()
            .build()


        Realm.setDefaultConfiguration(config)

    }
}