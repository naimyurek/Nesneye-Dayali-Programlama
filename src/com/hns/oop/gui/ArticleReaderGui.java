package com.hns.oop.gui;

import com.hns.oop.Helper;
import com.hns.oop.article.Article;
import com.hns.oop.exceptions.DatabaseException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class ArticleReaderGui extends javax.swing.JFrame {

    Article article;
    ArrayList<Article> similars;
    
    
    public ArticleReaderGui(Article article) {
        initComponents();
        this.article = article;
        similars = new ArrayList<>();
        jTextAreaArticle.setText(article.getContent());
        try {
            similars = Helper.getDefaultHelper().setSimilars(article, (DefaultTableModel) jTableResult.getModel(), 4);
        } catch (DatabaseException ex) {
            System.out.println(ex);
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(article.getTitle() + " - " + article.getAuthor());
        jTextAreaArticle.setCaretPosition(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaArticle = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableResult = new javax.swing.JTable();
        jLabelSimilars = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaArticle.setColumns(20);
        jTextAreaArticle.setRows(5);
        jScrollPane1.setViewportView(jTextAreaArticle);

        jTableResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TITLE", "AUTHOR", "VENUE", "YEAR", "SIMILARITY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableResultMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableResult);
        if (jTableResult.getColumnModel().getColumnCount() > 0) {
            jTableResult.getColumnModel().getColumn(3).setMinWidth(50);
            jTableResult.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTableResult.getColumnModel().getColumn(3).setMaxWidth(50);
            jTableResult.getColumnModel().getColumn(4).setMinWidth(80);
            jTableResult.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTableResult.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        jLabelSimilars.setText("Similar articles you may like to read:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                    .addComponent(jLabelSimilars))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSimilars)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableResultMouseClicked
        if (evt.getClickCount()>=2){
            int index = jTableResult.getSelectedRow();
            System.out.println("Clicked " + evt.getClickCount() + " times on article with index number " + index + ".");
            new ArticleReaderGui(similars.get(index)).setVisible(true);
        }
    }//GEN-LAST:event_jTableResultMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelSimilars;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableResult;
    private javax.swing.JTextArea jTextAreaArticle;
    // End of variables declaration//GEN-END:variables
}
