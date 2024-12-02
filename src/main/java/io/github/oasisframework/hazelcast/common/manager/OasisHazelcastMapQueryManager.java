package io.github.oasisframework.hazelcast.common.manager;

import java.util.concurrent.TimeUnit;

public interface OasisHazelcastMapQueryManager {
	<K,V> void addValueToMap(String mapName, K key, V value);
	<K, V> void addValueToMap(String mapName, K key, V value, long ttl, TimeUnit ttlTimeUnit);
	<K, V> void addValueToMap(String mapName, K key, V value, long ttl, TimeUnit ttlTimeUnit, long maxIdle, TimeUnit maxIdleTimeUnit);
}
