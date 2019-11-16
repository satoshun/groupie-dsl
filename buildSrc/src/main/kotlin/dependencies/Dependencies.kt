package dependencies

const val COMPILE_SDK = 29
const val MIN_SDK = 18
const val TARGET_SDK = 29

private const val VKOTLIN = "1.3.60"
private const val VNAVIGATION = "1.0.0"

const val ANDROID_PLUGIN = "com.android.tools.build:gradle:3.6.0-beta04"
const val KOTLIN_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VKOTLIN"
const val SAFEARGS_PLUGIN = "android.arch.navigation:navigation-safe-args-gradle-plugin:$VNAVIGATION"
const val VERSIONS_PLUGIN = "com.github.ben-manes:gradle-versions-plugin:0.20.0"

private const val VCOUROUTINE = "1.3.2"
const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:$VKOTLIN"
const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VCOUROUTINE"
const val UI_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VCOUROUTINE"

const val KTX = "androidx.core:core-ktx:1.1.0"
const val ACTIVITYX = "androidx.activity:activity-ktx:1.0.0"
const val FRAGMENTX = "androidx.fragment:fragment-ktx:1.1.0"

const val APPCOMPAT = "androidx.appcompat:appcompat:1.1.0"
const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.0.0"
const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:2.0.0-beta3"
const val CARDVIEW = "androidx.cardview:cardview:1.0.0"

const val CONTENTPAGER = "androidx.contentpager:contentpager:1.0.0-rc01"
const val WEBKIT = "androidx.webkit:webkit:1.0.0"
const val MEDIA2 = "androidx.media2:media2:1.0.0-alpha04"

const val MATERIAL = "com.google.android.material:material:1.1.0-beta02"
const val PAGING = "androidx.paging:paging-runtime:2.1.0"
const val BROWSER = "androidx.browser:browser:1.0.0"
const val NAVIGATION = "android.arch.navigation:navigation-fragment-ktx:$VNAVIGATION"
const val NAVIGATION_UI = "android.arch.navigation:navigation-ui-ktx:$VNAVIGATION"

const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-rc02"
const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0-rc02"
const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:2.0.0"
const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-rc02"

const val MULTIDEX = "androidx.multidex:multidex:2.0.0"

const val GROUPIE = "com.xwray:groupie:2.7.1"
const val GROUPIE_DATABINDING = "com.xwray:groupie-databinding:2.7.1"

const val ANDROID_ANNOTATION = "androidx.annotation:annotation:1.0.0"

const val GLIDE = "com.github.bumptech.glide:glide:4.9.0"

const val JUNIT = "junit:junit:4.12"
const val TRUTH = "com.google.truth:truth:1.0"
const val MOCKITO_KOTLIN = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
const val TEST_RUNNER = "androidx.test:runner:1.1.1"
const val TEST_RULE = "androidx.test:rules:1.1.0"
const val ESPRESSO = "androidx.test.espresso:espresso-core:3.1.1"
