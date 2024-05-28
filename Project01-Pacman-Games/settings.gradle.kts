dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        mavenLocal()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
        mavenCentral()
    }
}

pluginManagement {
    repositories {
//        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "H01-Student"
