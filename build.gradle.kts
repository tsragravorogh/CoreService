import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectVersion: String by project
val groupName: String by project

val targetJvm: String by project
val springBootVersion: String by project
val kotlinVersion: String by project
val jacksonVersion: String by project

group = groupName
version = projectVersion
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
}

dependencies {
    //Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    //SpringBoot
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = targetJvm
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
