package org.faya.sensei.structural.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public interface IJDKCompressor {

    GZIPOutputStream gzipCompress(ByteArrayOutputStream buffer) throws Exception;

    GZIPInputStream gzipDecompress(ByteArrayInputStream buffer) throws Exception;

    ZipOutputStream zipCompress(ByteArrayOutputStream buffer) throws Exception;

    ZipInputStream zipDecompress(ByteArrayInputStream buffer) throws Exception;
}
