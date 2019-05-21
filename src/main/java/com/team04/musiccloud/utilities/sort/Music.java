package com.team04.musiccloud.utilities.sort;

import java.util.ArrayList;

public class Music implements Comparable<Music> {

    private String title;
    private String artist;

    public Music(){}


    public Music(String title, String artist){
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public int compareTo(Music m) {
        return title.compareTo(m.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Music music = (Music) o;

        if (title != null ? !title.equals(music.title) : music.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }


    public ArrayList<Music> Musiclist(){
        //test title & artist
        //@TODO 실전에서는 이 배열을 제외하고 저장소에서 데이터를 불러온다.
        String[] title = {"abc", "하늘보리", "change", "dark", "zero", "who", "you", "monday", "너뭐하늘", "target",
                "jungle", "face", "embed", "gount", "이제 그만", "가지마", "돌아와", "카라멜", "수선화", "뭐라고",
                "영어", "하 늘", "거짓말", "하늘하늘", "다시와", "하늘나라", "천장", "타지에서", "박하늘짱", "지나가다"};

        String[] artist = {"견과류","휘성","보아","젝스키스","sam smith","ariana grande","john","park","camila","x-japan",
                "정글","하늘하늘","김윤수","김범수","이수","나얼","mc the max","저 차가운 하늘처럼 ","김필","샘김",
                "폴김","문근영","하늘","10cm","크레용팝","설운도","나얼","휘성","보아","김범수"};

        ArrayList<Music> playlist = new ArrayList<>();

        // playlist add
        for(int i=0; i < title.length; i++){
            Music list = new Music(title[i], artist[i]);
            playlist.add(list);
        }

        return playlist;
    }

//    public static void main(String[] argc){
//        Music m = new Music();
//        ArrayList<Music> musiclist = m.Musiclist();
//
//        for(int i = 0; i< musiclist.size(); i++){
//            System.out.println(musiclist.get(i));
//        }
//
//    }


}
