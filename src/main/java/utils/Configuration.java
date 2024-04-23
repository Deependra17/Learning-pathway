package utils;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class Configuration {

    private String Url;
    private String ToLogin;
    private String ClickOnLogin;
    private String ContinueWithGoogle;
    private String EmailInput;
    private String PasswordInput;
    private String ChooseProfile;
    private String ClickOnHome;
    private String ClickOnBook;
    private String TurnPage;
    private String ClickOnReadButton;
    private String PageContent;
    private String CLickOnNextButton;
    private String SecondNextButton;
    private String Activity;
    private String CloseButton;
    private String InvalidUserName;
    private String InvalidPassword;
    private String inputField;

    public Configuration() {


        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            Properties prop = new Properties();
            prop.load(input);

            this.Url = prop.getProperty("Url");
            this.ToLogin = prop.getProperty("ToLogin");
            this.ClickOnLogin = prop.getProperty("ClickOnLogin");
            this.ContinueWithGoogle = prop.getProperty("ContinueWithGoogle");
            this.EmailInput = prop.getProperty("EmailInput");
            this.PasswordInput = prop.getProperty("PasswordInput");
            this.ChooseProfile = prop.getProperty("ChooseProfile");
            this.ClickOnHome = prop.getProperty("ClickOnHome");
            this.ClickOnBook = prop.getProperty("ClickOnBook");
            this.TurnPage = prop.getProperty("TurnPage");
            this.ClickOnReadButton = prop.getProperty("ClickOnReadButton");
            this.PageContent = prop.getProperty("PageContent");
            this.CLickOnNextButton = prop.getProperty("CLickOnNextButton");
            this.SecondNextButton = prop.getProperty("SecondNextButton");
            this.Activity = prop.getProperty("Activity");
            this.CloseButton = prop.getProperty("CloseButton");
            this.InvalidUserName=prop.getProperty("InvalidUserName");
            this.InvalidPassword=prop.getProperty("InvalidPassword");
            this.inputField= prop.getProperty("inputField");

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
