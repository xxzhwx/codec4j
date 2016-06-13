package com.xxzhwx.gen.codec;

/**
 * @brief This file is Auto-Generated. please DON'T modify it EVEN if 
 *        you know what you are doing.
 * 
 *        created time: Thu Jun 02 15:33:34 CST 2016
 */
public class EchoPacketCodec implements com.xxzhwx.Codec<com.xxzhwx.protocol.EchoPacket> {
public void encode(com.xxzhwx.protocol.EchoPacket object, org.apache.mina.core.buffer.IoBuffer buffer) {
buffer.putInt(object.intValue);
this.writeString(object.strValue, buffer);
this.writeObject(object.objValue, buffer);
}
public com.xxzhwx.protocol.EchoPacket decode(org.apache.mina.core.buffer.IoBuffer buffer) {
com.xxzhwx.protocol.EchoPacket object = new com.xxzhwx.protocol.EchoPacket();
object.intValue = buffer.getInt();
object.strValue = this.readString(buffer);
object.objValue = this.readObject(com.xxzhwx.protocol.TestPacket.class, buffer);
return object;
}
}