package com.hns.oop;

import com.hns.oop.article.Article;
import com.hns.oop.article.ArticleComparator;
import com.hns.oop.article.ArticleDatabase;
import com.hns.oop.article.Database;
import com.hns.oop.article.JaccardSimilarity3Gram;
import com.hns.oop.article.PdfFile;
import com.hns.oop.csv.CsvReader;
import com.hns.oop.csv.CsvWriter;
import com.hns.oop.exam.Exam;
import com.hns.oop.exam.ÖsymParser;
import com.hns.oop.exceptions.DatabaseException;
import com.hns.oop.exceptions.ParserException;
import com.hns.oop.notifier.Email;
import com.hns.oop.notifier.EmailNotifier;
import com.hns.oop.notifier.Notifier;
import com.hns.oop.quartz.NotificationJob;
import com.hns.oop.quartz.NotificationScheduler;
import com.hns.oop.quartz.SendMailJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import org.quartz.SchedulerException;

public class Helper {

    private static Helper defaultHelper = null;
    
    private String MAILFILE;
    private String MAILUSERNAME;
    private String MAILPASSWORD;
    private String DATABASE_HOST;
    private String DATABASE_COLLECTION;
    private String ACM_CSV_FILE;
    private String EXAMS_CSV_FILE;
    private String CRON_EXPRESSION;
    
    private Email stdMail;
    private Database<Article> database;
    private ArrayList<Article> foundArticles;
    private ArrayList<Exam> selectedExams;
    private Notifier notifier;
    private String userMail;
    private NotificationScheduler scheduler;
    
    private Helper() {
        MAILFILE = "UserMailInfo.txt";
        MAILUSERNAME = "oopnotifier@gmail.com";
        MAILPASSWORD = "egebilmuh";
        DATABASE_HOST = "mongodb://oop:658898@ds133398.mlab.com:33398/oop";
        DATABASE_COLLECTION = "article";
        ACM_CSV_FILE = "acm.csv";
        EXAMS_CSV_FILE = "exams.csv";
        CRON_EXPRESSION = "0 0 12 1/1 * ? *"; // Once a day at 12.00 am
        
        database = new ArticleDatabase(DATABASE_HOST, DATABASE_COLLECTION);
        notifier = new EmailNotifier(MAILUSERNAME, MAILPASSWORD);
        selectedExams = new ArrayList<>();
        stdMail = new Email(MAILUSERNAME, userMail, "Exam Notify", "You have a closing exam!");
        initialize();
    } // Standart Helper
    
    public Helper(String mailUserName, String mailPassword, String dbHost, String dbCol, String cronExp){
        
        MAILUSERNAME = mailUserName;
        MAILPASSWORD = mailPassword;
        DATABASE_HOST = dbHost;
        DATABASE_COLLECTION = dbCol;
        CRON_EXPRESSION = cronExp;
        
        ACM_CSV_FILE = "acm.csv";
        EXAMS_CSV_FILE = "exams.csv";
        MAILFILE = "UserMailInfo.txt";
        
        database = new ArticleDatabase(DATABASE_HOST, DATABASE_COLLECTION);
        notifier = new EmailNotifier(MAILUSERNAME, MAILPASSWORD);
        selectedExams = new ArrayList<>();
        stdMail = new Email(MAILUSERNAME, userMail, "Exam Notify", "You have a closing exam!");
        initialize();
    }
    
    public static Helper getDefaultHelper(){ 
        if (defaultHelper == null){
            defaultHelper = new Helper();
        }
        return defaultHelper;
    } // Singleton
    
