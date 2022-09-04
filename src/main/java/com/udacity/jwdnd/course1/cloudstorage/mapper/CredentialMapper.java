package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("Select * from CREDENTIALS")
    List<Credential> getAllCredential();

    @Select("Select * from CREDENTIALS where credentialid = #{credentialid}")
    Credential getCredential(int credentialid);

    @Update("Update CREDENTIALS set `url` = #{url}, `username` = #{username}, `password` = #{password}, `key` = #{key} where credentialid = #{credentialid}")
    int updateCredential(Credential credential);

    @Delete("Delete from CREDENTIALS where credentialid = #{credentialid}")
    int deleteCredential(int credentialid);

    @Insert("INSERT INTO CREDENTIALS (credentialid, `url`, `username`, `key`, `password`, `userid`) values (#{credentialid}, #{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int addNewCredential(Credential credential);

}
