# Changelog

All notable changes to Simple Farming: Continued are documented here. The project
targets Minecraft 1.21.1.

## [1.21.1-2.2.9] - multi-loader stable

First **stable** release of the multi-loader port, built from the `1.21.1` branch. Same
content, recipes, tags, loot tables and registry ids as the 2.2.9 betas. No gameplay or
content changes.

### Fixed
- Forge and NeoForge mod metadata now reports the correct version. `mods.toml` /
  `neoforge.mods.toml` had a hardcoded version that was missed during the beta bumps and
  still read `1.21.1-2.2.9-beta.2`; the version is now stamped from `gradle.properties` at
  build time so it can no longer drift.

### Changed
- Forge and NeoForge metadata author attribution corrected. The continuation, **Fafiew aka
  FafaHima**, is credited first, and **enemeez** is credited as the author of the original
  Simple Farming. The description now states plainly that this is an unofficial continuation.

### Carried over from 1.21.1-2.2.9-beta.3
- Fabric: with Fabric API installed, the creative inventory no longer breaks. Fabric API's
  `fabric-item-group-api-v1` implements the same tab paging as the mod's own Fabric mixins,
  and the two clashed on the vanilla creative-screen classes. `SimpleFarmingMixinConfigPlugin`
  now detects that module and skips the mod's paging mixins when it is loaded, so Fabric API
  owns paging; without Fabric API the mod's own paging still runs as before.
- Fabric API is declared as an optional (`suggests`) dependency in the mod metadata.

### Compatibility
- Minecraft 1.21.1, Java 21.
- Forge 1.21.1-52.1.10+, NeoForge 21.1.0+, Fabric Loader 0.16.5+.
- Fabric API is optional on Fabric; the mod runs with or without it.

## [1.21.1-2.2.9-beta.3] - multi-loader beta

Third multi-loader beta, built from the `1.21.1` branch. Makes Fabric API an optional
dependency and hands creative-tab paging to it when present. No gameplay or content changes.

### Fixed
- Fabric: with Fabric API installed, the creative inventory no longer breaks. Fabric API's
  `fabric-item-group-api-v1` implements the same tab paging as the mod's own Fabric mixins,
  and the two clashed on the vanilla creative-screen classes. `SimpleFarmingMixinConfigPlugin`
  now detects `fabric-item-group-api-v1` and skips the mod's paging mixins when it is loaded,
  so Fabric API owns paging; without Fabric API the mod's own paging still runs as before.

### Compatibility
- Minecraft 1.21.1, Java 21.
- Forge 1.21.1-52.1.10+, NeoForge 21.1.0+, Fabric Loader 0.16.5+.
- Fabric API is optional on Fabric (`suggests`); the mod runs with or without it.

## [1.21.1-2.2.9-beta.2] - multi-loader beta

Second multi-loader beta, built from the `1.21.1Tri` branch. Focus is the Fabric build:
rendering parity and creative-inventory usability. No gameplay or content changes.

### Fixed
- Fabric foliage (crops, flowers, saplings, berry bushes, gourds, grapevine, trellis) now
  renders with the cutout layer. Vanilla ignores a model's `render_type` attribute, so the
  Fabric client now inserts the affected blocks into `ItemBlockRenderTypes`.
- Fabric creative inventory: the mod's tab no longer lands on an off-grid slot. 1.21.1
  fills all 14 vanilla tab slots, so the Fabric client gives the tab its own page with
  `<`/`>` navigation.
- Shared creative-tab fallback column corrected from 12 to 7 (vanilla top-row tabs occupy
  columns 0–6).

### Compatibility
- Minecraft 1.21.1, Java 21.
- Forge 1.21.1-52.1.10+, NeoForge 21.1.0+, Fabric Loader 0.16.5+.

## [1.21.1-2.2.9-beta.1] - multi-loader beta

First **beta** build of the multi-loader port. Same content and gameplay as the Forge
release, now built for Forge, NeoForge and Fabric from one codebase.

### Added
- **NeoForge 1.21.1** distribution.
- **Fabric 1.21.1** distribution.
- Shared `common/` source set compiled into every loader with that loader's mappings, so
  all three distributions ship identical content, recipes, tags, loot tables, models,
  textures and registry identifiers.
- `AGENTS.md` with repository/build conventions.
- README sections for supported loaders, dependencies, building and project layout.

### Changed
- Reorganised the project into `common/` + `forge/` + `neoforge/` + `fabric/` subprojects.
- Build outputs are now named per loader:
  - `simplefarmingcontinued-<version>-forge-1.21.1.jar`
  - `simplefarmingcontinued-<version>-neoforge-1.21.1.jar`
  - `simplefarmingcontinued-<version>-fabric-1.21.1.jar`
- World generation is injected per loader:
  - Forge: `forge:add_features` biome modifiers (shipped only in the Forge jar).
  - NeoForge: `neoforge:add_features` biome modifiers (shipped only in the NeoForge jar).
  - Fabric: code-based biome feature injection driven by `simplefarming` biome tags.
- Moved the client-only colour/screen listeners out of the loader mod constructors into
  client-only event subscribers, so the dedicated server no longer loads client classes.
- Merged the maintainer's README content with the new multi-loader build documentation.

### Fixed
- Forge/NeoForge dedicated-server crash:
  `Attempted to load class net/minecraft/client/color/block/BlockColors for invalid dist DEDICATED_SERVER`.
- Fabric client crash from registering block/item colours before the colour registries
  existed (colours are now registered from the `Minecraft` constructor).
- Fabric crop/flower/sapling/berry/gourd/grape blocks rendered opaque instead of cutout:
  vanilla (Fabric) ignores the model `render_type` attribute, so the Fabric client now
  registers the matching cutout render layer from `ItemBlockRenderTypes`.
- Forge `issueTrackerURL` now points at this repository.
- Fabric creative inventory: the mod's tab could land on an off-grid slot because 1.21.1
  fills all 14 vanilla tab slots; the Fabric client now gives the tab its own page with
  `<`/`>` navigation.
- Shared creative-tab fallback column corrected from 12 to 7 (vanilla top-row tabs occupy
  columns 0–6 for 1.21.1).

### Compatibility
- Minecraft 1.21.1, Java 21.
- Forge 1.21.1-52.1.10+, NeoForge 21.1.0+, Fabric Loader 0.16.5+.
- **No Fabric API, Architectury or other shared-library dependency is required.**
- JEI 19.57.0.451+ is **optional** on every loader and never required at runtime.

### Notes / limitations
- Fabric world generation is code-based (Fabric has no biome-modifier facility without
  Fabric API), so terrain is not seed-for-seed identical across loaders.
- The Fabric build could not be launched with JEI in the build environment because the
  published JEI Fabric jars are remapped with a newer Loom than this project uses; the
  plugin is compiled against the JEI 1.21.1 API and wired via the `jei_mod_plugin`
  entrypoint.

## [1.21.1-2.2.8] - Forge

- Rebranded the mod as **Simple Farming: Continued** (display name, mod link, description
  and authors updated; new mod icon).
- Includes the JEI integration from 1.21.1-2.2.7 (categories, recipes, transfer handlers
  and the fermenter GUI background).

## [1.21.1-2.2.7] - Forge

- Re-enabled JEI integration for 1.21.1.

## [1.21.1] - Forge

- Ported SimpleFarming to Minecraft 1.21.1 (Forge 52.1.10).
