package com.example.islam.mitelapp.data.local;

public interface SharedPreference {
    void setValue(String key, String value);

    void setValue(String key, int value);

    void setValue(String key, double value);
    void setValue(String key, long value);
    void setValue(String key, boolean value);
    String getStringValue(String key, String defaultValue);
    int getIntValue(String key, int defaultValue);
    long getLongValue(String key, long defaultValue);
    boolean getBoolanValue(String keyFlag, boolean defaultValue);
    void removeKey(String key);
    void clear();
}
