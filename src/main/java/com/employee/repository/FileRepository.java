package com.employee.repository;

import com.employee.exception.RepositoryAccessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FileRepository {

    public void saveData(String path, String data) {
        try {
            String content = data;
            File file = new File(path);
            if (!file.createNewFile()) {
                content = "\n" + data;
            }
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RepositoryAccessException(e);
        }
    }

    public void overwriteData(String path, String data) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RepositoryAccessException(e);
        }
    }

    public String getContent(String path) {
        Path filePath = Paths.get(path);
        try {
            if (Files.notExists(filePath)) {
                return null;
            }
            return new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            throw new RepositoryAccessException(e);
        }
    }

    public Stream<String> getLines(String path) {
        Path filePath = Paths.get(path);
        try {
            if (Files.notExists(filePath)) {
                return null;
            }
            return Files.lines(filePath);
        } catch (IOException e) {
            throw new RepositoryAccessException(e);
        }
    }
}
