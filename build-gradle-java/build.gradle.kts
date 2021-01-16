plugins {
    application
}

application {
    mainClass.set("io.mickeckemi21.springioguides.buildgradlejava.HelloWorld")
}

dependencies {
    implementation("joda-time:joda-time:2.2")
    testImplementation("junit:junit:4.12")
}

tasks.jar {
    archiveBaseName.set("gs-gradle")
    archiveVersion.set("0.1.0-SNAPSHOT")
}
