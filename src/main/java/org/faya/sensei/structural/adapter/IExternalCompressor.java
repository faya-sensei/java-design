package org.faya.sensei.structural.adapter;

import java.nio.ByteBuffer;

public interface IExternalCompressor {

    ByteBuffer compress(ByteBuffer data) throws Exception;

    ByteBuffer decompress(ByteBuffer data) throws Exception;
}
