/*tasks.register("createJavaDoc", Javadoc::class){
    source = sourceSets.create("digitalTwinsAPI.src.main.java").java
}*/

dependencies {
    implementation("com.azure:azure-digitaltwins-core:1.0.3")
    implementation("com.azure:azure-identity:1.2.4")
    implementation("com.azure:azure-core-http-okhttp:1.6.1")
    implementation("org.apache.clerezza.ext:org.json.simple:0.4")
    testImplementation("junit:junit:4.13")
}
