package flowcontext.core.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapConfig extends ConcurrentHashMap<ConfigProp, Object> {

    public void handleOverriding(Map<ConfigProp, Object> mapConfigOverride) {

        if( mapConfigOverride == null || mapConfigOverride.isEmpty() ) return;
        
        mapConfigOverride.entrySet().stream().forEach(
                entrySet -> {
                    Object value = TypeProp.convertValue(entrySet.getValue(), entrySet.getKey());
                    
                    if (value != null && !value.toString().isEmpty())
                        this.put( entrySet.getKey(), value);
                }
        );
    }

}
