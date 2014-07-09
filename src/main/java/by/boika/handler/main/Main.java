package by.boika.handler.main;

import by.boika.handler.Functionality.TextFunctionality;
import by.boika.handler.composite.*;
import by.boika.handler.parser.Parser;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        final String TEXT_PATH = "src\\main\\resources\\text\\text.txt";

        Parser parser = new Parser();
        IComponent text = parser.parse(TEXT_PATH);
        TextFunctionality textFunctionality = new TextFunctionality();

        IComponent compositeList = new TextComposite(TypeOfComponent.SENTENCE);
        text.getComponentsByType(compositeList, TypeOfComponent.SENTENCE);

        ArrayList<IComponent> sentenceList = textFunctionality.sortSentenceByWord(compositeList);

        for (IComponent iComponent : sentenceList) {
            LOGGER.info(iComponent.getValue());
        }


//        LOGGER.info(text.getValue());
//        LOGGER.info(compositeList.getValue());
//        sortAndPrintByWords(compositeList);
    }
}
