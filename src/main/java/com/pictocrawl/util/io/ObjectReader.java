package com.pictocrawl.util.io;

public interface ObjectReader {

    <T> T readObjectFromFile(Class<T> type, String filename, String directory);

}