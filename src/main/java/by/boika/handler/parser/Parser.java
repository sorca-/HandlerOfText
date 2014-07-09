package by.boika.handler.parser;

import by.boika.handler.composite.*;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

//    private static final Logger LOGGER = Logger.getLogger(Parser.class);
    private final String REGEX_LISTING = "(?s)(#{5}.*?#{5}\\n?)";
    private final String REGEX_PARAGRAPH = "(?<=(\\r\\n|\\r|\\n))";
    private final String REGEX_SENTENCE = "([^\\.]+[\\.:][\\p{Blank}\\n])";
    private final String REGEX_WORD_AND_PUNCTUATION = "(\\b[a-zA-Z]+\\b)(\\)?\\p{Punct}?\\p{Blank}?\\n?\\(?@?\\-?)";
    private final int ID_GROUP_WORD = 1;
    private final int ID_GROUP_PUNCTUATION = 2;

    public Parser() {

    }

    public IComponent parse(String path) {
        TextReader textReader = new TextReader();
        String readText = textReader.readText(path);
//        LOGGER.info(readText);
        IComponent wholeTextComposite = new TextComposite(TypeOfComponent.TEXT);
        parseToParagraphAndListing(wholeTextComposite, readText);
        return wholeTextComposite;
    }

    private void parseToParagraphAndListing(IComponent wholeTextComposite, String readText) {
        Pattern pattern = Pattern.compile(REGEX_LISTING);
        Matcher matcher = pattern.matcher(readText);
        int indexTextStart = 0;
        int indexTextEnd;
        String text;
        String listing;
        IComponent listingLeaf;
        while (matcher.find()) {
            indexTextEnd = matcher.start();
            if (indexTextEnd !=0) {
                text = readText.substring(indexTextStart, indexTextEnd);
//                LOGGER.info(text);
                wholeTextComposite.add(parseToParagraph(text));
            }
            listing = matcher.group();
//            LOGGER.info(listing);
            listingLeaf = new LexemeLeaf(listing, TypeOfComponent.LISTING);
            wholeTextComposite.add(listingLeaf);
            indexTextStart = matcher.end();
        }
    }

    private IComponent parseToParagraph(String textWithoutListing) {
        IComponent paragraphComposite = new TextComposite(TypeOfComponent.PARAGRAPH);
        String[] paragraphs = Pattern.compile(REGEX_PARAGRAPH, Pattern.MULTILINE).split(textWithoutListing);
        for (String paragraph : paragraphs) {
//            LOGGER.info(paragraph);
            paragraphComposite.add(parseToSentence(paragraph));
        }
//        LOGGER.info(paragraphComposite.getValue());
        return paragraphComposite;
    }

    private IComponent parseToSentence(String paragraph) {
        IComponent sentenceComposite = new TextComposite(TypeOfComponent.SENTENCE);
        Pattern pattern = Pattern.compile(REGEX_SENTENCE);
        Matcher matcher = pattern.matcher(paragraph);
        while (matcher.find()) {
//            LOGGER.info(matcher.group());
            parseToWordAndPunctuations(sentenceComposite, matcher.group());
        }
        return sentenceComposite;
    }

    private void parseToWordAndPunctuations(IComponent sentenceComposite, String sentence) {
        Pattern pattern = Pattern.compile(REGEX_WORD_AND_PUNCTUATION);
        Matcher matcher = pattern.matcher(sentence);
        while (matcher.find()) {
            if (!matcher.group(ID_GROUP_WORD).isEmpty()) {
                sentenceComposite.add(new LexemeLeaf(matcher.group(ID_GROUP_WORD), TypeOfComponent.WORD));
            }
            if (!matcher.group(ID_GROUP_PUNCTUATION).isEmpty()) {
                sentenceComposite.add(new LexemeLeaf(matcher.group(ID_GROUP_PUNCTUATION), TypeOfComponent.PUNCTUATION));
            }
        }
    }
}
