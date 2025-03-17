plugins {
	kotlin("jvm") version "2.0.0"
	id ("earth.terrarium.cloche") version "0.8.13"
}

repositories {
	maven (
		url = "https://maven.msrandom.net/repository/root"
	)
}


version = "1.0.0"
group = "dev.worldgen"

cloche {
	metadata {
		modId = "worldgen_patches"
		name = "Worldgen Patches"
		description = "Fixes several small issues in worldgen."
		license = "MIT"

		author("Apollo")
	}

	fabric("fabric:1.20.1") {
		minecraftVersion = "1.20.1"
		loaderVersion = "0.16.10"

		runs {
			client()
			server()
		}

		dependencies {
			fabricApi("0.92.3+1.20.1")
		}
	}

	fabric("fabric:1.21.1") {
		minecraftVersion = "1.21.1"
		loaderVersion = "0.16.10"

		runs {
			client()
			server()
		}

		dependencies {
			fabricApi("0.115.1+1.21.1")
		}
	}
}