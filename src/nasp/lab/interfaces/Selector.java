package nasp.lab.interfaces;

import java.util.List;

public interface Selector<T> {

    List<T> select (List<T> objects, T object);
}
