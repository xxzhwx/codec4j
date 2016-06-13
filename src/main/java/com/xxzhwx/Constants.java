package com.xxzhwx;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public interface Constants {
    Charset CHARSET = Charset.forName("utf-8");
    CharsetEncoder CHARSET_ENCODER = CHARSET.newEncoder();
    CharsetDecoder CHARSET_DECODER = CHARSET.newDecoder();
}
