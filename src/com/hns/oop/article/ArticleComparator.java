/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hns.oop.article;

/**
 *
 * @author Harun
 */
public class ArticleComparator {

    private SimilarityStrategy similarityStrategy;
    
    public ArticleComparator(SimilarityStrategy similarityStrategy) {
        this.similarityStrategy = similarityStrategy;
    }
    
    public float getSimilarity(Article a1, Article a2){
        return similarityStrategy.getSimilarity(a1, a2);
    }
    
}
