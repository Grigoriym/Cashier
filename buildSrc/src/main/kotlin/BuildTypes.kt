interface BuildType {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
        const val STAGING = "staging"
    }

    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
    val isDebuggable: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true
    override val isDebuggable: Boolean = true

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean = true
    override val isTestCoverageEnabled: Boolean = false
    override val isDebuggable: Boolean = false
}

object BuildTypePreStaging : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true
    override val isDebuggable: Boolean = true
}