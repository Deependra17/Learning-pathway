package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    public String getUrl() {
        return Url;
    }

    public String getToLogin() {
        return ToLogin;
    }

    public String getClickOnLogin() {
        return ClickOnLogin;
    }

    public String getEmailInput() {
        return EmailInput;
    }

    public String getPasswordInput() {
        return PasswordInput;
    }

    public String getContinueWithGoogle() {
        return ContinueWithGoogle;
    }

    public String getChooseProfile() {
        return ChooseProfile;
    }

    public String getClickOnHome() {
        return ClickOnHome;
    }

    public String getClickOnBook() {
        return ClickOnBook;
    }

    public String getTurnPage() {
        return TurnPage;
    }

    public String getClickOnReadButton() {
        return ClickOnReadButton;
    }

    public String getPageContent() {
        return PageContent;
    }

    public String getCLickOnNextButton() {
        return CLickOnNextButton;
    }

    public String getSecondNextButton() {
        return SecondNextButton;
    }

    public String getActivity() {
        return Activity;
    }

    public String getCloseButton() {
        return CloseButton;
    }

    public String getInvalidUserName() {
        return InvalidUserName;
    }

    public String getInvalidPassword() {
        return InvalidPassword;
    }

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

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
