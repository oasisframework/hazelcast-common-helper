package io.github.oasisframework.hazelcast.common;

import io.github.oasisframework.hazelcast.common.configuration.OasisHazelcastEnablingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Import(OasisHazelcastEnablingConfiguration.class)
public @interface EnableOasisHazelcast {
}
