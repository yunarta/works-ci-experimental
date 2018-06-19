package com.mobilesolutionworks.jenkins

import com.mobilesolutionworks.jenkins.features.BuildFeature
import com.mobilesolutionworks.jenkins.publisher.ArtifactPublisher
import org.yaml.snakeyaml.Yaml

//@Grab("org.yaml:snakeyaml:1.21")

class AndroidPipelineConfiguration {

    String node

    Map<String, BuildCommands> buildCommands = new HashMap<>()
    Map<String, BuildFeature> buildFeatures = new HashMap<>()
    Map<String, ArtifactPublisher> artifactPublishers = new HashMap<>()

    def from(String text) {
        Yaml yaml = new Yaml()
        def document = yaml.load(text)

        this.node = document.node

        Map<String, ?> commands = document.commands as Map
        for (entry in commands.entrySet()) {
            buildCommands.put(entry.key,
                    new BuildCommands(
                            name: entry.key,
                            commands: entry.value as List<String>
                    ))
        }

        Map<String, ?> features = document.features as Map
        for (entry in features.entrySet()) {
            Closure<BuildFeature> action = FeaturesMap.features[entry.key]
            buildFeatures.put(entry.key, action.call(entry.value))
        }

        Map<String, ?> publishers = document.publishers as Map
        for (entry in publishers?.entrySet()) {
            Closure<ArtifactPublisher> action = FeaturesMap.publishers[entry.key]
            artifactPublishers.put(entry.key, action.call(entry.value))
        }
    }


    @Override
    String toString() {
        return "AndroidPipelineConfiguration{" +
                "node='" + node + '\'' +
                '}'
    }
}
