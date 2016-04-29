package com.github.firecrafty.technicassembler;

import com.github.firecrafty.technicassembler.logging.SimpleLogger;

import java.io.*;
import java.util.Properties;

/**
 * @author firecrafty
 */
public class TechnicAssembler {
    /**
     * The directory that the pack file structure is in
     */
    protected static File packDir = getWorkingDirectory();
    /**
     * The logger for the program
     */
    protected static SimpleLogger logger = new SimpleLogger("TechnicAssembler", new File("TechnicAssembler"));
    /**
     * Properties for the modpackName
     */
    private static Properties properties = new Properties();
    /**
     * OutputStream used to output the config file
     */
    private static OutputStream out;
    /**
     * InputStream used to write the config to the file
     */
    private static InputStream in;
    /**
     * Name of the modpackName
     */
    private static String modpackName = "Modpack";
    /**
     * Version of the modpackName
     */
    private static String modpackVersion = "0.0.0";
    /**
     * Build the client?
     */
    private static boolean buildClient;
    /**
     * Build the server?
     */
    private static boolean buildServer;
    /**
     * Use a custom name for the zip file?
     */
    private static boolean useCustomName;
    /**
     * The custom file name to use
     */
    private static String customFileName;
    public static boolean useForge;

    /**
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        logger.startLog();
        handleConfig();
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (arg.startsWith("-n=")) {
                    useCustomName = true;
                    customFileName = arg.split("=")[1];
                }
                if (arg.startsWith("-d=")) {
                    packDir = getPackFolder(arg.split("=")[1]);
                }
                if (arg.startsWith("-o=")) {
                    modpackName = arg.split("=")[1];
                }
                if (arg.startsWith("-v=")) {
                    modpackVersion = arg.split("=")[1];
                }
                if (arg.equalsIgnoreCase("-client")) {
                    buildClient = true;
                }
                if (arg.equalsIgnoreCase("-server")) {
                    buildServer = true;
                }
            }
        }
        zipContents();
        logger.stopLog();
    }

    private static void handleConfig() {
        if (!Config.FILE.exists()) {
            initConfigFile();
        } else {
            loadConfig();
            modpackName = properties.getProperty(Config.MODPACK_NAME, modpackName);
            modpackVersion = properties.getProperty(Config.MODPACK_VERSION, modpackVersion);

        }

    }

    /**
     * @param dir
     * @return The directory that the pack is in
     */
    public static File getPackFolder(String dir) {
        return dir.charAt(1) == ':' ? new File(dir) : new File(getWorkingDirectory() + "/" + dir);
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
        if (buildServer) {
            logger.info("Zipping server...");
            zipFile = new ZipFile(Side.SERVER);
            zipFile.generateFileList();
            zipFile.zipIt();
        }

    }

    private static void initConfigFile() {
        try {
            out = new FileOutputStream(Config.FILE);
            setDefaultProperties();
            properties.store(out, "Modpack config for TechnicAssembler");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void loadConfig() {
        try {
            in = new FileInputStream(Config.FILE);
            properties.load(in);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Returns the current directory.
     *
     * @return The current directory as a file
     */
    public static File getWorkingDirectory() {
        return new File(System.getProperty("user.dir"));
    }

    private static void setDefaultProperties() {
        properties.setProperty("modpack.name", modpackName);
        properties.setProperty("modpack.version", modpackVersion);
        properties.setProperty("forge.automatic", "false");
    }

    /**
     * Gets the name of the file to zip to.
     *
     * @param side The side that the zip is for
     * @return A string with the zip file's name without extension
     */
    public static String getZipName(Side side) {
        if (useCustomName) {
            return customFileName + "-" + side.toString();
        } else {
            return modpackName + "-" + modpackVersion + "-" + side.toString();
        }
    }

    public static void setModpackName(String modpackName) {
        TechnicAssembler.modpackName = modpackName;
    }

    public static void setModpackVersion(String modpackVersion) {
        TechnicAssembler.modpackVersion = modpackVersion;
    }

    public static void setCustomName(String name) {
        useCustomName = true;
        customFileName = name;
    }

    public static void setPackDir(File dir) {
        packDir = dir;
    }
}
