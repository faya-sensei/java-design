package org.faya.sensei.structural.adapter;

public interface ICompressor {
    /**
     * Compress data into compressed format.
     *
     * @param type The type of compression.
     * @param data The uncompressed byte buffer.
     * @return The compressed byte buffer.
     */
    byte[] compress(String type, byte[] data) throws Exception;

    /**
     * Decompress data into uncompressed format.
     *
     * @param type The type of compression.
     * @param data The compressed byte buffer.
     * @return The uncompressed byte buffer.
     */
    byte[] decompress(String type, byte[] data) throws Exception;
}
