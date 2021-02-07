import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;




public class Commands {
    WebDriver driver;
    @Before
    public void SetUp(){
        String driverExecutablePath = "C://WebDriver//bin//chromedriver.exe";
        System.getProperty("webdriver.chrome.driver",driverExecutablePath);
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void practice1() throws InterruptedException {
        String url = "https://www.google.com";
        driver.get(url);
        Thread.sleep(5000L);

        //select google search field and enter data in it
        WebElement searchField = driver.findElement(By.name("q"));
        Thread.sleep(2000L);
        searchField.sendKeys("selenium");
        Thread.sleep(2000L);
        searchField.submit();
        Thread.sleep(5000L);

        //find the link however it is not the first link !!!!
        String elementXPath = "//div[@class='yuRUbf']/a[@href = 'https://www.selenium.dev/']";
        WebElement element = driver.findElement(By.xpath(elementXPath));
        element.click();
        Thread.sleep(2000L);

        //validate titles
        String title = "SeleniumHQ Browser Automation";
        String currentTitle = driver.getTitle();
        System.out.println("Title is "+title);
        Thread.sleep(2000L);
        Assert.assertEquals(title,currentTitle);
    }


    @Test
    public void registration() throws InterruptedException {
        String url = "http://automationpractice.com/index.php";
        driver.get(url);
        //navigate and click Sign in button
        String loginElementXPath = "//div[@class = 'header_user_info']/a[@title = 'Log in to your customer account']";
        WebElement loginElement = driver.findElement(By.xpath(loginElementXPath));
        loginElement.click();
        Thread.sleep(2000L);
        //enter an valid email
        WebElement createEmail = driver.findElement(By.id("email_create"));
        String email1 = createEmail.getText();
        createEmail.click();
        createEmail.sendKeys("sof123@gmail.com");
        Thread.sleep(2000L);
        //clicks Create an account
        WebElement submitButton = driver.findElement(By.id("SubmitCreate"));
        submitButton.click();
        Thread.sleep(2000L);
        //validate that the email is the same
        WebElement registrationEmail = driver.findElement(By.id("email"));
        String email2 = registrationEmail.getText();
        Assert.assertEquals(email1,email2);
        //fill the form
        fillingTheForm();
        //catch the message
        String title = "Welcome to your account. Here you can manage all of your personal information and orders";
        String currentTitle = driver.getTitle();
        System.out.println("Title is "+title);
        Thread.sleep(2000L);
        Assert.assertEquals(title,currentTitle);

    }

    private void fillingTheForm() throws InterruptedException {
        String customerFirstName = "Sofi-Mary";
        String customerLastName = "Mc'donal";

        //fill the title
        driver.findElement(By.id("id_gender2")).click();
        //fill the first and second name
        firstName(customerFirstName);
        lastName(customerLastName);
        //fill the password
        password("hoho1234");
        //fill date of birth
        Birthdate();
        //fill the check buttons
        CheckButtons();
        //fill your address
        //-fill first name
        //error ?????????????????????????????????????????????????????????
        // checkTheFirstName(customerFirstName);
        //-fill second name
        // checkTheLastName(customerLastName);
        //-fill address line1
        driver.findElement(By.id("address1")).sendKeys("Gaaaa st");
        Thread.sleep(2000L);
        //-fill address line2
        driver.findElement(By.id("address2")).sendKeys("floor 7 ap 85 ");
        Thread.sleep(2000L);
        //-fill city
        city("New York");
        //-fill State
        AddrState();
        //-fill country
        country();
        //-fill zip
        postcode("34521");
        //-fill mobile phone
        boolean isHomeNumberEmpty = false;
        boolean isMobileNumberEmpty = false;
        isHomeNumberEmpty = homePhoneNumber("");
        isMobileNumberEmpty = mobilePhoneNumber("+420-889-2856");
        if(!isHomeNumberEmpty && !isMobileNumberEmpty){
            System.out.println("There must be at least one phone number");
        }
        //submit
        driver.findElement(By.id("submitAccount")).click();
        Thread.sleep(5000L);
    }


    private void country() {
        //get the countrylist Element
        WebElement countryElement = driver.findElement(By.xpath("//select[@id='id_country']"));
        // check if element is disabled
        if (!countryElement.isEnabled()) {
            // get value from option 1 as countrylist is disabled.
            System.out.println(countryElement.findElement(By.tagName("option")).getText());
        }else {
            // select value as countrylist is enabled. default value = United States
            countryElement.findElement(By.xpath(".//option[.='United States']")).click();
        }
    }

    private boolean mobilePhoneNumber(String customerMobilePhoneNumber)  {
        WebElement mobileElement = driver.findElement(By.id("phone_mobile"));
        mobileElement.sendKeys(customerMobilePhoneNumber);
        boolean isEmpty = false;
        if (mobileElement.getAttribute("value").isEmpty()){
            isEmpty = true;
        }
        else{
            validatePhoneNumber(customerMobilePhoneNumber);
        }
        return isEmpty;

    }

    private boolean homePhoneNumber(String customerMobilePhoneNumber) {
        WebElement homeElement = driver.findElement(By.id("phone"));
        homeElement.sendKeys(customerMobilePhoneNumber);
        boolean isEmpty = false;
        if (homeElement.getAttribute("value").isEmpty()){
            isEmpty = true;
        }
        else {
            validatePhoneNumber(customerMobilePhoneNumber);
        }
        return isEmpty;
    }

    private void validatePhoneNumber(String phoneNumber){
        String myPattern = "\\+\\d{3}[\\.\\s]\\d{3}[-\\.\\s]\\d{4}";
        if(!phoneNumber.matches(myPattern)){
            System.out.println("Invalid phone! The format is +420 256 3568!");
        }
    }

    private void compareTextElements(WebElement el1, WebElement el2) throws InterruptedException {
        String text1 = el1.getText();
        String text2 = el2.getText();
        Assert.assertEquals(text1,text2);
        Thread.sleep(5000L);
    }


    private void checkTheLastName(String customerLastName) throws InterruptedException {
        WebElement lastNameElement = driver.findElement(By.id("lastname"));
        String lastName = lastNameElement.getText();
        Assert.assertEquals(customerLastName,lastName);
        Thread.sleep(2000L);
    }

    private void checkTheFirstName(String customerFirstName) throws InterruptedException {
        WebElement firstNameElement = driver.findElement(By.id("firstname"));
        String firstName = firstNameElement.getText();
        Assert.assertEquals(firstName,customerFirstName);
        Thread.sleep(2000L);
    }

    private void lastName(String customerLastName) {
        WebElement firstNameElement = driver.findElement(By.id("customer_lastname"));
        firstNameElement.sendKeys(customerLastName);
        validateNames(customerLastName);
        checkIfEmpty(firstNameElement);
    }

    private void firstName(String customerFirstName) {
        WebElement firstNameElement = driver.findElement(By.id("customer_firstname"));
        firstNameElement.sendKeys(customerFirstName);
        validateNames(customerFirstName);
        checkIfEmpty(firstNameElement);
    }

    private void validateNames(String name){
        //- is not accepted as valid ????????????????????????????????????????????????????????????????????????????????????????
        String myPattern = "[A-Z][a-zA-Z\s.’-]*";
        if(!name.matches(myPattern)){
            System.out.println("Invalid name! Only letters!");
        }
    }

    private void AddrState() {
        driver.findElement(By.id("id_state")).sendKeys("floor 7 ap 85 ");
        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByVisibleText("New York");
        //how to validate that the index is less than 51 ???????????????????????????????????????????????????????????????????
//        System.out.println(state.selectByValue());
    }

    private void CheckButtons() throws InterruptedException {
        WebElement newsletterBox = driver.findElement(By.xpath("//input[@id = 'newsletter']"));
        WebElement offersBox = driver.findElement(By.xpath("//input[@id = 'optin']"));
        selectCheckbox(newsletterBox);
        Thread.sleep(2000L);
        selectCheckbox(offersBox);
        deselectCheckbox(newsletterBox);
        Thread.sleep(2000L);
    }

    private void Birthdate() throws InterruptedException {
        Select day = new Select(driver.findElement(By.id("days")));
        day.selectByValue("25");
        Select month = new Select(driver.findElement(By.id("months")));
        month.selectByIndex(4);
        Select year = new Select(driver.findElement(By.id("years")));
        year.selectByValue("2000");
        Thread.sleep(5000L);
    }


    private void city(String customerCity) {
        WebElement cityElement = driver.findElement(By.id("city"));
        cityElement.sendKeys(customerCity);
        checkIfEmpty(cityElement);
    }


    private void password(String customerPassword) {
        WebElement passwordElement = driver.findElement(By.id("passwd"));
        passwordElement.sendKeys(customerPassword);
        checkIfEmpty(passwordElement);
    }


    private void postcode(String customerPostcode) throws InterruptedException {
        WebElement zipElement = driver.findElement(By.id("postcode"));
        zipElement.sendKeys(customerPostcode);
        String expectedPostcode = zipElement.getText();
        String myPattern = "\\d{5}";
        if(!expectedPostcode.matches(myPattern)){
            System.out.println("Zip/Postal code invalid!");
        }
        checkIfEmpty(zipElement);
        Thread.sleep(5000L);
    }


    private void checkIfEmpty(WebElement element){
        if (element.getAttribute("value").isEmpty()){
            System.out.println("Empty " + element.getText());
        }
    }


    private void selectCheckbox(WebElement checkbox) {
        System.out.println("Checkbox " + checkbox.getAttribute("name") + " states before selecting it");
        //isDisplayed false ??????????????????????????????????????????????????????????????????????????????????????????????
        System.out.println("isDisplayed " + checkbox.isDisplayed());
        System.out.println("isEnabled " + checkbox.isEnabled());
        System.out.println("isSelected " + checkbox.isSelected());
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    private void deselectCheckbox(WebElement checkbox) {
        System.out.println("Checkbox " + checkbox.getAttribute("name") + " states before deselecting it");
        System.out.println("isDisplayed " + checkbox.isDisplayed());
        System.out.println("isEnabled " + checkbox.isEnabled());
        System.out.println("isSelected " + checkbox.isSelected());
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }
}

