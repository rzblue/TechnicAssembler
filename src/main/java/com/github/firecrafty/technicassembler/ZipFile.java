package com.github.firecrafty.technicassembler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author firecrafty
 */
public class ZipFile {

    Side side;
    String outputZip = "";
    String parent = "";
    String[] ignoredFiles = {".DS_Store", ".DS_Store?", "Thumbs.db", "._*", ".Spotlight-V100", ".Trashes", "ehthumbs.db", "Thumbs.db"};
    Map<File, String> relativePath = new HashMap<>();

    public ZipFile(Side side) {
        this.side = side;
        if (side == Side.CLIENT) {
            parent = "";
        }
        this.outputZip = TechnicAssembler.getZipName(side) + ".zip";
    }

    public ZipFile generateFileList() {
        generateFiles(new File(TechnicAssembler.packDir + "/config/" + side.toString()), parent + "config");
        generateFiles(new File(TechnicAssembler.packDir + "/config/" + Side.COMMON), parent + "config");
        generateFiles(new File(TechnicAssembler.packDir + "/mods/" + side.toString()), parent + "mods");
        generateFiles(new File(TechnicAssembler.packDir + "/mods/" + Side.COMMON.toString()), parent + "mods");
        generateFiles(new File(TechnicAssembler.packDir + "/extra/" + side.toString()), parent + "");
        generateFiles(new File(TechnicAssembler.packDir + "/extra/" + Side.COMMON.toString()), parent + "");
        generateFiles(new File(TechnicAssembler.packDir + "/bin/" + side.toString()), parent + "bin");

        return this;
    }

    public ZipFile zipIt() {

        try {
            byte[] buffer = new byte[1024];
            
            ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(outputZip));
            for (File file : relativePath.keySet()) {

                if (file.isFile()) {
                    FileInputStream inputStream = new FileInputStream(file);
                    outputStream.putNextEntry(new ZipEntry(relativePath.get(file)));
                    TechnicAssembler.logger.fine("Zipping file: " + relativePath.get(file));
                    
                    int length;
                    while((length = inputStream.read(buffer)) > 0)
                        outputStream.write(buffer, 0 , length);
                    outputStream.closeEntry();
                    inputStream.close();
                } else {
                    addDirToArchive(outputStream, file, relativePath.get(file));
                }
            }
            outputStream.close();
        } catch (Exception e) {
            TechnicAssembler.logger.warning(e.getMessage());
        }
        TechnicAssembler.logger.info("Zip file created: " + outputZip);
        
        return this;
    }

    @SuppressWarnings("ConstantConditions")
    private void generateFiles(File folder, String fileType) {
        if (folder != null && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (Arrays.asList(ignoredFiles).contains(file.getName())){
                    continue;
                }
                String relPath = fileType;
                if (!file.isDirectory()) {
                    relPath += "/" + file.getName();
                } 
                if (relPath.startsWith("/")) {
                    relPath = relPath.substring(1);
                }
                relativePath.put(file, relPath);
                TechnicAssembler.logger.fine("Adding file: " + relPath);
            }
        }
    }
    @SuppressWarnings("ConstantConditions")
    private void addDirToArchive(ZipOutputStream outputStream, File srcFile, String parent) {
        for(File file : srcFile.listFiles()) {
            if(file.isDirectory()) {
                addDirToArchive(outputStream, file, parent.equals("") ? srcFile.getName() : parent + "/" + srcFile.getName());
                continue;
            }
            try {
                byte[] buffer = new byte[1024];
                
                FileInputStream fis = new FileInputStream(file);
                String entry = parent.equals("") ? srcFile.getName() + "/" + file.getName() : parent + "/" + srcFile.getName() + "/" + file.getName();
                outputStream.putNextEntry(new ZipEntry(entry));
                TechnicAssembler.logger.fine("Zipping file: " + entry);
                
                int length;
                
                while((length = fis.read(buffer)) > 0)
                    outputStream.write(buffer, 0 , length);
                outputStream.closeEntry();
                fis.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
