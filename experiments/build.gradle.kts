plugins {
    `java-library`
    groovy
}

group = "com.mobilesolutionworks"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.codehaus.groovy:groovy-all:2.3.11")
    testCompile("junit:junit:4.12")

    implementation("org.yaml:snakeyaml:1.21")
}

tasks.withType<Test> {
    maxParallelForks = Runtime.getRuntime().availableProcessors().div(2)
    doFirst {
        logger.quiet("Test with max $maxParallelForks parallel forks")
    }
}
