package com.lara.others.leetcode588;



import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThrows;

public class FileSystemTest {

    private FileSystem fs = new FileSystem();

    @Test
    public void testMkdir() {
        fs.mkdir("/a");
        assertTrue(fs.isDirectory("/a"));

        fs.mkdir("/a/b");
        assertTrue(fs.isDirectory("/a"));
        assertTrue(fs.isDirectory("/a/b"));

        assertThrows(IllegalArgumentException.class, () -> fs.mkdir("/invalid/path//"));
    }

    @Test
    public void testLs() {
        fs.mkdir("/a");
        fs.mkdir("/a/b");
        fs.addContentToFile("/a/c.txt", "content");

        List<String> result = fs.ls("/");
        assertEquals(Arrays.asList("a"), result);

        result = fs.ls("/a");
        assertEquals(Arrays.asList("b", "c.txt"), result);
    }

    @Test
    public void testAddContentToFile() {
        String prefix = "/Users/haibingzhang/Desktop/tmp";
        fs.mkdir(prefix + "/dir");
        fs.addContentToFile(prefix + "/dir/file.txt", "Hello");
        fs.addContentToFile(prefix + "/dir/file.txt", ", World!");

        String content = fs.readContentFromFile(prefix + "/dir/file.txt");
        assertEquals("Hello, World!", content);
    }

    @Test
    public void testReadContentFromFile() {
        fs.mkdir("/test");
        fs.addContentToFile("/test/file.txt", "This is a test file.");

        String content = fs.readContentFromFile("/test/file.txt");
        assertEquals("This is a test file.", content);

        assertThrows(NoSuchElementException.class, () -> fs.readContentFromFile("/nonexistent/file.txt"));
    }
}
