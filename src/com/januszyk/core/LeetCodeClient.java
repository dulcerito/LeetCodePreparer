package com.januszyk.core;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class LeetCodeClient {
    public static String getProblemJSON(String problemName) throws IOException {
        URL url = new URL("https://leetcode.com/graphql");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("content-type", "application/json");
        connection.setRequestProperty("user-agent", null);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write("{\"operationName\":\"questionData\",\"variables\":{\"titleSlug\":\"" + problemName + "\"},\"query\":\"query questionData($titleSlug: String!) {\\n  question(titleSlug: $titleSlug) {\\n    questionId\\n    questionFrontendId\\n    boundTopicId\\n    title\\n    titleSlug\\n    content\\n    translatedTitle\\n    translatedContent\\n    isPaidOnly\\n    difficulty\\n    likes\\n    dislikes\\n    isLiked\\n    similarQuestions\\n    contributors {\\n      username\\n      profileUrl\\n      avatarUrl\\n      __typename\\n    }\\n    langToValidPlayground\\n    topicTags {\\n      name\\n      slug\\n      translatedName\\n      __typename\\n    }\\n    companyTagStats\\n    codeSnippets {\\n      lang\\n      langSlug\\n      code\\n      __typename\\n    }\\n    stats\\n    hints\\n    solution {\\n      id\\n      canSeeDetail\\n      __typename\\n    }\\n    status\\n    sampleTestCase\\n    metaData\\n    judgerAvailable\\n    judgeType\\n    mysqlSchemas\\n    enableRunCode\\n    enableTestMode\\n    envInfo\\n    libraryUrl\\n    __typename\\n  }\\n}\\n\"}");
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return reader.readLine();
    }

    public static String getCode(String problemJSON, CodeLang lang) {
        String codeSnippets = getCodeSnippets(problemJSON);
        String codeSnippet = getCodeSnippet(codeSnippets, lang);
        return getCode(codeSnippet);
    }

    private static String getCodeSnippets(String problemJSON) {
        return CodeExtractor.getExtractedCode(problemJSON, problemJSON.indexOf("\"codeSnippets\":["), '[', ']');
    }

    private static String getCodeSnippet(String codeSnippets, CodeLang lang) {
        return CodeExtractor.getExtractedCode(codeSnippets, codeSnippets.indexOf(lang.getLang()), '{', '}');
    }

    private static String getCode(String codeSnippet) {
        String startCodeString = "\"code\":\"";
        String endCodeString = "\",\"__typename\":\"CodeSnippetNode\"";
        return codeSnippet.substring(codeSnippet.indexOf(startCodeString) + startCodeString.length(), codeSnippet.indexOf(endCodeString));
    }
}