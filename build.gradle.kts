import java.util.Calendar
import java.util.jar.Attributes

plugins {
    application // Adds support for building a CLI application.

    alias(libs.plugins.kotlin.jvm)

    alias(libs.plugins.release.axion)

    alias(libs.plugins.shadow)

    jacoco

    `maven-publish`

    alias(libs.plugins.dokka)
}

scmVersion {
    tag {
        prefix = ""
    }
}

version = scmVersion.version

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

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }

    withSourcesJar()
}

application {
    mainClass = "${group}.${property("package")}.MainKt"
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

    jacocoTestCoverageVerification {
        dependsOn(test)

        violationRules {
            rule {
                limit {
                    minimum = "1.0".toBigDecimal()
                }
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = name
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
