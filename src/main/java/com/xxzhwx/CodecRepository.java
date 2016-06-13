package com.xxzhwx;

import java.util.HashMap;
import java.util.Map;

public final class CodecRepository {
    public static final CodecRepository I = new CodecRepository();
    private CodecRepository() {}

    public <T> void setCodec(Class<T> type, Codec<T> codec) {
        if (codecMap.containsKey(type)) {
            System.out.println("duplicated codec for:" + type.getName());
            return;
        }

        codecMap.put(type, codec);
    }

    @SuppressWarnings("unchecked")
    public <T> Codec<T> getCodec(Class<T> type) {
        return (Codec<T>) codecMap.get(type);
    }

    private Map<Class<?>, Codec<?>> codecMap = new HashMap<>();
}
