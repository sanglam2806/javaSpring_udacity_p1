package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Service
public class FileService {
    private final FileMapper mapper;

    public FileService(FileMapper mapper) {
        this.mapper = mapper;
    }

    public List<File> getAllFiles() {
        List<File> fileList = mapper.getFiles();

        String data;
        String line;

        for(File file : fileList) {
            BufferedReader reader = null;
            StringBuilder builder = new StringBuilder();
            try {
                reader = new BufferedReader(new InputStreamReader(file.getFiledata()));
                while((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                file.setDataFile(builder.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fileList;
    }

    public boolean isFileExist(String fileName) {
        return mapper.getFile(fileName) != null;
    }

    public void addNewFile(File file) {
        mapper.addNewFile(file);
    }

    public void deleteFile(int fileId) { mapper.deleteFile(fileId); }
}
