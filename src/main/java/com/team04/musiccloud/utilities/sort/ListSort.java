package com.team04.musiccloud.utilities.sort;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSort {

    public ListSort() {
    }

    //전달요소: 배열
    public ArrayList<Music> Sort(int SelSort){

        //Music List를 받습니다.
        Music m = new Music();
        ArrayList<Music> Mlist = m.Musiclist();



        // 오름차순 정렬
        Collections.sort(Mlist, new Comparator<Music>(){



            //1, 2: title 오름차순, 내림차순
            //3, 4: artist 오름차순, 내림차순.
            @Override
            public int compare(Music o1, Music o2) {

                switch (SelSort) {
                    case 1:
                        return o1.getTitle().compareTo(o2.getTitle());
                    case 2:
                        return o2.getTitle().compareTo(o1.getTitle());
                    case 3:
                        return o1.getArtist().compareTo(o2.getArtist());
                    case 4:
                        return o2.getArtist().compareTo(o1.getArtist());
                    default:
                        return 0;
                }
            }
        });

        //print
//        System.out.println("Artist  Title");
//        for(int i = 0; i<Mlist.size(); i++){
//            System.out.println(Mlist.get(i).getArtist() + "      " + Mlist.get(i).getTitle());
//        }
        return Mlist;
    }
}
