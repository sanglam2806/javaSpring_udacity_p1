package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Mapper
public interface FileMapper {
    @Select("Select * from FILES")
    List<File> getFiles();

    @Insert("Insert into FILES (fileId, filename, contenttype, filesize, userid, filedata) values (#{fileId},#{fieldname},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addNewFile(File file);

    @Select("SELECT * FROM FILES WHERE filename=#{username}")
    File getFile(String filename);

    @Delete("Delete from FILES where fileId=#{fileId}")
    int deleteFile(int fileId);
}
