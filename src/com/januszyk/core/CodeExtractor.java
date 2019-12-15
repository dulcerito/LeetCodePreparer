package com.januszyk.core;

class CodeExtractor {
    static String getExtractedCode(String problemJSON, int startIndex, char startChar, char endChar) {
        char[] chars = problemJSON.toCharArray();
        int endCharIndex = -1, startCharIndex = -1, openCharsNum = 0;

        for (int i = startIndex; i < chars.length; i++) {
            if (chars[i] == startChar) {
                if (startCharIndex == -1)
                    startCharIndex = i;
                openCharsNum++;
                continue;
            }
            if (chars[i] == endChar) {
                openCharsNum--;
                if (openCharsNum == 0) {
                    endCharIndex = i;
                    break;
                }
            }
        }

        return problemJSON.substring(startCharIndex, endCharIndex + 1);
    }
}
