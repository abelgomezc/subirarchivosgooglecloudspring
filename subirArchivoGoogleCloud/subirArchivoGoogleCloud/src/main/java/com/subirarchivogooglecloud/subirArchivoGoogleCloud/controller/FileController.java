/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.subirarchivogooglecloud.subirArchivoGoogleCloud.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;

import com.subirarchivogooglecloud.subirArchivoGoogleCloud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Abel Gomez
 */
@RestController
@RequestMapping("/api/Archivos")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/listarArchivos")
    public ResponseEntity<List<String>> listadeArchivos() {

        List<String> archivos = fileService.listOfFiles();

        return ResponseEntity.ok(archivos);
    }

    @PostMapping("subirArchivo")
    public ResponseEntity<String> subirArchivo(
            @RequestParam MultipartFile archivo) throws IOException {

        fileService.uploadFile(archivo);

        return ResponseEntity.ok("El archivo ha subido correctamente");
    }

    @DeleteMapping("borrarArchivo/{nombreArchivo}")
    public ResponseEntity<String> borrarArchivo(@PathVariable String nombreArchivo) {
        fileService.deleteFile(nombreArchivo);
        return ResponseEntity.ok("Archivo eliminado con éxito");
    }


    @GetMapping("descargarArchivo/{nombreArchivo}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombreArchivo) {
        ByteArrayResource resource = fileService.downloadFile(nombreArchivo);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + nombreArchivo + "\"");

        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_OCTET_STREAM).
                headers(headers).body(resource);
    }

}
