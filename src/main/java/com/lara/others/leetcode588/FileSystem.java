package com.lara.others.leetcode588;


import org.junit.Test;

import java.util.*;

public class FileSystem {
    // 使用HashMap模拟文件系统结构，键为路径，值为Node对象表示目录或文件
    private Map<String, Node> fileSystem = new HashMap<>();

    class Node {
        String type; // "dir" 或 "file"
        StringBuilder content; // 文件内容，仅当type为"file"时有效
        Map<String, Node> children; // 子节点，仅当type为"dir"时有效

        Node(String type) {
            this.type = type;
            if (type.equals("file")) {
                this.content = new StringBuilder();
            } else {
                this.children = new HashMap<>();
            }
        }
    }

    public FileSystem() {}

    // 创建目录
    public void mkdir(String path) {
        if (!path.startsWith("/") || path.contains("//") || path.endsWith("/")) {
            throw new IllegalArgumentException("Invalid path");
        }
        String[] dirs = path.substring(1).split("/");
        Node current = fileSystem.get("/");
        for (int i = 0; i < dirs.length; i++) {
            String dir = dirs[i];
            if (i == dirs.length - 1) { // 最后一个元素是目录名
                if (!current.children.containsKey(dir)) {
                    current.children.put(dir, new Node("dir"));
                }
            } else {
                if (!current.children.containsKey(dir)) {
                    current.children.put(dir, new Node("dir"));
                    current = current.children.get(dir);
                } else {
                    current = current.children.get(dir);
                }
            }
        }
    }

    // 列出目录下的所有文件和子目录
    public List<String> ls(String path) {
        if (!path.startsWith("/") || !fileSystem.containsKey("/") || !isDirectory(path)) {
            return Collections.emptyList();
        }
        Node node = fileSystem.get(path);
        return new ArrayList<>(node.children.keySet());
    }

    // 向文件添加内容
    public void addContentToFile(String filePath, String content) {
        if (!filePath.startsWith("/") || !isValidPath(filePath) || isDirectory(filePath)) {
            throw new IllegalArgumentException("Invalid file path or it's a directory.");
        }
        Node fileNode = getOrCreateFileNode(filePath);
        fileNode.content.append(content);
    }

    // 读取文件内容
    public String readContentFromFile(String filePath) {
        if (!filePath.startsWith("/") || !isValidPath(filePath) || !isFile(filePath)) {
            throw new NoSuchElementException("File does not exist.");
        }
        Node fileNode = fileSystem.get(filePath);
        return fileNode.content.toString();
    }

    // 检查路径是否为有效的非空目录路径
    boolean isDirectory(String path) {
        return isValidPath(path) && fileSystem.get(path).type.equals("dir");
    }

    // 检查路径是否为有效的文件路径
    private boolean isFile(String path) {
        return isValidPath(path) && fileSystem.get(path).type.equals("file");
    }

    // 检查路径是否有效且存在
    private boolean isValidPath(String path) {
        return fileSystem.containsKey(path) && !path.endsWith("/");
    }

    // 获取或创建目标文件的Node对象
    private Node getOrCreateFileNode(String filePath) {
        String[] dirs = filePath.substring(1).split("/");
        Node current = fileSystem.get("/");
        for (int i = 0; i < dirs.length; i++) {
            String dir = dirs[i];
            if (i == dirs.length - 1) {
                if (!current.children.containsKey(dir)) {
                    current.children.put(dir, new Node("file"));
                }
                return current.children.get(dir);
            } else {
                if (!current.children.containsKey(dir)) {
                    current.children.put(dir, new Node("dir"));
                }
                current = current.children.get(dir);
            }
        }
        return null; // 这里不会执行到，仅为了编译通过
    }

    @Test
    public void test() {
        System.out.println("test");
    }
}
