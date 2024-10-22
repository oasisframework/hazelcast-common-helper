package io.github.oasisframework.hazelcast.common.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HazelcastMapProperty {
	private String name;
	private int maxIdleSeconds;
	private int timeToLiveSeconds;
	private int maxAsyncBackupCounts;
}
