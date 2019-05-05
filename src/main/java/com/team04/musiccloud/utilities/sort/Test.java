package com.team04.musiccloud.utilities.sort;

// binarySearch로 특정값을 찾아준다.

// <>안에 든 것을 정렬하는 클래스

public class Test{


    //main method
    public static void main(String[] args) {
        ListSort listsort = new ListSort();

        listsort.Sort(1);
        System.out.println("========================Sort Title DSC========================");
        listsort.Sort(2);
        System.out.println("========================Sort Artist ASC========================");
        listsort.Sort(3);
        System.out.println("========================Sort Artist DSC========================");
        listsort.Sort(4);


    }

}
