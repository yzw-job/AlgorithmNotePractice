package CodePractice;

import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 *  测试comparator 比较方法
 */
public class TestCompare {

    public static void main(String[] args) throws InterruptedException {
        Student s1=new Student(01,"alice",new Date());
        Student s2=new Student(21,"jim",new Date());
        Thread.currentThread().sleep(1000);
        Student s5=new Student(25,"john",new Date());

        Student s6=new Student(32,"smith",new Date());
        Thread.currentThread().sleep(1000);
        Student s3=new Student(58,"alex",new Date());
        Thread.currentThread().sleep(1000);
        Student s4=new Student(10,"bob",new Date());

        // sort
        Student[] arr={s1,s2,s3,s5,s4,s6};
        TestSortMerge<Student> tsort=new TestSortMerge();
        tsort.sort(arr,new Student.ComNam());
        for (int i=0;i<6;i++)arr[i].show();

    }
}

class Student{
    private int number;
    private String name;
    private Date time;

    Student(int number ,String name,Date time){
        this.number=number;
        this.name=name;
        this.time=time;
    }
    void show(){
        System.out.print("  "+this.name);
        System.out.print("  "+this.number);
        System.out.print("  "+this.time);
        System.out.println();
    }
    public static class CompTime implements Comparator<Student>{
        @Override
        public int compare(Student s1, Student s2) {
            return s1.time.compareTo(s2.time) ;
        }
    }

    public static class ComNum implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return ((Integer)o1.number).compareTo((Integer)o2.number);
        }
    }

    public static class ComNam implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareTo(o2.name);
        }
    }
}
