import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id("com.google.protobuf") version "0.8.17"
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.14.0"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}


dependencies {
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonAsynchronous))

    implementation(project(Modules.featureProductCategoryRepository))
    implementation(project(Modules.featureProductCategoryDb))
    implementation(project(Modules.featureProductCategoryDomain))

    implementation(project(Modules.featureSignUpDomain))
    implementation(project(Modules.featureSignUpRepository))

    implementation(Deps.AndroidX.paging)
    implementation(Deps.AndroidX.dataStore)
    implementation(Deps.AndroidX.dataStorePreferences)
    implementation(Deps.Google.protobuf)

    coreLibraryDesugaring(Deps.desugar)
}