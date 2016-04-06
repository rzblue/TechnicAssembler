package com.github.firecrafty.technicassembler;

import com.github.firecrafty.technicassembler.logging.SimpleLogger;
import java.io.File;

/**
 *
 * @author firecrafty
 */
public class TechnicAssembler {

    static String output = "Modpack";
    static String version = "0.0.0";
    static File packDir = getWorkingDirectory();

    static boolean buildClient = false;
    static boolean buildServer = false;
    static boolean useCustomName = false;
    static String customFileName;
    static SimpleLogger logger = new SimpleLogger("TechnicAssembler", new File("TechnicAssembler.log"));
    /**
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        logger.startLog();
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (arg.startsWith("-n")) {
                    useCustomName = true;
                    customFileName = arg.split("=")[1];
                }
                if (arg.startsWith("-d"))
                    packDir = getPackFolder(arg.split("=")[1]);
                if (arg.startsWith("-o="))
                    output = arg.split("=")[1];
                if (arg.startsWith("-v="))
                    version = arg.split("=")[1];
                if (arg.equalsIgnoreCase("-client"))
                    buildClient = true;
                if (arg.equalsIgnoreCase("-server"))
                    buildServer = true;
            }
        }
        zipContents();
        logger.stopLog();
    }
    /**
     * Zips up the contents of the pack for each side based on arguments.
     */
    public static void zipContents() {
        if (packDir == null) {
            logger.severe("The pack directory is null. Aborting.");
            System.exit(1);
        }
        if (packDir.isFile()) {
            logger.severe("The pack directory cannot be a file. Aborting.");
            System.exit(1);
        }
        ZipFile zipFile;
        
        if (buildClient) {
            logger.info("Zipping client...");
            zipFile = new ZipFile(Side.CLIENT);
            zipFile.generateFileList();
            zipFile.zipIt();
        }
        if(buildServer) {
            logger.info("Zipping server...");
            zipFile = new ZipFile(Side.SERVER);
            zipFile.generateFileList();
            zipFile.zipIt();
        }
        
    }
    /**
     * 
     * @param dir
     * @return The directory that the pack is in
     */
    public static File getPackFolder(String dir) {
        return dir.charAt(1) == ':' ? new File(dir) : new File(getWorkingDirectory() + "/" + dir);
    }

    public static File getWorkingDirectory() {
        return new File(System.getProperty("user.dir"));
    }
    public static String getZipName(Side side) {
        if(useCustomName) {
            return customFileName + "-" + side.toString();
        } else {
            return output + "-" + version + "-" + side.toString();
        }
    }

}
