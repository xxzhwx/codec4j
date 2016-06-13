package com.xxzhwx;

import org.apache.mina.core.buffer.IoBuffer;

public final class CodecUtils {
    @SuppressWarnings("unchecked")
    public static <T> void encode(T object, IoBuffer buffer) {
        Codec<T> codec = CodecRepository.I.getCodec((Class<T>) object.getClass());
        codec.encode(object, buffer);
    }

    public static <T> T decode(Class<T> clazz, IoBuffer buffer) {
        Codec<T> codec = CodecRepository.I.getCodec(clazz);
        return codec.decode(buffer);
    }
}
