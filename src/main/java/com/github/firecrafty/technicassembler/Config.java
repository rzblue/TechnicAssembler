/*
 * File name: Config.java
 * com.github.firecrafty.technicassembler.Config
 *
 * Copyright (C) 2016 firecrafty
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.firecrafty.technicassembler;



import java.io.File;
import java.util.Properties;

/**
 * @author firecrafty
 */
public class Config {

    //Config file info
    public final static File CONFIG_FILE = new File(Config.CONFIG_FILENAME);
    private final static String CONFIG_FILENAME = "assemble.config";
    //The default properties
    public static Properties DEFAULT_PROPERTIES = new Properties();
    //Keys
    public final static String MODPACK_NAME_KEY =       "modpack.name";
    public final static String MODPACK_VERSION_KEY =    "modpack.version";
    public final static String USE_FORGE_DOWNLOAD_KEY = "forge.automatic";
    public final static String FORGE_VERSION_KEY =      "forge.version";
    public final static String USE_CUSTOM_NAME_KEY =    "modpack.useCustomName";
    public final static String CUSTOM_NAME_KEY =        "modpack.customName";

    //Property data
    public final static String MODPACK_NAME =           "Modpack";
    public final static String MODPACK_VERSION =        "0.0.0";
    public final static String USE_FORGE_DOWNLOAD =     "false";
    public final static String FORGE_VERSION =          "1.8.9-11.15.1.1855";
    public final static String USE_CUSTOM_NAME =        "false";
    public final static String CUSTOM_NAME =            "";

    public static void setDefaultProperties() {
        DEFAULT_PROPERTIES.setProperty(Config.MODPACK_NAME_KEY, Config.MODPACK_NAME);
        DEFAULT_PROPERTIES.setProperty(Config.MODPACK_VERSION_KEY, Config.MODPACK_VERSION);
        DEFAULT_PROPERTIES.setProperty(Config.USE_FORGE_DOWNLOAD_KEY, Config.USE_FORGE_DOWNLOAD);
        DEFAULT_PROPERTIES.setProperty(Config.FORGE_VERSION_KEY, Config.FORGE_VERSION);
        DEFAULT_PROPERTIES.setProperty(Config.USE_CUSTOM_NAME_KEY, Config.USE_CUSTOM_NAME);
        DEFAULT_PROPERTIES.setProperty(Config.CUSTOM_NAME_KEY, Config.CUSTOM_NAME);
    }



}
