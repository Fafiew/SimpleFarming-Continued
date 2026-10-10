<a href="https://www.curseforge.com/minecraft/mc-mods/simple-farming"><img src="http://cf.way2muchnoise.eu/full_1735258_downloads.svg" /></a>

# Simple Farming: Continued

**The same simple farming experience, continued on newer Minecraft versions.**

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

Thank you for helping keep Simple Farming growing!
