package com.udacity.jwdnd.course1.cloudstorage.model;

import java.io.InputStream;

public class File {
    private Integer fileId;
    private String fieldname;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private InputStream filedata;
    private String dataFile;

    public File(Integer fileId, String fieldname, String contenttype, String filesize, Integer userid, InputStream filedata) {
        this.fileId = fileId;
        this.fieldname = fieldname;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public InputStream getFiledata() {
        return filedata;
    }

    public void setFiledata(InputStream filedata) {
        this.filedata = filedata;
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }
}
