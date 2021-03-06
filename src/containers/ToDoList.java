package containers;

import java.util.PriorityQueue;

/**
 * Created by IORI on 2014/6/25.
 */
public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {

    static class ToDoItem implements Comparable<ToDoItem> {

        private char primary;
        private int secondary;
        private String item;

        public ToDoItem(String td, char pri, int sec) {
            primary = pri;
            secondary = sec;
            item = td;
        }



        @Override
        public int compareTo(ToDoItem arg) {
            if (primary > arg.primary)
                return 1;
            else if (primary == arg.primary) {
                if(secondary > arg.secondary)
                    return 1;
                else if (secondary == arg.secondary)
                    return 0;
            }
            return -1;
        }

        public String toString() {
            return Character.toString(primary) + secondary + ": " + item;
        }
    }

    public void add(String td, char pri, int sec) {
        super.add(new ToDoItem(td, pri, sec));
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        toDoList.add("empty trash", 'c', 4);
        toDoList.add("feed dog", 'a', 2);
        toDoList.add("feed bird", 'b', 4);
        toDoList.add("water lawn", 'c', 1);
        toDoList.add("feed cat", 'a', 7);
        while (!toDoList.isEmpty()) {
            System.out.println(toDoList.remove());
        }
    }
}
