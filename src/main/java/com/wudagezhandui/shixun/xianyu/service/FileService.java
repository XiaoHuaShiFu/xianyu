package com.wudagezhandui.shixun.xianyu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {

    boolean save(String directoryPath, File file);

    boolean save(MultipartFile file, String fileName, String directoryPath);

    String save(MultipartFile file, String directoryPath);

    String saveAndGetUrl(MultipartFile file, String directoryPath);

    void delete(String fileName, String directoryPath);

    void delete(String fileUrl);

    String updateFile(MultipartFile file, String oldUrl, String directoryPath);
}
