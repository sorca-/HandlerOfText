package by.boika.handler.Functionality;

import by.boika.handler.composite.IComponent;
import by.boika.handler.composite.TextComposite;
import by.boika.handler.composite.TypeOfComponent;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;

public class TextFunctionality {
    //private static final Logger LOGGER = Logger.getLogger(TextFunctionality.class);

    public TextFunctionality() {
    }

    public ArrayList<IComponent> sortSentenceByWord (IComponent compositeList) {
        int size = compositeList.getSize();
        IComponent wordList;
        for (int i = 0; i < size; ++i) {
            wordList = new TextComposite(TypeOfComponent.WORD);
            compositeList.getIComponent(i).getComponentsByType(wordList, TypeOfComponent.WORD);
        }
        Comparator<IComponent> comparator = (a, b) -> a.getSize() - b.getSize();
        return compositeList.getSortedList(comparator);
    }
}
