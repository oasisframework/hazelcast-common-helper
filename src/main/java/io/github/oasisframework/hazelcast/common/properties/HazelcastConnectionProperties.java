package io.github.oasisframework.hazelcast.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Configuration
@ConfigurationProperties (prefix = "oasis.hz-connection")
public class HazelcastConnectionProperties {
	private static final String DEFAULT_PORT = "5701";
	private static final String PORT_DELIMITER= ":";
	private static final String ADDRESS_SEPARATOR = ",";

	private String address;
	private String connectionName;
	private String connectionPassword;
	private Integer connectTimeoutAsMilliSeconds;
	private List<HazelcastMapProperty> creatableMaps;

	public List<String> getAddressList() {
		if (!StringUtils.hasText(address)) {
			return Collections.emptyList();
		}
		return Arrays.stream(address.split(ADDRESS_SEPARATOR)).filter(StringUtils::hasText).map(String::trim).filter(StringUtils::hasText)
				.map(this::handlePort).collect(Collectors.toList());
	}

	private String handlePort(String address) {
		if (address.contains(PORT_DELIMITER)) {
			return address;
		}

		return address + PORT_DELIMITER + DEFAULT_PORT;
	}

}
