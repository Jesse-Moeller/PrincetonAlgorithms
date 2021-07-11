import java.util.Comparator;
public class Student {
    public static final Comparator<Student> BY_IDX = new ByIdx();
    public static final Comparator<Student> BY_GRADE = new ByGrade();
    public static final Comparator<Student> BY_NAME = new ByName();
    Integer idx;
    Character grade;
    String name;

    private static class ByIdx implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.idx.compareTo(w.idx);
        }
    }

    private static class ByGrade implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.grade.compareTo(w.grade);
        }
    }

    private static class ByName implements Comparator<Student> {
        public int compare(Student v, Student w) {
            return v.name.compareTo(w.name);
        }
    }
}
