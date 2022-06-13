object Dependency {
    object Kotlin {
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin.kotlin-stdlib:${Versions.KOTLIN_VERSION}"
        const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
        const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
    }

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
        const val LIFECYCLE_VIEWMODEL_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"
        const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"

        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

        const val DATA_STORE = "androidx.datastore:datastore:${Versions.DATA_STORE}"
        const val DATA_STORE_PREF = "androidx.datastore:datastore-preferences:${Versions.DATA_STORE}"
        const val DATA_STORE_CORE = "androidx.datastore:datastore-core:${Versions.DATA_STORE}"

        const val NAVIGATION_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    }

    object Google {
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_VIEWMODEL}"
        const val HILT_GRADLE = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"

        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

        const val EMOJI2 = "androidx.emoji2:emoji2:${Versions.EMOJI2}"
        const val EMOJI2_VIEWS = "androidx.emoji2:emoji2-views:${Versions.EMOJI2}"

//        const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
//        const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
//        const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
//        const val COMPOSE_FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout:${Versions.COMPOSE}"
//        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"
//        const val COMPOSE_RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}"
//        const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
//        const val COMPOSE_THEME_ADAPTER = "com.google.android.material:compose-theme-adapter:${Versions.COMPOSE}"
    }

    object Library {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
        const val OKHTTP_URL_CONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.OKHTTP}"

        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

        const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE}"

        const val DATEPICKER_TIMELINE = "com.github.shrikanth7698:Collapsible-Calendar-View-Android:v1.0.3"

        const val BLURRY = " jp.wasabeef:blurry:${Versions.BLURRY}"

        const val AWESOME_DIALOG = "com.github.chnouman:AwesomeDialog:${Versions.AWESOME_DIALOG}"
    }

    object UnitTest {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
    }

    object AndroidTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }

    object UI {
        const val TOASTY = "com.github.GrenderG:Toasty:${Versions.TOASTY}"
    }
}