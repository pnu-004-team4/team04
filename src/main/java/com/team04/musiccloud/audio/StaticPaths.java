package com.team04.musiccloud.audio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticPaths {
    public static final Path system = Paths.get(System.getProperty("user.dir"));
    public static final Path staticResources = Paths.get(system.toString(),"src", "main", "resources", "static");
    public static final Path storage = Paths.get(staticResources.toString(), "storage");
}
