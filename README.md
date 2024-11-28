# Hazelcast Common Helper

It provides an abstraction for hazelcast and capabilities.

## Properties
`hazelcast: enabled: true` : It enables hazelcast connection. 
If the `enabled` property was set as `false`, microservice will not connect to the hazelcast cluster
### Connection Properties
- `connectionName`: It means **Cluster Name** in hazelcast 5.x.x or **Group Name** in hazelcast 3.x.x
- `connectionPassword`: When you use hazelcast 3, you have to set password. In hazelcast 5, it is deprecated and not necessary.
- `address`: It refers to Cluster or group address. It can be one or more Ip addresses or DNS names.
- `instanceName`: It is optional but if you monitorize your connected hazelcast instances, you can specify a name.
- `creatableMaps`: You can configure maps to create. Maps can be configurable and there are 4 property for it.
**Note: It has to be a list**
### Map Properties
- `name`: Map name
- `maxIdleSeconds`: Maximum time in seconds for each entry to stay idle in the map. (More )
- `timeToLiveSeconds`: It refers to Cluster or group address. It can be one or more Ip addresses or DNS names.
- `maxAsyncBackupCounts`: It is optional but if you monitorize your connected hazelcast instances, you can specify a name.


## Usage

To use in your microservice, you need to follow this steps;
### 1. Add Dependencies
- Add `hazelcast-common-helper` dependency into your gradle file.
- Add related hazelcast helper version you want to use.
  (`hazelcast-3-helper` or `hazelcast-5-helper`)
- Add related hazelcast dependency you want to use.
  * for example, if you want to use `hazelcast-5-helper`, you have to use `hazelcast 5.x.x`

```gradle
    implementation("io.github.oasisframework:hazelcast-common-helper:1.0.2")
    implementation("io.github.oasisframework:hazelcast-5-helper:1.0.2")
    implementation("com.hazelcast:hazelcast:5.3.6")
```
### 2. Configuring Connection Properties and Activating Hazelcast

After adding dependencies, you have to configure hazelcast connection.
To configure your hazelcast connection basically, you can modify application.yaml like this;
```yaml
oasis:
  hazelcast: 
    enabled: true
  hz-connection:
    connectionName: "my-cluster"
    connectionPassword: "myPassword"
    address: 127.0.0.1, 127.0.0.2, 127.0.0.3:5071
    instanceName: "MY_MICROSERVICE"
```
If the `enabled` property was set as `false`, microservice will not connect to the hazelcast cluster

### 3. Implementing to Spring Boot Application

#### Enable Hazelcast Helper
- After configuring connection, You have to add `@EnableOasisHazelcast` annotation into your application class. 
When you add this annotation, you can use helper classes.
```Java
@EnableOasisHazelcast
@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

}
```
- Then, Inject `OasisHazelcastMapManager` class into your related component(Service, Controller etc.).

#### Reading Value From Map and Adding a Value to Map

- Example usage of `getValueFromMap`and `addValueToMap`,

```Java
@Service
public class ExampleService {
    
    private final OasisHazelcastMapManager oasisHazelcastMapManager;
    // can be other dependencies
    //...
    @Autowired
    public ExampleService(OasisHazelcastMapManager oasisHazelcastMapManager) {
        this.oasisHazelcastMapManager = oasisHazelcastMapManager;
    }
    //...
    // can be other methods
    //...
    public String getValueFromCache(String key){
        return oasisHazelcastMapManager.getValueFromMap("MY_MAP_NAME", key);
    }
    public void addValueToCache(String key, String value){
        oasisHazelcastMapManager.addValueToMap("MY_MAP_NAME", key, value);
    }
    
}
```
- If it necessary, you can define a custom serializer. If you don't need you can pass this section.
- Example Proto Serializer:
```java
@Configuration
public class CustomSerializerConfig {
    @Bean
    public SerializerConfig createProtocolBufferSerializer() {
        return new SerializerConfig().setImplementation(new HzProtobufferSerializer()).setTypeClass(GeneratedMessageV3.class);
    }
}
  ```


#### Adding New Map or Maps

If you want to create a map with specified properties, you have to define map property for any map.

For example:
```yaml
oasis:
  hazelcast:
    enabled: true
  hz-connection:
    connectionName: "my-cluster"
    connectionPassword: "myPassword"
    connectTimeoutAsMilliSeconds: 5000
    address: 127.0.0.1, 127.0.0.2, 127.0.0.3:5071
    instanceName: "MY_MICROSERVICE
    creatableMaps:
      - name: "exampleMap1"
        maxIdleSeconds: 300
        timeToLiveSeconds: 600
        maxAsyncBackupCounts: 2
      - name: "exampleMap2"
        maxIdleSeconds: 200
        timeToLiveSeconds: 400
        maxAsyncBackupCounts: 1

   ```
- [**More details about map config**](https://docs.hazelcast.com/hazelcast/5.5/data-structures/map-config)



