package by.boika.handler.composite;

import java.util.ArrayList;
import java.util.Comparator;

public class LexemeLeaf implements IComponent {
    private String value;
    private TypeOfComponent typeOfComponent;

    public LexemeLeaf(String str, TypeOfComponent typeOfComponent) {
        this.typeOfComponent = typeOfComponent;
        this.value = str;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void add(IComponent part) {
        //add Logic Exception
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(IComponent part) {
        //add Logic Exception
        throw new UnsupportedOperationException();

    }

    @Override
    public void getComponentsByType(IComponent iComponent, TypeOfComponent typeOfComponent) {
        if (typeOfComponent == this.typeOfComponent) {
            iComponent = this;
        }
    }

    @Override
    public IComponent getIComponent(int index) {
        //add Logic Exception
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSize() {
        return value.length();
    }

    @Override
    public ArrayList<IComponent> getSortedList(Comparator<IComponent> comparator) {
        //add Logic Exception
        throw new UnsupportedOperationException();
    }

    public TypeOfComponent getTypeOfComponent() {
        return typeOfComponent;
    }

}

