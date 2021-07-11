import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

public class UsingComparators {
    public static void sort(Object[] array, Comparator comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 && less(comparator, array[j], array[j-1]); j--) {
                exch(array, j, j-1);
            }
        }
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v,w) < 0);
    }

    private static void exch(Object[] array, int i, int j) {
        Object swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    public static void main(String[] args) {
        Student[] myClass = new Student[4];
        String[] names = {"Yannic", "Alex", "Todd", "Becky"};
        Character[] grades = {'A', 'F', 'D', 'A'};
        for (int i = 0; i < myClass.length; i++) {
            Student newStudent = new Student();
            newStudent.grade = grades[i];
            newStudent.idx = i;
            newStudent.name = names[i];
            myClass[i] = newStudent;
        }
        StdOut.println("Sorted by name:");
        sort(myClass, Student.BY_NAME);
        for (Student student : myClass) {
            StdOut.println(student.name);          
        }
        StdOut.println("Sorted by grade:");
        sort(myClass, Student.BY_GRADE);
        for (Student student : myClass) {
            StdOut.println(student.name);          
        }
        StdOut.println("Sorted by index:");
        sort(myClass, Student.BY_IDX);
        for (Student student : myClass) {
            StdOut.println(student.name);          
        }
    }
}
