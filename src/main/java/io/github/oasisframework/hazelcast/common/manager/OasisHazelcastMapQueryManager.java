package io.github.oasisframework.hazelcast.common.manager;

public interface OasisHazelcastMapQueryManager {
	<K,V> void addValueToMap(String mapName, K key, V value);
}
