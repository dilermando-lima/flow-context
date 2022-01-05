package flowcontext.core.config;

public enum TypeProp {
    ARRAY_STRING_BY_COMMA,
    INT,
    STRING,
    BOOLEAN;
    

    public static Object convertValue(Object value, ConfigProp configProp){

            if( value == null ) return value;

            if( configProp.getType() == TypeProp.BOOLEAN ){
            
                if( value.toString().trim().equalsIgnoreCase("true") || value.toString().trim().equals("1") ) return true;
                if( value.toString().trim().equalsIgnoreCase("false") || value.toString().trim().equals("0") ) return false;
 
            }else if(configProp.getType() == TypeProp.INT ){
    
                try{
                    return Integer.parseInt(value.toString());
                }catch(NumberFormatException e){
                    // will pass through ending-IllegalArgumentException
                }
                
            }else if(configProp.getType() == TypeProp.ARRAY_STRING_BY_COMMA ){

                return value.toString().trim().split(",");

            }else if(configProp.getType() == TypeProp.STRING ){

                return value.toString().trim();
            }
    
        throw new IllegalArgumentException(
                String.format("config propertie '%s' has been received worng value '%s' needs to be '%s'", configProp.getProp() , value, configProp.getType().name()));

    }
}
