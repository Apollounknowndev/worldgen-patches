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

// Remember the magic words: `./gradlew remapFabric1214ClientMinecraftIntermediary`
cloche {
	metadata {
		modId = "worldgen_patches"
		name = "Worldgen Patches"
		description = "Fixes several small issues in worldgen."
		license = "MIT"

		author("Apollo")
	}

	common {
		accessWideners.from(file("src/common/main/resources/worldgen_patches.accesswidener"))

		dependencies {
			compileOnly("org.spongepowered:mixin:0.8.3")
			compileOnly("io.github.llamalad7:mixinextras-common:0.3.5")
		}
	}

	fabric("fabric:1.21.1") {
		mixins.from(file("src/common/main/resources/worldgen_patches.mixins.json"))
		accessWideners.from(file("src/common/main/resources/worldgen_patches.accesswidener"))

		minecraftVersion = "1.21.1"
		loaderVersion = "0.16.10"

		includedClient()

		runs {
			client()
			server()
		}

		dependencies {
			fabricApi("0.115.2+1.21.1")
		}
	}

	fabric("fabric:1.21.4") {
		mixins.from(file("src/common/main/worldgen_patches.mixins.json"))
		accessWideners.from(file("src/common/main/resources/worldgen_patches.accesswidener"))

		minecraftVersion = "1.21.4"
		loaderVersion = "0.16.10"

		includedClient()

		runs {
			client()
			server()
		}

		dependencies {
			fabricApi("0.119.0+1.21.4")
		}
	}
}