<div align="center">

<img src="common/src/main/resources/logo.png" alt="Simple Farming: Continued logo" width="200" />

# Simple Farming: Continued

**The same simple farming experience, continued on newer Minecraft versions.**

[![Modrinth downloads](https://img.shields.io/modrinth/dt/simple-farming-continued?label=Downloads&logo=modrinth&color=00AF5C)](https://modrinth.com/mod/simple-farming-continued)
[![CurseForge downloads](http://cf.way2muchnoise.eu/full_1735258_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/simple-farming-continued)

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-3C8527?logo=minecraft&logoColor=white)
![Loaders](https://img.shields.io/badge/Loaders-Forge%20%7C%20NeoForge%20%7C%20Fabric-6E7B8B)
![JEI](https://img.shields.io/badge/JEI-optional-6E7B8B)

</div>

Simple Farming: Continued is an independent continuation of [Simple Farming](https://www.curseforge.com/minecraft/mc-mods/simple-farming), originally created by **enemeez1**.

The goal is simple: keep the original Simple Farming experience alive by bringing it to newer Minecraft versions and additional mod loaders, making it accessible to more players while preserving what made the original mod enjoyable.

## 🌱 Features

Simple Farming expands Minecraft's farming and food system with:

- **New Crops** — Grow a wider variety of crops in your farms.
- **Berry Bushes** — Harvest berries as another source of food.
- **Fruit Trees** — Grow and harvest fruits from trees.
- **New Meals** — Turn your harvest into a variety of foods.
- **Brewing** — Explore the original mod's brewing content.

  ![All the items of the mod](https://cdn.modrinth.com/data/cached_images/d955c824c12a813eabf74d980edbaa6a24b112bb.png)

## 🔧 What is Simple Farming: Continued?

This project focuses on **compatibility, maintenance, and accessibility**.

- Ports the original mod to newer Minecraft versions.
- Brings support for additional mod loaders.
- Maintains the original gameplay and content as faithfully as possible.
- Addresses compatibility issues and version-specific changes where needed.

**No new gameplay features or content are planned.** The aim is to preserve the original experience, not redesign or expand it.

## ❤️ Credits

Simple Farming: Continued is based on the work of **enemeez1**, the original creator of Simple Farming. The original mod's content and design are the foundation of this project.

- **Original mod:** [Simple Farming on CurseForge](https://www.curseforge.com/minecraft/mc-mods/simple-farming)

This is an independent continuation and is not an official release of the original project. All required original copyright and license notices are retained in accordance with the applicable license.

## 📦 Compatibility

All three distributions share the same content, recipes, tags, loot tables, world generation and registry identifiers. There are no separate gameplay implementations — loader-specific code is limited to initialisation, registration glue and world-gen injection.

| Minecraft | Forge | NeoForge | Fabric |
|-----------|-------|----------|--------|
| 1.21.1    | yes   | yes      | yes    |

A normal install needs **only Minecraft 1.21.1 and the relevant mod loader**:

- **Forge 1.21.1** — Forge 52.1.10 or newer.
- **NeoForge 1.21.1** — NeoForge 21.1.0 or newer.
- **Fabric 1.21.1** — Fabric Loader 0.16.5 or newer.

**Fabric API is not required** and there is no dependency on Architectury or any other compatibility library. The Fabric build implements resource-pack mounting and world-gen injection directly against the vanilla / Fabric Loader APIs. Fabric API is an *optional* dependency: when it is installed the mod defers creative-tab paging to Fabric API's item-group module, and when it is absent the mod provides that paging itself.

On Fabric the mod's creative tab is reached with the page arrows (`<` / `>`) next to the creative inventory's search box; vanilla 1.21.1 fills every tab slot, so a second page is provided. Forge and NeoForge paginate the tab list natively, so no extra step is needed there.

### Optional: JEI

Just Enough Items is optional on every loader and is never required at runtime (JEI 19.57.0.451 or newer for 1.21.1). When JEI is present it shows the mod's items and a Fermenter recipe category with input, output, transfer and catalyst support. When JEI is absent the mod launches and plays normally; no JEI classes are loaded. JEI is declared as an *optional, client-side* dependency in each loader's metadata and is never bundled in the released jars.

## 🔨 Building

Requirements: **JDK 21**.

```bash
# Build all three distributions
./gradlew buildAll

# ...or one at a time
./gradlew :forge:build
./gradlew :neoforge:build
./gradlew :fabric:build

# Build everything and copy the shippable jars into build/libs
./gradlew collectJars
```

Output artifacts (in `build/libs`) use a consistent naming convention:

```
simplefarmingcontinued-<version>-forge-1.21.1.jar
simplefarmingcontinued-<version>-neoforge-1.21.1.jar
simplefarmingcontinued-<version>-fabric-1.21.1.jar
```

Each jar contains only its own loader's metadata (`META-INF/mods.toml`, `META-INF/neoforge.mods.toml` or `fabric.mod.json`) and only the world-gen data for that loader.

### Development runs

```bash
./gradlew :forge:runClient     # or :forge:runServer
./gradlew :neoforge:runClient
./gradlew :fabric:runClient
```

## 🗂️ Project layout

```
common/    shared, loader-agnostic source (content, recipes, resources, JEI plugin)
forge/     Forge entrypoint, init and world-gen data (forge:add_features)
neoforge/  NeoForge entrypoint, init and world-gen data (neoforge:add_features)
fabric/    Fabric entrypoint, resource-pack mounting, code-based world-gen injection
```

The `common` sources are compiled into each loader's source set with that loader's mappings. World generation differs by loader: Forge and NeoForge use data-driven biome modifiers, while Fabric rebuilds biome generation settings in code (Fabric has no biome modifier facility without Fabric API). Because the underlying world-gen data differs per loader, terrain is not seed-for-seed identical across loaders.

Thank you for helping keep Simple Farming growing!
