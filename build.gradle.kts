import com.techshroom.inciseblue.commonLib

plugins {
    id("org.rivierarobotics.gradlerioredux") version "0.3.0"
    id("net.ltgt.apt-idea") version "0.20"
    id("net.ltgt.apt-eclipse") version "0.20"
}
gradleRioRedux {
    robotClass = "org.rivierarobotics.robot.Robot"
    teamNumber = 5818
    addCtre = true
}

repositories {
    mavenLocal()
}

dependencies {
	implementation("com.armabot:lidar-contrib:0.3.0-SNAPSHOT")
}
