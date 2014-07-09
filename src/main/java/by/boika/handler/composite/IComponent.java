package by.boika.handler.composite;

import java.util.ArrayList;
import java.util.Comparator;

public interface IComponent {
    public String getValue();
    public void add (IComponent component);
    public void remove (IComponent component);
    public void getComponentsByType(IComponent dstComponent, TypeOfComponent typeOfComponent);
    public IComponent getIComponent(int index);
    public int getSize ();
    public TypeOfComponent getTypeOfComponent ();
    public ArrayList<IComponent> getSortedList(Comparator<IComponent> comparator);
}
