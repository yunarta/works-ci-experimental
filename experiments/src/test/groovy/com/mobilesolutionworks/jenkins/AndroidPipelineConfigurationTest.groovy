package com.mobilesolutionworks.jenkins

import com.mobilesolutionworks.jenkins.features.CodeCoverageIOConfiguration
import com.mobilesolutionworks.jenkins.publisher.BintrayArtifactPublisher

class AndroidPipelineConfigurationTest extends GroovyTestCase {

    void testFrom() {
        def resource = AndroidPipelineConfigurationTest.class.classLoader.getResource("basic-android.yaml")

        def configuration = new AndroidPipelineConfiguration()
        configuration.from(resource.newReader().text)

        println(configuration)
        assertEquals("android", configuration.node)
        assertTrue(configuration.buildCommands.containsKey("check"))
        assertTrue(configuration.buildCommands.containsKey("buildSnapshot"))
        assertTrue(configuration.buildCommands.containsKey("buildRelease"))
        assertTrue(configuration.buildFeatures.get("codecov") instanceof CodeCoverageIOConfiguration)
        assertTrue(configuration.artifactPublishers.get("bintray") instanceof BintrayArtifactPublisher)
    }
}
