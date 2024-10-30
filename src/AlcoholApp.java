import classes.ArrayStringList;

import java.util.Arrays;

public class AlcoholApp  {

    public static void main(String[] args) {


        ArrayStringList array = new ArrayStringList(10);
        array.add("1");
        array.add("3");
        array.add(1,"2");
        array.add(0,"0");

        array.remove("3");

        System.out.println(Arrays.toString(array.toArray()));
        System.out.println(array.size());

        System.out.println(array.add("4"));
    }
}
