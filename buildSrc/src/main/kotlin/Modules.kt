object Modules {
    object Core {
        private const val CORE = ":core"

        const val DATABASE = "$CORE:database"
        const val UI = "$CORE:ui"
        const val UTILS = "$CORE:utils"
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

        const val DATA = "$COMMONS:data"
        const val DOMAIN = "$COMMONS:domain"
        const val COMPONENTS = "$COMMONS:components"
        const val NAVIGATION = "$COMMONS:navigation"
    }
}
