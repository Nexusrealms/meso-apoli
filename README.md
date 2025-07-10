# Meso-Apoli
A backport of [neo-apoli](https://github.com/eggohito/neo-apoli), a proof-of-concept modern rewrite of [apoli](https://github.com/apace100/apoli), the library that powers the popular origins mod.

Currently unstable, with many apoli features (mainly individual action and condition types) missing.
As of now undocumented. Meso-Apoli should be as close to neo-apoli as possible, except for differences caused by minecraft versions, eg. the absence of `RenderState`s
## Usage
Meso-Apoli is unstable and developer support is small

If you want to use it anyway, you use the Nexusrealms Reposilite

Add this to your build.gradle.
Into the `repositories` block:
```
maven {
    name = "nexusrelamsSnapshots"
    url = uri("https://maven.riftrealms.de/snapshots")
}
```
Into the `dependencies` block:
```
modImplementation("de.nexusrealms:meso-apoli:0.1.0")
```