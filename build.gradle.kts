import org.rivierarobotics.frcgrantle.FirstVersionSet
import org.rivierarobotics.frcgrantle.LibraryKind
import org.rivierarobotics.frcgrantle.SimpleDep

plugins {
    id("org.rivierarobotics.frcgrantle") version "0.1.9"
}
grantle {
    packageBase = "org.rivierarobotics.robot"
    teamNumber = 5818

    val wpi = "2018.424242.4.1"
    val wpiUtil = "3.2.0"
    val openCv = "3.2.0"
    val csCore = "1.3.0"
    val ntCore = "4.1.0"
    val ctr = "5.3.1.0"
    versionSet(FirstVersionSet.create().apply {
        addFirstLibrary("wpilib", SimpleDep.getWPILIB().withVersion(wpi),
                listOf(LibraryKind.builtInJava(), LibraryKind.jni()))
        addUserLibrary(SimpleDep.create("edu.wpi.first.hal", "hal", wpi),
                listOf(LibraryKind.nativeKind("", "zip")))

        addFirstLibrary("wpiutil", SimpleDep.getWPI_UTIL().withVersion(wpiUtil),
                listOf(LibraryKind.builtInJava(), LibraryKind.cpp()))

        addFirstLibrary("opencv", SimpleDep.getOPENCV().withVersion(openCv),
                listOf(LibraryKind.builtInJava(), LibraryKind.jni(), LibraryKind.cpp()))

        addFirstLibrary("cscore", SimpleDep.getCSCORE().withVersion(csCore),
                listOf(LibraryKind.builtInJava(), LibraryKind.cpp()))
//        addUserLibrary(SimpleDep.create("edu.wpi.first.cameraserver", "cameraserver", wpi),
//                listOf(LibraryKind.builtInJava(), LibraryKind.cpp()))
//        addUserLibrary(SimpleDep.create("edu.wpi.first.ni-libraries", "ni-libraries", wpi),
//                listOf(LibraryKind.nativeKind("", "zip")))

        addFirstLibrary("ntcore", SimpleDep.getNTCORE().withVersion(ntCore),
                listOf(LibraryKind.builtInJava(), LibraryKind.cpp()))

        addUserLibrary(SimpleDep.getCTR_LIB().withVersion(ctr),
                listOf(LibraryKind.builtInJava(), LibraryKind.cpp()))
    })
}
repositories {
    mavenLocal()
    maven { url = uri("https://jitpack.io") }
}
dependencies {
    compile("org.rivierarobotics:pololu-frc-contrib:0.1.2-SNAPSHOT") {
        exclude("edu.wpi.first.hal", "hal-jni")
        exclude("edu.wpi.first.hal", "hal-java")
    }
}
