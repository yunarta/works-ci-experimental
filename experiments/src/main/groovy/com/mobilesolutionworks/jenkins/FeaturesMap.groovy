package com.mobilesolutionworks.jenkins

import com.mobilesolutionworks.jenkins.features.BuildFeature
import com.mobilesolutionworks.jenkins.features.CodeCoverageIOConfiguration
import com.mobilesolutionworks.jenkins.publisher.ArtifactPublisher
import com.mobilesolutionworks.jenkins.publisher.BintrayArtifactPublisher

class FeaturesMap {

    static LinkedHashMap<String, Closure<BuildFeature>> features = [
            "codecov": {
                def configuration = new CodeCoverageIOConfiguration()
                configuration.configure(it)

                return configuration
            }
    ]

    static LinkedHashMap<String, Closure<ArtifactPublisher>> publishers = [
            "bintray": {
                def configuration = new BintrayArtifactPublisher()
                configuration.configure(it)

                return configuration
            }
    ]
}
