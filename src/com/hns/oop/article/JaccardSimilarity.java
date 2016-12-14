/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hns.oop.article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Harun
 */
public class JaccardSimilarity implements SimilarityStrategy {

    @Override
    public float getSimilarity(Article a1, Article a2) {
        
        Set<String> set1 = new HashSet<>(getAnagramList(a1));
        Set<String> set2 = new HashSet<>(getAnagramList(a2));
        
        int size1 = set1.size();
        int size2 = set2.size();
        
        set1.retainAll(set2);
        
        return 100*((float)set1.size()/(size1 + size2 - set1.size()));
    }
    
    private List<String> getAnagramList(Article a){
        List<String> anagramList = new ArrayList<>();
        for(String s : a.getKeywordList(0)){
            s = "_".concat(s).concat("_");
            for(int i=0; i<s.length()-2; i++){
                anagramList.add(s.substring(i, i+3));
            }
        }
        anagramList = anagramList.stream().distinct().collect(Collectors.toList());
        
        return anagramList;
    }
    
}
