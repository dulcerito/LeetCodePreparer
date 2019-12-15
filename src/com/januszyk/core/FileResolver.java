package com.januszyk.core;

import java.io.IOException;
import java.nio.file.Path;

public class FileResolver {
    public static void resolveParent(Path filePath) throws IOException {
        Path parent = filePath.getParent();
        if (!parent.toFile().exists())
            throw new IOException("Parent folder \"" + parent + "\" does not exist");
    }

    public static void resolveSolutionClassName(Path filePath) throws IOException {
        String fileName = filePath.getFileName().toString();
        if (!fileName.equals("Solution.java"))
            throw new IOException("Invalid class name \"" + fileName + "\", expected name is \"Solution.java\"");
    }

    public static void handleExistingSolutionClass(Path filePath) throws IOException {
        if (filePath.toFile().exists()) {
            System.out.print("Do you want to override existing Solution.class? Enter [yes/no]: ");
            FileOverrider.handleInput();
        }
    }

    public static void resolvePackage(Path filePath) throws IOException {
        String path = filePath.toString();
        if (path.matches(".*[/\\\\].*src.*[/\\\\].*") || path.matches(".*[/\\\\]src[/\\\\]main[/\\\\]java[/\\\\].*"))
            return;
        throw new IOException("Solution.java is in invalid path.\n" +
                "If you are using Java project, mark \"src\" folder as Sources Root.\n" +
                "If you are using Maven project, mark \"java\" folder as Sources Root\n" +
                "Finally make sure the path you provided is inside Sources Root, example:\n" +
                ".\\src\\main\\java\\Solution.java\n" +
                ".\\src\\com\\leetcode\\Solution.java");
    }
}
