package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CredentialController {

    private final CredentialService credentialService;
    private final FileService fileService;
    private final NoteService noteService;

    private final UserService userService;

    public CredentialController(CredentialService credentialService, FileService fileService,
                                NoteService noteService, UserService userService) {
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/addnewCredential")
    public String addNewCredential(@ModelAttribute Credential credential, Model model, Authentication authentication) {
        List<File> listFiles = fileService.getAllFiles();
        model.addAttribute("listFiles",listFiles);
        List<Note> listNotes = noteService.getAllNotes();
        model.addAttribute("listNotes", listNotes);

        credential.setUserid(userService.getUser(authentication.getName()).getUserId());
        credentialService.addNewCredential(credential);

        List<Credential> listCredentials = credentialService.getAllCredential();
        model.addAttribute("listCredentials", listCredentials);

        return "home";
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam("credentialid") int credentialid, Model model) {
        credentialService.deleteCredential(credentialid);

        List<File> listFiles = fileService.getAllFiles();
        model.addAttribute("listFiles",listFiles);
        List<Note> listNotes = noteService.getAllNotes();
        model.addAttribute("listNotes", listNotes);
        List<Credential> listCredentials = credentialService.getAllCredential();
        model.addAttribute("listCredentials", listCredentials);
        return "home";
    }
}
