package com.xxzhwx.protocol;

import com.xxzhwx.PacketClass;
import com.xxzhwx.PacketField;

@PacketClass
public class EchoPacket {
    @PacketField(order = 1)
    public int intValue;

    @PacketField(order = 2)
    public String strValue;

    @PacketField(order = 3)
    public TestPacket objValue;

    public EchoPacket() {}

    public EchoPacket(int intValue, String strValue, TestPacket objValue) {
        this.intValue = intValue;
        this.strValue = strValue;
        this.objValue = objValue;
    }
}
