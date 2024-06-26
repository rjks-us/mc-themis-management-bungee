package us.rjks.module;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 26.05.2021 / 18:06
 *
 **************************************************************************/

public class BungeeModule {

    private String name, directory;
    private ModuleType type;

    private File dir;

    private File file;
    private Configuration configuration;

    private Plugin plugin;

    private HashMap<String, Object> cache = new HashMap<>();

    public BungeeModule(Plugin plugin, String directory, String name, ModuleType type, boolean autoCreate) {
        this.name = name;
        this.directory = directory;
        this.type = type;
        this.dir = new File(directory);
        this.file = new File(getDirectory(), getName() + "." + getType().toString().toLowerCase());

        this.plugin = plugin;

        try {
            if (autoCreate) {
                if (!dirExists()) createDirectory();
                if (!fileExists()) createEmptyFile();

                loadFile();
            }
        } catch (Exception exception) {
        }

        constructor();
    }

    public void constructor() {

    }

    public void createEmptyFile() throws Exception {
        if (!fileExists()) {
            getFile().createNewFile();
        }
    }

    public void createDirectory() throws Exception {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void loadFromCache(String name) throws Exception {
        if (!fileExists()) {
            Files.copy(plugin.getResourceAsStream(name), new File(getDirectory(), name).toPath());
        }
    }

    public void loadFile() throws Exception {
        configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile());
    }

    public void loadTemplate(String name) throws Exception {
        if (!dirExists()) createDirectory();
        loadFromCache(name);
    }

    public void reloadFile() throws Exception {
        cache.clear();
    }

    public void save() throws Exception {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, getFile());
    }

    public boolean fileExists() {
        return file.exists();
    }

    public boolean dirExists() {
        return dir.exists();
    }

    public File getFile() {
        return file;
    }

    public Configuration getConfig() {
        return configuration;
    }

    public String getName() {
        return name;
    }

    public String getDirectory() {
        return directory;
    }

    public ModuleType getType() {
        return type;
    }

    public HashMap<String, Object> getCache() {
        return cache;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public File getDir() {
        return dir;
    }
}
