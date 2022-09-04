package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
public class UploadFileController {

    private final UserService userService;
    private final FileService fileService;

    public UploadFileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Model model,
            Authentication authentication) {
        List<File> listfiles = fileService.getAllFiles();

        if (multipartFile != null) {
            // check file exist!
            if (fileService.isFileExist(multipartFile.getOriginalFilename())) {
                System.out.println("File is exist!");
                model.addAttribute("uploadError", multipartFile.getOriginalFilename() + " is exist!");
                model.addAttribute("listFiles", listfiles);

                return "home";
            } else {
                // get User info
                int userId = userService.getUser(authentication.getName()).getUserId();

                try {
                    File file = new File(0, multipartFile.getOriginalFilename(), multipartFile.getContentType(),
                            String.valueOf(multipartFile.getSize()), userId, multipartFile.getInputStream());

                    fileService.addNewFile(file);
                    listfiles.add(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            model.addAttribute("uploadError", "Not choose the file upload yet!");
        }

        model.addAttribute("listFiles", listfiles);

        return "home";
    }

    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam("fileId") int fileId, Model model) {
        fileService.deleteFile(fileId);
        List<File> listFile = fileService.getAllFiles();
        model.addAttribute("listFiles", listFile);

        return "home";
    }
}
