import java.util.Calendar
import java.util.jar.Attributes

buildscript {
    dependencies {
        classpath(libs.dokka)
    }
}

plugins {
    application // Adds support for building a CLI application.

    `maven-publish`

    alias(libs.plugins.kotlin.jvm)

    alias(libs.plugins.shadow)

    alias(libs.plugins.dokka)
}

repositories {
    mavenCentral()

    maven("https://jitpack.io")
}

dependencies {
    implementation(libs.utility)

    testRuntimeOnly(libs.junit.platform)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jetbrains)
}

version = "1.1.1"
group = "dev.enderman"

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }

    withSourcesJar()
}

application {
    mainClass = "${group}.template.AppKt"
}

tasks {
    jar {
        manifest {
            attributes[Attributes.Name.MAIN_CLASS.toString()] = application.mainClass
        }
    }

    test {
        useJUnitPlatform()
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = rootProject.name
            version = version.toString()
        }
    }
}

dokka {
    moduleName.set("Kotlin Template")

    dokkaSourceSets.main {
        sourceLink {
            localDirectory.set(file("src/main/kotlin"))

            remoteUrl("https://github.com/esoterictemplates/template-kotlin-repository/${version}/main/src/main/kotlin")
        }
    }

    pluginsConfiguration.html {
        footerMessage.set("© ${Calendar.getInstance().get(Calendar.YEAR)} Esoteric Enderman")
    }
}
