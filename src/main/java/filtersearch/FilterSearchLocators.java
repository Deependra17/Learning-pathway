package filtersearch;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class FilterSearchLocators {
    private String FilterSearchButton;
    private String SelectCategory;
    private String ShowBooksButton;
    private String ClickOnBookForCategory;
    private String VerifyCategory;
    private String CloseButton;
    private String Level;
    private String VerifyLevel;
    private String SelectLanguage;
    private String VerifyAllBooks;
    private String ChooseLanguage;
    private String SelectAudio;
    private String ClickOnAudioBook;
    private String VerifyAudio;
    private String ClickOnBook;

    public FilterSearchLocators() {
        try {
            InputStream input = new FileInputStream("src/main/resources/filter.properties");
            Properties prop = new Properties();
            prop.load(input);

            this.FilterSearchButton = prop.getProperty("FilterSearchButton");
            this.SelectCategory = prop.getProperty("SelectCategory");
            this.ShowBooksButton = prop.getProperty("ShowBooksButton");
            this.ClickOnBookForCategory = prop.getProperty("ClickOnBookForCategory");
            this.VerifyCategory = prop.getProperty("VerifyCategory");
            this.CloseButton = prop.getProperty("CloseButton");
            this.Level = prop.getProperty("Level");
            this.VerifyLevel = prop.getProperty("VerifyLevel");
            this.SelectLanguage = prop.getProperty("SelectLanguage");
            this.VerifyAllBooks = prop.getProperty("VerifyAllBooks");
            this.ChooseLanguage = prop.getProperty("ChooseLanguage");
            this.SelectAudio = prop.getProperty("SelectAudio");
            this.ClickOnAudioBook = prop.getProperty("ClickOnAudioBook");
            this.VerifyAudio = prop.getProperty("VerifyAudio");
            this.ClickOnBook = prop.getProperty("ClickOnBook");


        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }

}
