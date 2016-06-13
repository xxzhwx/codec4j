package com.xxzhwx;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;

public interface Codec<T> {
    void encode(T object, IoBuffer buffer);

    T decode(IoBuffer buffer);

    default void writeString(String data, IoBuffer buffer) {
        try {
            buffer.putPrefixedString(data, Constants.CHARSET_ENCODER);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    default <T1> void writeObject(T1 data, IoBuffer buffer) {
        Codec<T1> codec = CodecRepository.I.getCodec((Class<T1>) data.getClass());
        codec.encode(data, buffer);
    }

    default String readString(IoBuffer buffer) {
        try {
            return buffer.getPrefixedString(Constants.CHARSET_DECODER);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    default <T1> T1 readObject(Class<T1> clazz, IoBuffer buffer) {
        Codec<T1> codec = CodecRepository.I.getCodec(clazz);
        return codec.decode(buffer);
    }
}
