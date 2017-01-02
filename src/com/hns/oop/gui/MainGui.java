package com.hns.oop.gui;

import com.hns.oop.csv.CsvReader;
import com.hns.oop.csv.CsvWriter;
import com.hns.oop.Downloader;
import com.hns.oop.quartz.QuartzScheduler;
import com.hns.oop.article.Article;
import com.hns.oop.article.ArticleDatabase;
import com.hns.oop.article.Database;
import com.hns.oop.article.PdfFile;
import com.hns.oop.exam.Exam;
import com.hns.oop.exam.ÖsymParser;
import com.hns.oop.exceptions.DatabaseException;
import com.hns.oop.exceptions.ParserException;
import com.hns.oop.notifier.Email;
import com.hns.oop.notifier.EmailNotifier;
import com.hns.oop.notifier.Notifier;
import com.hns.oop.quartz.SendMailJob;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.quartz.SchedulerException;

public class MainGui extends javax.swing.JFrame{

    private final String MAILFILE = "UserMailInfo.txt";
    private final String MAILUSERNAME = "oopnotifier@gmail.com";
    private final String MAILPASSWORD = "egebilmuh";
    
    private final String DBHOST = "mongodb://oop:658898@ds133398.mlab.com:33398/oop";
    private final String DBCOLLECTION = "article";
    
    private final String ACM_CSV = "acm.csv";
    private final String EXAMS_CSV = "exams.csv";
    private final String ARTICLE_DIRECTORY = "articles";
    private final String ARTICLE_DOWNLOAD_LINK = "http://dl.acm.org/citation.cfm?id=";
    
    private final String CRON_EXPRESSION = "0 0 12 1/1 * ? *"; // Once a day at 12.00 am
    
    private Database<Article> db;
    private ArrayList<Article> al;
    private ArrayList<Exam> selectedExams;
    private Notifier notifier;
    private String userMail;
    QuartzScheduler quartzScheduler;
    
