package com.xxzhwx;

import com.xxzhwx.gen.CodecRegistry;
import com.xxzhwx.protocol.EchoPacket;
import com.xxzhwx.protocol.TestPacket;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        CodecRegistry.register();

        EchoPacket echo = new EchoPacket(131, "EchoingXxx", new TestPacket(114));

        IoBuffer buf = IoBuffer.allocate(0);
        buf.setAutoExpand(true);
        buf.setAutoShrink(true);
        CodecUtils.encode(echo, buf);

        buf.flip();
        EchoPacket echo1 = CodecUtils.decode(EchoPacket.class, buf);
        System.out.println(echo1.intValue);
        System.out.println(echo1.strValue);
        System.out.println(echo1.objValue.id);

        assertTrue(true);
    }
}
