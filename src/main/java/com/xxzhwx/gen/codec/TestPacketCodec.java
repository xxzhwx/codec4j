package com.xxzhwx.gen.codec;

/**
 * @brief This file is Auto-Generated. please DON'T modify it EVEN if 
 *        you know what you are doing.
 * 
 *        created time: Thu Jun 02 15:33:34 CST 2016
 */
public class TestPacketCodec implements com.xxzhwx.Codec<com.xxzhwx.protocol.TestPacket> {
public void encode(com.xxzhwx.protocol.TestPacket object, org.apache.mina.core.buffer.IoBuffer buffer) {
buffer.putInt(object.id);
}
public com.xxzhwx.protocol.TestPacket decode(org.apache.mina.core.buffer.IoBuffer buffer) {
com.xxzhwx.protocol.TestPacket object = new com.xxzhwx.protocol.TestPacket();
object.id = buffer.getInt();
return object;
}
}