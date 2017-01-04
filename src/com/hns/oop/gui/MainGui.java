package com.hns.oop.gui;

import com.hns.oop.Helper;
import com.hns.oop.exceptions.DatabaseException;
import com.hns.oop.exceptions.ParserException;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.quartz.SchedulerException;

public class MainGui extends javax.swing.JFrame{
    
    public MainGui() {
        Helper.getDefaultHelper();
        
        initComponents();
        
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        jLabelFailed.setVisible(false);
        jButtonKaydet.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableResult = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButtonPopulateDatabase = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableExam = new javax.swing.JTable();
        jButtonLoad = new javax.swing.JButton();
        jLabelFailed = new javax.swing.JLabel();
        jButtonKaydet = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemSetEmail = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Assistant");

        jTableResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TITLE", "AUTHOR", "VENUE", "YEAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jScrollPane1.setViewportView(jTableResult);
        if (jTableResult.getColumnModel().getColumnCount() > 0) {
            jTableResult.getColumnModel().getColumn(3).setMinWidth(50);
            jTableResult.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTableResult.getColumnModel().getColumn(3).setMaxWidth(50);
        }

        jTextFieldSearch.setToolTipText("Search");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Title");

        jRadioButton2.setText("Year");

        jRadioButton3.setText("Keyword");

        jButtonPopulateDatabase.setText("Populate Database");
        jButtonPopulateDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPopulateDatabaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton3)
                                .addGap(287, 287, 287)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonPopulateDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jButtonPopulateDatabase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextFieldSearch.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Article", jPanel1);

        jTableExam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME", "DATE", "FIRST APPLICATION", "LAST APPLICATION", "RESULT", "ALERT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableExam);
        if (jTableExam.getColumnModel().getColumnCount() > 0) {
            jTableExam.getColumnModel().getColumn(1).setMinWidth(80);
            jTableExam.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTableExam.getColumnModel().getColumn(1).setMaxWidth(80);
            jTableExam.getColumnModel().getColumn(2).setMinWidth(130);
            jTableExam.getColumnModel().getColumn(2).setPreferredWidth(130);
            jTableExam.getColumnModel().getColumn(2).setMaxWidth(130);
            jTableExam.getColumnModel().getColumn(3).setMinWidth(125);
            jTableExam.getColumnModel().getColumn(3).setPreferredWidth(125);
            jTableExam.getColumnModel().getColumn(3).setMaxWidth(125);
            jTableExam.getColumnModel().getColumn(4).setMinWidth(80);
            jTableExam.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableExam.getColumnModel().getColumn(4).setMaxWidth(80);
            jTableExam.getColumnModel().getColumn(5).setMinWidth(50);
            jTableExam.getColumnModel().getColumn(5).setPreferredWidth(50);
            jTableExam.getColumnModel().getColumn(5).setMaxWidth(50);
        }

        jButtonLoad.setText("LOAD EXAMS ↺");
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jLabelFailed.setText("Failed Loading Exams!");

        jButtonKaydet.setText("Save Selection");
        jButtonKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKaydetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFailed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonKaydet)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFailed)
                    .addComponent(jButtonKaydet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Exam", jPanel2);
        jPanel2.getAccessibleContext().setAccessibleName("");

        jMenu1.setText("File");

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Settings");

        jMenuItemSetEmail.setText("Set Email Address");
        jMenuItemSetEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetEmailActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSetEmail);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

        searchButton.setEnabled(false);
        
        String query = "";
        
        if(jRadioButton1.isSelected()){
            query = "title=/"+jTextFieldSearch.getText()+"/";
        }
        else if(jRadioButton2.isSelected()){
            query = "year="+jTextFieldSearch.getText();
        }
        else if(jRadioButton3.isSelected()){
            query = "keywords=/"+jTextFieldSearch.getText()+"/";
        }
        
        try {
            Helper.getDefaultHelper().searchArticles((DefaultTableModel) jTableResult.getModel(), query);
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        searchButton.setEnabled(true);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        
        try {
            Helper.getDefaultHelper().getExamsFromÖSYM((DefaultTableModel) jTableExam.getModel());
            jLabelFailed.setVisible(false);
        } catch (ParserException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            jLabelFailed.setVisible(true);
        } catch (SchedulerException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
            jLabelFailed.setVisible(false);
        }
        
        jButtonKaydet.setEnabled(true);
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jTableResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableResultMouseClicked
        if (evt.getClickCount()>=2){
            int index = jTableResult.getSelectedRow();
            
            new ArticleReaderGui(Helper.getDefaultHelper().getArticleFromFoundArticles(index)).setVisible(true);
        }
    }//GEN-LAST:event_jTableResultMouseClicked

    private void jButtonKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKaydetActionPerformed
        try {
            Helper.getDefaultHelper().saveExamsToFile((DefaultTableModel) jTableExam.getModel());
            JOptionPane.showMessageDialog(this, "Saved. You will be notified when the exam date is close.");
        } catch (IOException | SchedulerException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButtonKaydetActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemSetEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetEmailActionPerformed
        new SetEmailGui().setVisible(true);
    }//GEN-LAST:event_jMenuItemSetEmailActionPerformed

    private void jButtonPopulateDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPopulateDatabaseActionPerformed
        try {
            String result = Helper.getDefaultHelper().populateDatabase();
            JOptionPane.showMessageDialog(this, result);
        } catch (DatabaseException | IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButtonPopulateDatabaseActionPerformed
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonKaydet;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonPopulateDatabase;
    private javax.swing.JLabel jLabelFailed;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemSetEmail;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableExam;
    private javax.swing.JTable jTableResult;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
