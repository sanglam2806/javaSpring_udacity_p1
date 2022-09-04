package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;

    private final CredentialService credentialService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String homePage(Model model) {
        List<File> listFiles = fileService.getAllFiles();
        model.addAttribute("listFiles", listFiles);

        List<Note> listNotes = noteService.getAllNotes();
        model.addAttribute("listNotes", listNotes);

        List<Credential> listCredentials = credentialService.getAllCredential();
        model.addAttribute("listCredentials", listCredentials);

        return "home";
    }
}
