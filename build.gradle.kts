plugins {
    id("java")
//    id("io.qameta.allure") version "<latest>"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation ("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation ("org.seleniumhq.selenium:selenium-java:4.21.0")
    testImplementation ("commons-io:commons-io:2.16.1")
    testImplementation ("io.github.bonigarcia:webdrivermanager:5.9.2")
    testImplementation ("com.intuit.karate:karate-core:1.4.1")
    testImplementation ("com.intuit.karate:karate-junit5:1.4.1")
    testImplementation ("net.masterthought:cucumber-reporting:5.8.0")
    testImplementation ("com.intuit.karate:karate-apache:0.9.6")
    testImplementation ("io.qameta.allure:allure-junit5:2.27.0")
    testImplementation ("io.qameta.allure:allure-assertj:2.27.0")
    testImplementation ("io.qameta.allure:allure-rest-assured:2.27.0")
    testImplementation ("io.qameta.allure:allure-java-commons:2.27.0")
    testImplementation ("org.aspectj:aspectjweaver:1.9.22")
    testImplementation ("ch.qos.logback:logback-classic:1.5.6");
//    testImplementation ("org.seleniumhq.selenium:selenium-devtools:4.0.0-rc-1")
}

tasks.test {
    useJUnitPlatform()
    reports {
        junitXml.required = true
        junitXml.outputLocation = file("${layout.buildDirectory}/allure-results")
    }
}
