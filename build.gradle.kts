import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.git.version) // https://stackoverflow.com/a/71212144
}

val pluginId = "com.jasonernst.jni-library"
val githubUrl = "https://github.com/compscidr/jni-library"
val webUrl = "https://github.com/compscidr/jni-library"
val projectDescription = "A gradle plugin for generating a JNI library"

version = "0.0.0-SNAPSHOT"
gitVersioning.apply {
    refs {
        branch(".+") { version = "\${ref}-SNAPSHOT" }
        tag("v(?<version>.*)") { version = "\${ref.version}" }
    }
}
group = "com.jasonernst.jni-library"
description = projectDescription

configurations {
    configureEach {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin" && requested.name.startsWith("kotlin")) {
                useVersion(getKotlinPluginVersion())
            }
        }
    }
}

dependencies {

}

tasks {

}

gradlePlugin {
    website.set(webUrl)
    vcsUrl.set(githubUrl)
    plugins {
        create("kotlinterPlugin") {
            id = pluginId
            displayName = "Kotlin Lint plugin"
            description = project.description
            tags.addAll(listOf("kotlin", "ktlint", "lint", "format", "style", "android"))
            implementationClass = "org.jmailen.gradle.kotlinter.KotlinterPlugin"
        }
    }
}

java {
    withSourcesJar()
}

publishing {
    publications.withType<MavenPublication> {

        pom {
            name.set(project.name)
            description.set(project.description)
            url.set(webUrl)

            scm {
                url.set(githubUrl)
            }

            licenses {
                license {
                    name.set("GNU Lesser General Public License v3.0")
                    url.set("https://www.gnu.org/licenses/lgpl-3.0.html")
                    distribution.set("repo")
                }
            }

            developers {
                developer {
                    id.set("compscidr")
                    name.set("Jason Ernst")
                }
            }
        }
    }
}