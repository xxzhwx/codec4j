package com.xxzhwx.gen;

/**
 * @brief This file is Auto-Generated. please DON'T modify it EVEN if 
 *        you know what you are doing.
 * 
 *        created time: Thu Jun 02 15:33:34 CST 2016
 */
public final class CodecRegistry {
public static void register(){
com.xxzhwx.CodecRepository.I.setCodec(com.xxzhwx.protocol.EchoPacket.class, new com.xxzhwx.gen.codec.EchoPacketCodec());
com.xxzhwx.CodecRepository.I.setCodec(com.xxzhwx.protocol.TestPacket.class, new com.xxzhwx.gen.codec.TestPacketCodec());
}
}