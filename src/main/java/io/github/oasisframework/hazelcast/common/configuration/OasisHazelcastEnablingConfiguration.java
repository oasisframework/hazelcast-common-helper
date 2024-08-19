package io.github.oasisframework.hazelcast.common.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty (name = "oasis.hazelcast.enabled")
@ComponentScan ("io.github.oasisframework.hazelcast")
public class OasisHazelcastEnablingConfiguration {
}
