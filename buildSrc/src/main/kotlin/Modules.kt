object Modules {
    const val STORAGE = ":storage"
    const val CORE_OLD = ":core-old"

    object Core {
        const val DATABASE = ":core:database"
        const val UI = ":core:ui"
        const val UTILS = ":core:utils"
    }

    object Features {
        private const val FEATURES = ":features"

        const val SETTINGS = "$FEATURES:settings"
        const val FAVORITES = "$FEATURES:favorites"
        const val CALENDAR = "$FEATURES:calendar"
        const val DASHBOARD = "$FEATURES:dashboard"
        const val ONBOARDING = "$FEATURES:onboarding"
        const val DETAILS = "$FEATURES:details"
    }

    object Commons {
        private const val COMMONS = ":commons"

        const val COMPONENTS = "$COMMONS:components"
        const val NAVIGATION = "$COMMONS:navigation"
        const val RESOURCES = "$COMMONS:resources"
        const val UTILS = "$COMMONS:utils"
    }
}
