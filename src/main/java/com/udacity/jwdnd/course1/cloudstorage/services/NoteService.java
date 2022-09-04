package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void updateNote(Note note) {
        if (noteMapper.getNote(note.getNoteid()) != null) {
            noteMapper.updateNote(note.getNoteid(), note.getNoteTitle(), note.getNoteDescription());
        } else {
            noteMapper.addNewNote(note);
        }
    }

    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes();
    }

    public void deleteNote(int noteid) {
        noteMapper.deleteNote(noteid);
    }

}