    public void initialize(){
        try {
            readExamsFromFile();
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            readUserMailInformationFromFile();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            restartNotificationScheduler();
        } catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
        }
    } // Program ilkleniyor
    
    public void saveExamsToFile(DefaultTableModel model) throws IOException, SchedulerException{
        
        selectedExams.clear();
        for(int i=0; i<model.getRowCount(); i++){
            if((Boolean) model.getValueAt(i, 5)){ // Eğer kullanıcı tarafından işaretlenmiş ise
                Exam e = new Exam(  (String) model.getValueAt(i, 0), 
                                    (String) model.getValueAt(i, 1), 
                                    (String) model.getValueAt(i, 2),
                                    (String) model.getValueAt(i, 3),
                                    (String) model.getValueAt(i, 4));
                selectedExams.add(e); // O sınavı seçili olarak kaydet.
            }
        }
         
        String[] columns = {"name","examdate","applicationfirst","applicationlast","result"};
        CsvWriter cw = new CsvWriter(EXAMS_CSV_FILE, columns); // Önce sütun isimlerini yazdır.
            
        for(Exam e : selectedExams){
            String[] contents = {e.getAd(), e.getSınavTarihi(), e.getBaşvuruTarihiFirst(), e.getBaşvuruTarihiLast(), e.getSonuçTarihi()};
            cw.write(contents);
        } // Bütün seçili sınavları yazdır.
        cw.close(); // Yazıcıyı kapat.
        
        restartNotificationScheduler(); // Yeni seçilmiş sınavların bildiriminin gitmesi için scheduler'ı yeniden kur.
        
    } // Tablo modelini alip içindekileri dosyaya yazdırır.
    
    public void getExamsFromÖSYM(DefaultTableModel model) throws ParserException, SchedulerException {
        
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            readExamsFromFile(); // Seçili sınavları dosyadan okur.
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        ArrayList<Exam> alExam = ÖsymParser.getParser().getList();
        for(Exam e : alExam){
            model.addRow(new Object[]{e.getAd(), e.getSınavTarihi(), e.getBaşvuruTarihiFirst(), e.getBaşvuruTarihiLast(), e.getSonuçTarihi(), selectedExams.stream().anyMatch((exam) -> (exam.equals(e)))});
        } // Tüm sınavları tabloya yerleştirir, seçili olup olmamasını dosyadan okuduğu bilgilere göre belirler.
    } // Ösym'den aldığı sınav bilgilerini Tablo modeline yerleştirir.
    
    public void readExamsFromFile() throws IOException {
        
        selectedExams.clear(); // Seçili sınavları temizler.
        try {
            CsvReader cr = new CsvReader(EXAMS_CSV_FILE);
            String[] values;
            while((values = cr.readNext())!= null){
                Exam e = new Exam(values[0], values[1], values[2], values[3], values[4]);
                selectedExams.add(e);
            } // Seçili sınavları dosyadan okur.
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    } // Dosyadan seçilmiş sınavları okur.
    
    public void saveUserMailInformationToFile() throws IOException{
        FileWriter fw = new FileWriter(MAILFILE);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(userMail + " " + notifier.isaDayAgo() + " " + notifier.isaWeekAgo());
        bw.close();
        fw.close();
    } // Kullanıcının mail ve bildirim istekleri bilgilerini dosyaya yazdırır.
    
    public void readUserMailInformationFromFile() throws IOException{
        userMail = null;
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(MAILFILE));
            String line = br.readLine();
            String[] strs = line.split(" ");
            userMail = strs[0];
            notifier.setaDayAgo(strs[1].equals("true"));
            notifier.setaWeekAgo(strs[2].equals("true"));
            br.close();
        }
        catch (IOException ex){
            notifier.setaDayAgo(false);
            notifier.setaWeekAgo(false);
            throw ex;
        }
        
        stdMail.setTo(userMail);
    } // Kullanıcının mail ve bildirim isteklerini dosyadan okur.
    
    public String populateDatabase() throws IOException, DatabaseException {
        
        CsvReader cr;
        cr = new CsvReader(ACM_CSV_FILE);
        
        String[] s;
        Downloader d = Downloader.getDownloader();
        int counterFailed = 0;
        int counter = 0;
        
        while ((s = cr.readNext())!= null) {
            if (database.find("id="+s[0]).isEmpty()){
                d.download("http://dl.acm.org/citation.cfm?id=" + s[0], s[0] + ".pdf");
                        
                PdfFile pdfFile = new PdfFile(s[0] + ".pdf");
                        
                Article article = new Article(s[0], s[1], s[2], s[3], s[4], pdfFile.toString());
                        
                counter++; // Her makale için 1 artar
                    
                try{
                    database.insert(article);
                }
                catch (DatabaseException ex){
                    counterFailed++; // Veri tabanına makaleyi eklerken hata olursa 1 artar.
                }
            }
        }
        
        return counterFailed + " out of " + counter + " article(s) are populated into database.";
    } // Veri tabanını internet sitesinden makale id'si ile makale çekerek doldurur.
    
    public void restartNotificationScheduler() throws SchedulerException{
        
        if (scheduler != null) // Scheduler çalışıyorsa durdur
            scheduler.stop();
        
        SendMailJob.setStandartEmail(stdMail);
        NotificationJob.setNotifier(notifier);
        NotificationJob.setCronExpression(CRON_EXPRESSION);
        
        ArrayList<NotificationJob> jobs = new ArrayList<>();
        for(Exam e : selectedExams){
            SendMailJob smj = new SendMailJob(e.getAd(), e.getSınavTarihi(), e.getBaşvuruTarihiFirst(), e.getBaşvuruTarihiLast(), e.getSonuçTarihi());
            jobs.add(smj);
        } // Her sınav nesnesinden bir iş nesnesi yarat.
        
        scheduler = new NotificationScheduler(jobs);
        
        scheduler.start();
    } // Scheduler'ı yeniden kurup başlatır.
    
    public void searchArticles(DefaultTableModel model, String query) throws DatabaseException {
        
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        foundArticles = database.find(query);
        
        for(Article a : foundArticles){
            model.addRow(new Object[]{a.getTitle(), a.getAuthor(), a.getVenue(), a.getYear()});
        }
    } // Tabloyu verilen sorguyu veri tabanında uygulayarak doldurur.
    
    public ArrayList<Article> setSimilars(Article article, DefaultTableModel model, int count) throws DatabaseException{
        
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        Map<Article, Float> map = new HashMap<>();
        ArticleComparator ac = new ArticleComparator(new JaccardSimilarity3Gram());
        
        for (Article w : database.find("")) {
            Float similarity = ac.getSimilarity(article, w);
            map.put(w, similarity);
        } // Map'e makale ve benzerlik oranı eklenir.
        
        map = map.entrySet() // Map benzerlik oranına göre büyükten küçüğe sıralanır.
        .stream()
        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        
        ArrayList<Article> similars = new ArrayList<>();
        
        for(Map.Entry<Article,Float> e : map.entrySet()){
            if (model.getRowCount() != count){ // Count 'ı aşmayacak şekilde tabloya eklenir.
                Article a = e.getKey();
                Float similarity = e.getValue();
                if (!article.equals(a)){
                    model.addRow(new Object[]{a.getTitle(), a.getAuthor(), a.getVenue(), a.getYear(), similarity});
                    similars.add(a);
                }
            }
            else {
                return similars;
            }
        }
        
        return similars;
    } // Veri tabanındaki tüm makaleler parametre olarak alınan makale ile karşılaştırılır. Benzerlik oranına göre büyükten küçüğe sıralanıp X tanesi tabloya eklenir. Ayrıca ArrayList olarak döndürülür.
    
    public void setNotificationSettings(String userMail, boolean aDayAgo, boolean aWeekAgo) throws IOException, SchedulerException{
        this.userMail = userMail;
        notifier.setaDayAgo(aDayAgo);
        notifier.setaWeekAgo(aWeekAgo);
        
        saveUserMailInformationToFile();
        restartNotificationScheduler();
    } // Bildirim ayarları set ediliyor.
    
    public Article getArticleFromFoundArticles(int index) {
        return foundArticles.get(index);
    }

    public Database<Article> getDatabase() {
        return database;
    }

    public ArrayList<Article> getFoundArticles() {
        return foundArticles;
    }

    public NotificationScheduler getScheduler() {
        return scheduler;
    }

    public ArrayList<Exam> getSelectedExams() {
        return selectedExams;
    }

    public String getUserMail() {
        return userMail;
    }
    
    public Notifier getNotifier() {
        return notifier;
    }
   
    public void setAcmCsvFile(String acmCsvFile) {
        this.ACM_CSV_FILE = acmCsvFile;
    }

    public void setCronExpression(String cronExpression) {
        this.CRON_EXPRESSION = cronExpression;
    }

    public void setDatabaseCollection(String databaseCollection) {
        this.DATABASE_COLLECTION = databaseCollection;
    }

    public void setDatabaseHost(String databaseHost) {
        this.DATABASE_HOST = databaseHost;
    }

    public void setDatabase(Database<Article> database) {
        this.database = database;
    }

    public static void setDefaultHelper(Helper defaultHelper) {
        Helper.defaultHelper = defaultHelper;
    }

    public void setExamCsvFile(String examCsvFile) {
        this.EXAMS_CSV_FILE = examCsvFile;
    }

    public void setMailFile(String mailFile) {
        this.MAILFILE = mailFile;
    }

    public void setMailPassword(String mailPassword) {
        this.MAILPASSWORD = mailPassword;
    }

    public void setMailUsername(String mailUsername) {
        this.MAILUSERNAME = mailUsername;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    public void setScheduler(NotificationScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setStdMail(Email stdMail) {
        this.stdMail = stdMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
