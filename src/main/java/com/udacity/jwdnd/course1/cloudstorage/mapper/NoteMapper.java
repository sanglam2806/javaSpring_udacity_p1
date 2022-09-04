package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Select("Select * from NOTES where noteid=#{noteid} ")
    Note getNote(int noteid);

    @Update("Update NOTES set notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteid = #{noteid}")
    int updateNote(int noteid, String noteTitle, String noteDescription);

    @Insert("INSERT INTO NOTES (noteid, notetitle, notedescription, userid) VALUES (#{noteid}, #{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNewNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{noteid}")
    int deleteNote(int noteid);
}