    public MainGui() {
        initComponents();
        
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        jLabelFailed.setVisible(false);
        
        db = new ArticleDatabase(DBHOST, DBCOLLECTION);
        notifier = new EmailNotifier(MAILUSERNAME, MAILPASSWORD);
        selectedExams = new ArrayList<>();
        
        readSelectedExams();
        readMailInformation();
        
        jButtonKaydet.setEnabled(false);
        setNotifyScheduler();
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
                "NAME", "DATE", "ALERT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
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
            jTableExam.getColumnModel().getColumn(1).setMinWidth(120);
            jTableExam.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTableExam.getColumnModel().getColumn(1).setMaxWidth(120);
            jTableExam.getColumnModel().getColumn(2).setMinWidth(50);
            jTableExam.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTableExam.getColumnModel().getColumn(2).setMaxWidth(50);
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
        
        DefaultTableModel model = (DefaultTableModel) jTableResult.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        al = null;
        
        if(jRadioButton1.isSelected()){
            try {
                al = db.find("title=/"+jTextFieldSearch.getText()+"/");
            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if(jRadioButton2.isSelected()){
            try {
                al = db.find("year="+jTextFieldSearch.getText());
            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        else if(jRadioButton3.isSelected()){
            try {
                al = db.find("keywords=/"+jTextFieldSearch.getText()+"/");
            } catch (DatabaseException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
        
        if(al != null){
            for(Article a : al){
                model.addRow(new Object[]{a.getTitle(), a.getAuthor(), a.getVenue(), a.getYear()});
            }
        }
        
        searchButton.setEnabled(true);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        initExams();
        
        try {
            quartzScheduler.stop();
            setNotifyScheduler();
            jButtonKaydet.setEnabled(true);
        } catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
            jButtonKaydet.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jTableResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableResultMouseClicked
        if (evt.getClickCount()>=2){
            int index = jTableResult.getSelectedRow();
            
            new ArticleReaderGui(al.get(index), db).setVisible(true);
        }
    }//GEN-LAST:event_jTableResultMouseClicked

    private void jButtonKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKaydetActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableExam.getModel();
        selectedExams.clear();
        for(int i=0; i<model.getRowCount(); i++){
            if((Boolean) model.getValueAt(i, 2)){
                Exam e = new Exam( (String) model.getValueAt(i, 0), (String) model.getValueAt(i, 1));
                selectedExams.add(e);
            }
        }
        try {
            String[] columns = {"name","date"};
            CsvWriter cw = new CsvWriter(EXAMS_CSV, columns);
            
            for(Exam e : selectedExams){
                String[] contents = {e.getAd(), e.getTarih()};
                cw.write(contents);
            }
            cw.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Saved. You will be notified when the exam date is close.");
        
        try {
            quartzScheduler.stop();
            setNotifyScheduler();
        } catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButtonKaydetActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemSetEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetEmailActionPerformed
        new SetEmailGui(notifier, this).setVisible(true);
    }//GEN-LAST:event_jMenuItemSetEmailActionPerformed

    private void jButtonPopulateDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPopulateDatabaseActionPerformed
        populateDatabase(db, ACM_CSV);
    }//GEN-LAST:event_jButtonPopulateDatabaseActionPerformed

    private void initExams() {
        DefaultTableModel model = (DefaultTableModel) jTableExam.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        readSelectedExams();
        
        try {
            ArrayList<Exam> alExam = ÖsymParser.getParser().getList();
            if(alExam != null){
                for(Exam e : alExam){
                    model.addRow(new Object[]{e.getAd(), e.getTarih(), selectedExamsContains(e)});
                }
            }
            jLabelFailed.setVisible(false);
        } catch (ParserException ex) {
            jLabelFailed.setVisible(true);
        }
    }
    
    private boolean selectedExamsContains(Exam exam){
        return selectedExams.stream().anyMatch((e) -> (e.equals(exam)));
    }
    
    private void readSelectedExams() {
        try {
            selectedExams.clear();
            CsvReader cr = new CsvReader(EXAMS_CSV);
            String[] values;
            while((values = cr.readNext())!= null){
                Exam e = new Exam(values[0], values[1]);
                selectedExams.add(e);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getUserMail(){
        return this.userMail;
    }
    
    public void setUserMail(String userMail){
        this.userMail = userMail;
    }
    
    public void saveMailInformation(Notifier n, String mail) throws IOException{
        FileWriter fw = new FileWriter(MAILFILE);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(mail + " " + n.isaDayAgo() + " " + n.isaWeekAgo());
        bw.close();
        fw.close();
    }
    
    public void readMailInformation(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(MAILFILE));
            String line = br.readLine();
            String[] strs = line.split(" ");
            setUserMail(strs[0]);
            notifier.setaDayAgo(strs[1].equals("true"));
            notifier.setaWeekAgo(strs[2].equals("true"));
            br.close();
        } 
        catch (Exception ex) {
            notifier.setaDayAgo(false);
            notifier.setaWeekAgo(false);
            System.out.println(ex.getMessage());
        }
    }
    
    private void populateDatabase(Database<Article> db, String csvFile) {
        
        CsvReader cr;
        try {
            cr = new CsvReader(csvFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }
        String[] s;
        Downloader d = Downloader.getDownloader();
        d.setDirectory(ARTICLE_DIRECTORY);
        int counterFailed = 0;
        int counter = 0;
        
        try {
            while ((s = cr.readNext())!= null) {
                try {
                    if (db.find("id="+s[0]).isEmpty()){
                        d.download(ARTICLE_DOWNLOAD_LINK + s[0], s[0] + ".pdf");
                        
                        PdfFile pdfFile = new PdfFile(d.getDirectory() + s[0] + ".pdf");
                        
                        Article article = new Article(s[0], s[1], s[2], s[3], s[4], pdfFile.toString());
                        
                        counter++;
                        
                        try{
                            db.insert(article);
                        }
                        catch (DatabaseException ex){
                            counterFailed++;
                        }
                    }
                } catch (DatabaseException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                    return;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }
        
        JOptionPane.showMessageDialog(this, counterFailed + " out of " + counter + " article(s) are populated to database.");
    }
    
    private void setNotifyScheduler(){
        SendMailJob.setEmail(new Email(MAILUSERNAME, userMail, "Exam Notify", "You have a closing exam!"));
        SendMailJob.setNotifier(notifier);
        SendMailJob.setCronExpression(CRON_EXPRESSION);
        
        ArrayList<SendMailJob> jobs = new ArrayList<>();
        for(Exam e : selectedExams){
            SendMailJob smj = new SendMailJob(e.getAd(), e.getTarih());
            jobs.add(smj);
        }
        
        quartzScheduler = new QuartzScheduler(jobs);
        try {
            quartzScheduler.start();
        } catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
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
