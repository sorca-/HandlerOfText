package by.boika.handler.composite;

import java.util.ArrayList;
import java.util.Comparator;

public class TextComposite implements IComponent {
    private TypeOfComponent typeOfComponent;
    private ArrayList<IComponent> partList = new ArrayList<>();

    public TextComposite(TypeOfComponent typeOfComponent) {
        this.typeOfComponent = typeOfComponent;
    }

    @Override
    public String getValue() {
        StringBuilder sb = new StringBuilder();
        for (IComponent iComponent : partList) {
            sb.append(iComponent.getValue());
        }
        return sb.toString();
    }

    @Override
    public void add(IComponent part) {
        partList.add(part);

    }

    @Override
    public void remove(IComponent part) {
        partList.remove(part);
    }

    @Override
    public void getComponentsByType(IComponent iComponent, TypeOfComponent typeOfComponent) {
        for (IComponent srcComponent : partList) {
            if (srcComponent.getTypeOfComponent() == typeOfComponent) {
                iComponent.add(srcComponent);
            } else {
                srcComponent.getComponentsByType(iComponent, typeOfComponent);
            }
        }
    }

    @Override
    public IComponent getIComponent(int index) {
        return partList.get(index);
    }

    @Override
    public int getSize() {
        return partList.size();
    }

    @Override
    public TypeOfComponent getTypeOfComponent() {
        return typeOfComponent;
    }

    @Override
    public ArrayList<IComponent> getSortedList(Comparator<IComponent> comparator) {
        ArrayList<IComponent> sortedList = partList;
        sortedList.sort(comparator);
        return sortedList;
    }
}
