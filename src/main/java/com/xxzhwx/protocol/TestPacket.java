package com.xxzhwx.protocol;

import com.xxzhwx.PacketClass;
import com.xxzhwx.PacketField;

@PacketClass
public class TestPacket {
    @PacketField(order = 1)
    public Integer id;

    public TestPacket() {}

    public TestPacket(int id) {
        this.id = id;
    }
}
