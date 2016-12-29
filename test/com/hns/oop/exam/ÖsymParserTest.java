/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hns.oop.exam;

import com.hns.oop.exceptions.ParserException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Naim YÜREK
 */
public class ÖsymParserTest {
    
    public ÖsymParserTest() {
    }

    @Test
    public void testGetList() {
        System.out.println("getList");
        try {
            ArrayList<Exam> result = ÖsymParser.getList();
            for (Exam e : result){
                System.out.println(e);
            }
        } catch (ParserException ex) {
            System.out.println(ex);
        }
    }
    
}
