object BuildConfig {
    const val COMPILE_SDK = 35
    const val MIN_SDK = 24
    const val TARGET_SDK = 34

    const val VERSION_CODE = 3
    const val VERSION_NAME = "1.0.1"

    const val APP_ID = "com.fakhry.lifelog"
    private const val JAVA_VERSION_NUMBER = "11"

    const val JAVA_VERSION = "VERSION_$JAVA_VERSION_NUMBER"

    fun generateNamespace(name: String) = "$APP_ID.$name"
}

