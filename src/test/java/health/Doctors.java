package health;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;


public class Doctors {
    WebDriver driver = new ChromeDriver();

    @Test
    public void getTotalDoctors() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Response response = RestAssured.get("https://health-backend.hamropatro.com/consultant/search/t_m_np?sid=&name=&cursor=");

        List<String> consultantNames = response.path("items.consultantResponse.name");

        System.out.println("Total number of consultants: " + consultantNames.size());
        System.out.println("Status Code: " + response.statusCode());
        driver.close();
    }

    @Test
    public void consultantNames() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Response response = RestAssured.get("https://health-backend.hamropatro.com/consultant/search/t_m_np?sid=&name=&cursor=");

        List<String> consultantNames = response.path("items.consultantResponse.name");

        System.out.println("Consultant names:");
        for (String name : consultantNames) {
            System.out.println(name);
        }
        System.out.println("Status Code: " + response.statusCode());
        driver.close();
    }

    @Test
    public void getPrice() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Response response = RestAssured.get("https://health-backend.hamropatro.com/consultant/search/t_m_np?sid=&name=&cursor=");

        List<Float> finalPrices = response.path("items.consultantResponse.finalPrice");
        System.out.println("Final price of consultants:");
        for (Float price : finalPrices) {
            System.out.println(price);
        }
        System.out.println("Status Code: " + response.statusCode());
        driver.close();
    }

    @Test
    public void getNameWithPrice() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Response response = RestAssured.get("https://health-backend.hamropatro.com/consultant/search/t_m_np?sid=&name=&cursor=");

        List<Map<String, Object>> consultants = response.path("items");

        System.out.println("Total number of consultants: " + consultants.size());
        System.out.println("Consultant names and prices:");
        for (Map<String, Object> consultant : consultants) {
            Map<String, Object> consultantResponse = (Map<String, Object>) consultant.get("consultantResponse");
            String name = (String) consultantResponse.get("name");
            Float finalPrice = (Float) consultantResponse.get("finalPrice");
            System.out.println("Name: " + name + ", Price: " + finalPrice);
        }
        System.out.println("status Code" + ": " + response.statusCode());
        driver.close();
    }

    @Test
    public void getDoctorsDetail() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Response response = RestAssured.get("https://health-backend.hamropatro.com/consultant/search/t_m_np?sid=&name=&cursor=");

        List<Map<String, ?>> doctors = response.path("items.consultantResponse");

        System.out.println("Total number of doctors: " + doctors.size());
        System.out.println("Doctor details:");

        for (Map<String, ?> doctor : doctors) {
            System.out.println("Name: " + doctor.get("name"));
            System.out.println("finalPrice: " + doctor.get("finalPrice"));

            List<String> specialities = (List<String>) doctor.get("specialities");
            System.out.println("Specialities: " + specialities);

            System.out.println("-----");
        }
        driver.close();
    }
}