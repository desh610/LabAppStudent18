package com.example.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LabAppointmentSystemTest {

    private WebDriver driver;

    @Before
    public void setUp() {
    	System.setProperty("webdriver.edge.driver", "C:\\Users\\desha\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = (WebDriver) new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        driver.get("http://localhost:8090/MyLabTest1/app/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("1");
        passwordField.sendKeys("1111");

        WebElement loginButton = driver.findElement(By.id("loginButton"));
        loginButton.click();

        String dashboardTitle = driver.getTitle();
        assertEquals("Lab Receptionist Dashboard", dashboardTitle);
    }

    @Test
    public void testAppointmentBooking() {
        driver.get("http://localhost:8090/MyLabTest1/app/login/add_appointments");

        WebElement dateField = driver.findElement(By.id("date"));
        WebElement timeField = driver.findElement(By.id("time"));
        WebElement purposeField = driver.findElement(By.id("purpose"));
        dateField.sendKeys("2025-12-31");
        timeField.sendKeys("09:00");
        purposeField.sendKeys("Routine check-up");

        WebElement submitButton = driver.findElement(By.id("submitButton"));
        submitButton.click();

        WebElement successMessage = driver.findElement(By.className("success-message"));
        assertTrue(successMessage.isDisplayed());
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}
