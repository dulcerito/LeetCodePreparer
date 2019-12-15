package com.januszyk.core;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URL;

public class ProblemNameExtractor {
    private static URL getProblemUrlFromClipboard() throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String textFromClipboard = clipboard.getData(DataFlavor.stringFlavor).toString();
        if (!textFromClipboard.matches("^https://leetcode\\.com/problems/.*/$"))
            throw new IOException("Can not resolve text from clipboard. Text should match \"https://leetcode.com/problems/<problem name>/\"");
        return new URL(textFromClipboard);
    }

    public static String gerProblemName() throws IOException, UnsupportedFlavorException {
        String[] pathTree = getProblemUrlFromClipboard().getPath().split("/");
        return pathTree[pathTree.length - 1];
    }
}
