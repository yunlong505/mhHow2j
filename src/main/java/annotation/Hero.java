package annotation;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    String name;

    @Deprecated
    public void hackMap(){

    }

    public static void main(String[] args) {

        new Hero().hackMap();
        @SuppressWarnings({ "rawtypes", "unused" })
        List heros = new ArrayList();
    }

}