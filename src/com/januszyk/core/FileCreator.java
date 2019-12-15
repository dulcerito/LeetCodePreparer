package com.januszyk.core;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileCreator {
    public static void createFile(Path filePath, String code) throws IOException {
        FileOutputStream writer = new FileOutputStream(filePath.toFile());
        String fileContent = getPackage(filePath) + code.replaceAll("\\s{4}", "\t")
                .replaceAll("\\\\n", "\n");
        writer.write(fileContent.getBytes());
        writer.close();
    }

    private static String getPackage(Path filePath) {
        String packageName = filePath.toString().replaceAll(".*[/\\\\]src[/\\\\]main[/\\\\]java[/\\\\]", "")
                .replaceAll(".*[/\\\\]src[/\\\\]","")
                .replaceAll("[/\\\\]Solution\\.java",";\n\n")
                .replaceAll("[/\\\\]","\\.");
        return packageName.equals("Solution.java") ? "" : "package " + packageName;
    }
}
