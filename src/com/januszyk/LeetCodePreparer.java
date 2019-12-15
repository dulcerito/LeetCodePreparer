package com.januszyk;

import com.januszyk.core.*;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeetCodePreparer {

    /**
     * Provide full path to Solution class
     * May be relative or absolute
     * <p>
     * examples :
     * .\src\Solution.java
     * C:\Cloud\Project_1\src\Solution.java
     */
    public LeetCodePreparer(String pathToSolutionClass) {
        try {
            Path filePath = Paths.get(pathToSolutionClass);

            FileResolver.resolveParent(filePath);
            FileResolver.resolveSolutionClassName(filePath);
            FileResolver.resolvePackage(filePath);
            FileResolver.handleExistingSolutionClass(filePath);

            String problemName = ProblemNameExtractor.gerProblemName();
            String problemJSON = LeetCodeClient.getProblemJSON(problemName);
            String code = LeetCodeClient.getCode(problemJSON, CodeLang.JAVA);

            FileCreator.createFile(filePath, code);
            FileOpener.openFile(filePath);
        } catch (IOException | UnsupportedFlavorException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}