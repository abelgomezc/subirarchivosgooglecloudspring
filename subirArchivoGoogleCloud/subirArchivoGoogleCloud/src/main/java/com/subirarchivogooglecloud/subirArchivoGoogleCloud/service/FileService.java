/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.subirarchivogooglecloud.subirArchivoGoogleCloud.service;

import java.io.IOException;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Abel Gomez
 */
public interface FileService {

    List<String> listOfFiles();

    ByteArrayResource downloadFile(String fileName);

    boolean deleteFile(String fileName);

    void uploadFile(MultipartFile file) throws IOException;

}
