package io.github.oasisframework.hazelcast.common.manager;

public interface OasisHazelcastMapCommandManager {
	<K,V> V getValueFromMap(String mapName, K key);
}
