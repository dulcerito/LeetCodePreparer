package com.januszyk.core;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;

public class FileOpener {
    public static void openFile(Path path) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(path.toFile());
    }
}
