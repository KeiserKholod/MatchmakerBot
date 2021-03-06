package com.company.bot;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

public class AnswersStorage {

    private static DocumentBuilderFactory docBuilderFactory;
    private static DocumentBuilder docBuilder;
    private static Document answers;

    public static void configureAnswerStorage(AnswerLang lang) throws ParserConfigurationException, IOException, SAXException {
        docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilder = docBuilderFactory.newDocumentBuilder();
        if (lang == AnswerLang.RU)
            answers = docBuilder.parse(new File(configureAnswersPath("ru.xml")));
        if (lang == AnswerLang.EN)
            answers = docBuilder.parse(new File(configureAnswersPath("en.xml")));
        if (lang == AnswerLang.DE)
            answers = docBuilder.parse(new File(configureAnswersPath("de.xml")));
    }

    private static String configureAnswersPath(String fileName) {
        StringJoiner joiner = new StringJoiner(File.separator)
                .add("MatchmakerBot")
                .add("src")
                .add("com")
                .add("company")
                .add("bot")
                .add("answers")
                .add(fileName);
        var pathToDoc = File.separator + joiner.toString();
        String currentDir = System.getProperty("user.dir");
        return currentDir + pathToDoc;
    }

    public static String getHelpMessage() {
        return answers.getElementsByTagName("helpMessage").item(0).getTextContent();
    }


    public static String getDefaultMessage() {
        return answers.getElementsByTagName("defaultMessage").item(0).getTextContent();
    }

    private static String getBotName() {
        return answers.getElementsByTagName("botname").item(0).getTextContent();
    }

    public static String getRegisterNameMessage() {
        return answers.getElementsByTagName("registerNameMessage").item(0).getTextContent();
    }

    public static String getRegAgeMessage() {
        return answers.getElementsByTagName("regAgeMessage").item(0).getTextContent();
    }

    public static String getWrongAgeMessage() {
        return answers.getElementsByTagName("wrongAgeMessage").item(0).getTextContent();
    }

    public static String getRegCityMessage() {
        return answers.getElementsByTagName("regCityMessage").item(0).getTextContent();
    }

    public static String getRegInfoMessage() {
        return answers.getElementsByTagName("regInfoMessage").item(0).getTextContent();
    }

    public static String getStartMessage() {
        return String.format(answers.getElementsByTagName("startMessage").item(0).getTextContent() +
                "\n", getBotName());
    }

    public static String getForcedRegMessage() {
        return answers.getElementsByTagName("forcedRegMessage").item(0).getTextContent() + "\n\n";
    }

    public static String getShowbioErrorMessage() {
        return answers.getElementsByTagName("showbioErrorMessage").item(0).getTextContent()
                + getForcedRegMessage();
    }

    public static String getMatchErrorMessage() {
        return answers.getElementsByTagName("matchErrorMessage").item(0).getTextContent();
    }

    public static String getNoUsernameError() {
        return answers.getElementsByTagName("noUsernameError").item(0).getTextContent();
    }

    public static String getShowMatchesMessage() {
        return answers.getElementsByTagName("showMatchesMessage").item(0).getTextContent();
    }

    public static String getLikeMessage() {
        return answers.getElementsByTagName("likeMessage").item(0).getTextContent();
    }

    public static String getStopMessage() {
        return answers.getElementsByTagName("stopMessage").item(0).getTextContent();
    }

    public static String getNobodyElseMessage() {
        return answers.getElementsByTagName("nobodyElseMessage").item(0).getTextContent();
    }

    public static String getForwardMessage() {
        return "\n" + answers.getElementsByTagName("forwardMessage").item(0).getTextContent() + "\n";
    }

    public static String getStartFindingMessage() {
        return "\n" + answers.getElementsByTagName("startFindingMessage").item(0).getTextContent();
    }

    public static String getLangHelpMessage() {
        return answers.getElementsByTagName("langHelpMessage").item(0).getTextContent();
    }

    public static String getChangeLangMessage() {
        return answers.getElementsByTagName("changeLangMessage").item(0).getTextContent();
    }

    public static String getUserInfo(User user) {
        return String.format(answers.getElementsByTagName("userInfo").item(0).getTextContent(),
                user.getName(), user.getAge(), user.getCity(), user.getInfo());
    }

}