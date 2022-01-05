package flowcontext.core.implementation;

import flowcontext.core.base.FlowBase.ConfigLoadBase;
import flowcontext.core.config.ConfigProp;
import flowcontext.core.config.MapConfig;
import flowcontext.core.config.TypeProp;

public class ConfigLoadDefault implements ConfigLoadBase{


    @Override
    public MapConfig loadConfig() {

        MapConfig mapConfig =  new MapConfig();
        for (ConfigProp configProp : ConfigProp.values()) {
            Object value = System.getenv(configProp.getProp());

            if( value == null ) value = System.getProperty(configProp.getProp());
            
            value = TypeProp.convertValue(value, configProp);

            if( value == null ) value = configProp.getDefaultValue();

            if( value != null && !value.toString().isEmpty() )
                mapConfig.put(configProp, value);
        }
        
        return mapConfig;
    }
    
}
