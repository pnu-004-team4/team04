package com.team04.musiccloud.utilities.Search;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.team04.musiccloud.utilities.sort.Music;
import java.util.ArrayList;


public class Search {


   public ArrayList<Music> msearch(String search){
        Music m = new Music();
        ArrayList<Music> comp;

        comp = m.musiclist();

        ArrayList<Music> result = new ArrayList<>();

        //검색창

//정규식이 문자열과 부분적으로 전체적으로 일치하는 여부를 검사

       //검색창에 들어갈 내용
        Pattern regex = Pattern.compile(search, Pattern.CASE_INSENSITIVE);

        //music class에서 타이틀과 아티스트 합친 내용
        for(int i = 0; i < comp.size(); i++){
            Matcher regexMatcher = regex.matcher(comp.get(i).getArtist() + " " + comp.get(i).getTitle());
            boolean foundMatch = regexMatcher.find(); //true
            if(foundMatch) result.add(comp.get(i));
        }

//        //확인차 프린트
//        for(int i = 0; i < result.size(); i++) System.out.println(result.get(i).getArtist());

        return result;
    }
}
