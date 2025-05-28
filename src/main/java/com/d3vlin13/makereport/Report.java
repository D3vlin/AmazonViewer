package com.d3vlin13.makereport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Report {
    private String nameFile;
    private String title;
    private String content;
    private String extension;

    public Report() {
    }

    public String getNameFile() {
        return this.nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void makeReport() {
        if (this.getNameFile() != null && this.getTitle() != null && this.getContent() != null) {
            try {
                File file = new File(this.getNameFile() + "." + this.getExtension());
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(this.getContent());
                bw.close();
            } catch (IOException var5) {
                IOException e = var5;
                e.printStackTrace();
            }
        } else {
            System.out.println("Ingresa los datos del archivo");
        }

    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
