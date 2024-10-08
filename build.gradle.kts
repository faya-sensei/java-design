plugins {
    id("java")
}

group = "org.faya.sensei"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.+"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.12.+")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.+")
    testImplementation("org.reflections:reflections:0.10.+")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}