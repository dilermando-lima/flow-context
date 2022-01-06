package flowcontext.core.config;

public enum ConfigProp {
    
    STEP_IGNORE_LIST("fcontext.step.ignore-list",TypeProp.ARRAY_STRING_BY_COMMA,null),

    STEP_IGNORE_LIST_2("fcontext.step.ignore-list",TypeProp.ARRAY_STRING_BY_COMMA,null),
    STEP_IGNORE_LIST_3("fcontext.step.ignore-list",TypeProp.ARRAY_STRING_BY_COMMA,null),
    STEP_IGNORE_LIST_4("fcontext.step.ignore-list",TypeProp.ARRAY_STRING_BY_COMMA,null),
    STEP_IGNORE_LIST_5("fcontext.step.ignore-list",TypeProp.ARRAY_STRING_BY_COMMA,null)

    ;

    private final String prop;
    private final TypeProp type;
    private final Object defaultValue;

    private ConfigProp(String prop, TypeProp type, Object defaultValue) {
        this.prop = prop;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getProp() {
        return prop;
    }

    public TypeProp getType() {
        return type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

}
