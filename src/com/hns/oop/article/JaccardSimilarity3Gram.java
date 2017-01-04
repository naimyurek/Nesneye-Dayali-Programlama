package com.hns.oop.article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JaccardSimilarity3Gram implements SimilarityStrategy {

    @Override
    public float getSimilarity(Article a1, Article a2) {
        
        Set<String> set1 = new HashSet<>(getAnagramList(a1));
        Set<String> set2 = new HashSet<>(getAnagramList(a2));
        
        int size1 = set1.size(); // A
        int size2 = set2.size(); // B
        
        set1.retainAll(set2); // set1 = A kesişim B
        
        return 100*((float)set1.size()/(size1 + size2 - set1.size())); // (A kesişim B) / (A + B - (A kesişim B))
    }
    
    private List<String> getAnagramList(Article a){
        List<String> anagramList = new ArrayList<>();
        for(String s : a.getKeywordList(0)){
            s = "_".concat(s).concat("_");
            for(int i=0; i<s.length()-2; i++){
                anagramList.add(s.substring(i, i+3));
            }
        }
        anagramList = anagramList.stream().distinct().collect(Collectors.toList()); // Bütün 3gramlardan tekrar edenler çıkarılır.
        
        return anagramList;
    } // Bir makalenin tüm keywordlerinden anagram listesi oluşturur.
    
}
