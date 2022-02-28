package at.commodussolutions.plentyentry.backendConfig.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlentyEntryBackendUtils {
    @Value("${pe.baseHost}")
    private String host;

    public String getHost() {
        return host;
    }
}
