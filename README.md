# Atlas plugin API

[![Maven Central](https://img.shields.io/maven-central/v/gg.getatlas/atlas-api)](https://central.sonatype.com/artifact/gg.getatlas/atlas-api)

[Atlas](https://getatlas.gg) is a control panel and autoscaler for Minecraft
networks. Through this API, your own plugins read the network: every running
server with its players and metadata, the metadata of their own server, and
servers as they come and go.

## Dependency

A Paper, Velocity or BungeeCord plugin gets the API from the Atlas plugin at
runtime, so it compiles against it only:

```kotlin
dependencies {
    compileOnly("gg.getatlas:atlas-api:<version>")
}
```

The API is on Maven Central. The full reference is on
[javadoc.io](https://javadoc.io/doc/gg.getatlas/atlas-api), and the
[plugin API guide](https://getatlas.gg/docs/api) shows how to load it.

## Source

This repository mirrors the API from the Atlas repository on each release.

## Licence

MIT. See [LICENSE](LICENSE).
