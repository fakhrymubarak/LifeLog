object Modules {
    const val STORAGE = ":storage"
    const val CORE = ":core"

    object Features {
        private const val FEATURES = ":features"

        const val SETTINGS = "$FEATURES:settings"
    }

    object Commons {
        private const val COMMONS = ":commons"

        const val NAVIGATION = "$COMMONS:navigation"
        const val RESOURCES = "$COMMONS:resources"
        const val UTILS = "$COMMONS:utils"
    }
}
