package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "ourway_sys_files")
public class OurwaySysFiles extends UUidEntity {
    @Column(name = "FILE_CLASS", nullable = true, length = 160)
    private String fileClass;
    @Column(name = "FILE_CLASS_ID", nullable = true, length = 64)
    private String fileClassId;
    @Column(name = "FILE_NAME", nullable = true, length = 160)
    private String fileName;
    @Column(name = "FILE_LABEL", nullable = true, length = 160)
    private String fileLabel;
    @Column(name = "FILE_SIZE", nullable = true, length = 64)
    private String fileSize;
    @Column(name = "FILE_MD5", nullable = true, length = 240)
    private String fileMd5;
    @Column(name = "FILE_PATH", nullable = true, length = 240)
    private String filePath;
    @Column(name = "FILE_EXTION", nullable = true, length = 240)
    private String fileExtion;
    @Column(name = "FILE_RANDON", nullable = true, length = 800)
    private String fileRandon;
    @Column(name = "FILE_DOWN", nullable = true)
    private Integer fileDown;
    @Column(name = "MEMO", nullable = true, length = 800)
    private String memo;
    @Column(name = "FILE_NO", nullable = true, length = 160)
    private String fileNo;
    @Column(name = "FILE_DESC", nullable = true, length = 1600)
    private String fileDesc;
    @Column(name = "FILE_TYPE", nullable = true, length = 64)
    private String fileType;

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileClass() {
        return fileClass;
    }

    public void setFileClass(String fileClass) {
        this.fileClass = fileClass;
    }

    public String getFileClassId() {
        return fileClassId;
    }

    public void setFileClassId(String fileClassId) {
        this.fileClassId = fileClassId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLabel() {
        return fileLabel;
    }

    public void setFileLabel(String fileLabel) {
        this.fileLabel = fileLabel;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtion() {
        return fileExtion;
    }

    public void setFileExtion(String fileExtion) {
        this.fileExtion = fileExtion;
    }

    public String getFileRandon() {
        return fileRandon;
    }

    public void setFileRandon(String fileRandon) {
        this.fileRandon = fileRandon;
    }

    public Integer getFileDown() {
        return fileDown;
    }

    public void setFileDown(Integer fileDown) {
        this.fileDown = fileDown;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
