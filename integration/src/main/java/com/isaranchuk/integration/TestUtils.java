package com.isaranchuk.integration;

import io.restassured.internal.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class TestUtils {

    public static String fileToString(String fileLocation) {
        try {
            return new String(IOUtils.toByteArray(ClassLoader.getSystemResourceAsStream(fileLocation)));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static Properties loadProperties(String filename) {
        try (InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(in);
            return properties;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
