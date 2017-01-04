package com.hns.oop.article;

public class ArticleComparator {

    private SimilarityStrategy similarityStrategy;
    
    public ArticleComparator(SimilarityStrategy similarityStrategy) {
        this.similarityStrategy = similarityStrategy;
    } // Hangi benzerlik stratejisi kullanılacaksa belirlenir.
    
    public float getSimilarity(Article a1, Article a2){
        return similarityStrategy.getSimilarity(a1, a2);
    } // İki makalenin benzerliğinin yüzdesini döndürür.
    
}
