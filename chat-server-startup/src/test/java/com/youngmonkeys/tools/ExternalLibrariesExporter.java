package com.youngmonkeys.tools;

import static com.tvd12.ezyfox.io.EzyStrings.isEmpty;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.tvd12.ezyfox.collect.Sets;

public final class ExternalLibrariesExporter {

    private static final String EXTENSION_JAR = ".jar";
    private static final String FOLDER_COMMON = "common";
    private static final Set<String> EXCLUSIVE_LIBRARY_NAMES =
        Sets.newHashSet(
            "chat-server-entry",
            "chat-server-plugin",
            "chat-server-api",
            "chat-server-startup",
            "ezyfox-server-embedded"
        );

    public static void main(String[] args) throws Exception {
        final String ezyfoxServerHome = getEzyFoxServerHome(args);
        if (isEmpty(ezyfoxServerHome)) {
            System.err.println("you must provide ezyfox server folder path to export");
            return;
        }
        final Path commonFolderPath = Paths.get(ezyfoxServerHome, FOLDER_COMMON);
        if (!Files.exists(commonFolderPath)) {
            System.err.println("invalid ezyfox server folder path: " + ezyfoxServerHome);
        }

        final Set<String> exportedLibraries = listExportedLibraries();
        if (exportedLibraries.isEmpty()) {
            System.err.println(
                "you must run: "
                    + "'mvn clean install -Denv.EZYFOX_SERVER_HOME=deploy -Pezyfox-deploy' "
                    + "on chat-server-startup module to export all libraries first"
            );
            return;
        }
        exportLibrariesToEzyFox(
            ezyfoxServerHome,
            exportedLibraries,
            listCurrentLibraries(ezyfoxServerHome)
        );
    }

    private static String getEzyFoxServerHome(String[] args) {
        String ezyfoxServerHome = null;
        if (args.length > 0) {
            ezyfoxServerHome = args[0];
        }
        if (isEmpty(ezyfoxServerHome)) {
            ezyfoxServerHome = System.getProperty("EZYFOX_SERVER_HOME");
        }
        if (isEmpty(ezyfoxServerHome)) {
            ezyfoxServerHome = System.getenv("EZYFOX_SERVER_HOME");
        }
        if (isEmpty(ezyfoxServerHome)) {
            System.out.print("please input ezyfox server home path: ");
            ezyfoxServerHome = new Scanner(System.in).nextLine().trim();
        } else {
            System.out.println("EzyFox Server Home: " + ezyfoxServerHome);
        }
        return ezyfoxServerHome;
    }

    private static Set<String> listExportedLibraries() throws Exception {
        final Path deployLibFolderPath = getDeployLibFolderPath();
        if (!Files.exists(deployLibFolderPath)) {
            return Collections.emptySet();
        }
        return listLibraries(deployLibFolderPath);
    }

    private static Path getDeployLibFolderPath() {
        Path deployLibFolderPath = Paths.get("deploy/lib");
        if (!Files.exists(deployLibFolderPath)) {
            deployLibFolderPath = Paths.get(
                "chat-server-startup/deploy/lib"
            );
        }
        return deployLibFolderPath;
    }

    private static Set<String> listCurrentLibraries(
        String ezyfoxServerHome
    ) throws Exception {
        return listLibraries(Paths.get(ezyfoxServerHome));
    }

    private static Set<String> listLibraries(
        Path path
    ) throws Exception {
        return Files.walk(path)
            .filter(p -> p.toString().endsWith(EXTENSION_JAR))
            .map(p -> p.getFileName().toString())
            .collect(Collectors.toSet());
    }

    private static void exportLibrariesToEzyFox(
        String ezyfoxServerHome,
        Set<String> exportedLibraries,
        Set<String> currentLibraries
    ) throws Exception {
        final Path deployLibFolderPath = getDeployLibFolderPath();
        final Set<String> needExportLibraries = exportedLibraries
            .stream()
            .filter(it -> !containsLibrary(currentLibraries, it))
            .collect(Collectors.toSet());
        for (String libName : needExportLibraries) {
            final Path toPath = Paths.get(ezyfoxServerHome, FOLDER_COMMON, libName);
            Files.copy(deployLibFolderPath.resolve(libName), toPath);
            System.out.println("exported to: " + toPath);
        }
    }

    private static boolean containsLibrary(
        Set<String> libraries,
        String library
    ) {
        if (libraries.contains(library)) {
            return true;
        }
        final String libraryName = library.substring(
            0,
            library.lastIndexOf('-')
        );
        if (EXCLUSIVE_LIBRARY_NAMES.contains(libraryName)) {
            return true;
        }
        for (String lib : libraries) {
            if (lib.startsWith(libraryName)) {
                return true;
            }
        }
        return false;
    }
}
