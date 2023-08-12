class StringIntegerPair {
    private String stringValue;
    private Integer intValue;

    public StringIntegerPair(String stringValue, int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    @Override
    public String toString() {
        return "String: " + stringValue + ", Integer: " + intValue;
    }
}