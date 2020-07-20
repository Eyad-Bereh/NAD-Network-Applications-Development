package app;

import java.util.HashMap;

public class Message {
    private HashMap<String, Object> properties;
    private int type;
    public static final int NORMAL_MESSAGE = 0;
    public static final int USERS_COUNT_MESSAGE = 1;
    
    public Message() {
        properties = new HashMap<>();
        type = -1;
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public void setProperty(String key, Object value) {
        if (properties.containsKey(key)) {
            properties.replace(key, value);
        }
        else {
            properties.put(key, value);
        }
    }

    public void deleteProperty(String key) {
        properties.remove(key);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
