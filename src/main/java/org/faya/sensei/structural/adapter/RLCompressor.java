package org.faya.sensei.structural.adapter;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class RLCompressor implements IExternalCompressor {

    @Override
    public ByteBuffer compress(ByteBuffer data) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte previousByte = data.get();
        int count = 1;

        while (data.hasRemaining()) {
            byte currentByte = data.get();

            if (currentByte == previousByte && count < 255) {
                count++;
            } else {
                byteArrayOutputStream.write(previousByte);
                byteArrayOutputStream.write(count);

                previousByte = currentByte;
                count = 1;
            }
        }

        byteArrayOutputStream.write(previousByte);
        byteArrayOutputStream.write(count);

        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    @Override
    public ByteBuffer decompress(ByteBuffer data) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        while (data.hasRemaining()) {
            final byte value = data.get();
            final int count = data.get() & 0xFF;

            for (int i = 0; i < count; i++) {
                byteArrayOutputStream.write(value);
            }
        }

        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }
}
