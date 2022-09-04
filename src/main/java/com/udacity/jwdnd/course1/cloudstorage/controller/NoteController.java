package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {
    private final NoteService noteService;
    private final FileService fileService;
    private final UserService userService;

    public NoteController(FileService fileService, NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/addNewNote")
    public String addNewNote(@ModelAttribute Note note, Model model, Authentication authentication) {
        int userId = userService.getUser(authentication.getName()).getUserId();

        note.setUserId(userId);

        noteService.updateNote(note);

        List<Note> listNote = noteService.getAllNotes();
        List<File> listFile = fileService.getAllFiles();

        model.addAttribute("listNotes",listNote);
        model.addAttribute("listFiles", listFile);

        return "home";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam("noteid") int noteid, Model model) {
        noteService.deleteNote(noteid);

        List<Note> listNote = noteService.getAllNotes();
        List<File> listFile = fileService.getAllFiles();

        model.addAttribute("listNotes",listNote);
        model.addAttribute("listFiles", listFile);

        return "home";
    }
}
