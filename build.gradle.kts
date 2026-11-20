import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
    id("atlas.maven-library")
}

description = "The API plugins use to read the servers of an Atlas network."

mavenPublishing {
    configure(JavaLibrary(JavadocJar.Javadoc(), SourcesJar.Sources()))

    pom {
        url = "https://github.com/getatlasgg/atlas-api"

        scm {
            url = "https://github.com/getatlasgg/atlas-api"
        }

        licenses {
            license {
                name = "MIT License"
                url = "https://opensource.org/license/mit"
            }
        }
    }
}
