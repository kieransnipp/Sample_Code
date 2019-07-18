//Demo this one

package snipp.test.bav.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.annotations.*;

import org.testng.asserts.Assertion;

//import org.testng.AssertJUnit;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
//import org.junit.Test;  
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
//import org.testng.AssertJUnit;
//import org.testng.annotations.Test;

public class UAT_BavarianInnLoginUploadReciepts {

	public static Properties prop;

	// Exent 1
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	Exception exception = null;

	private static WebDriver driver = null;

	public UAT_BavarianInnLoginUploadReciepts() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/bav/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Pre conditions @Before
	@BeforeSuite // 1
	public void launchBrowser() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.out.println("Launching firefox");
			// browserName = ((Object) driver).Firefox("drivers/geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("ie")) {
			System.out.println("Launching Internet Explorer");
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");

			driver = new InternetExplorerDriver();

		} else if (browserName.equals("opera")) {

			System.out.println("Launching opera");
			System.setProperty("webdriver.opera.driver", "drivers/operadriver.exe");
			driver = new OperaDriver();

		}
		System.out.println("Maximize window, delete cookies");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(prop.getProperty("url"));

		WebDriverRunner.setWebDriver(driver);

		// Manage Window and cookies
	}

	@BeforeTest // 2
	public void beforeTest() {
		System.out.println("@Before test bit");

	}

	@BeforeClass
	public void setUp() {// Exent 2

		htmlReporter = new ExtentHtmlReporter("UAT_BavarianInnLoginUploadReciepts.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

//	@BeforeMethod // 4
//	public void beforeMethod() {
//		System.out.println("@Before method bit");
//
//	}

	// test cases, starting with @Test
	@Test(priority = 1) // 5
	public void loginToSite() {
		// Exent 3
		ExtentTest test = extent.createTest("loginToSite", "Upload reciept");
		System.out.println("Verify the title of the login page");
		String pageTitle = driver.getTitle();
		System.out.println("Title of the page is " + pageTitle);
		AssertJUnit.assertEquals(driver.getTitle().contentEquals("Home"), true);
		test.pass("Passed");
	}

	@Test(priority = 2) // 6
	public void enterDetails() {
		System.out.println("Login to site");
		ExtentTest test = extent.createTest("enterDetails", "Upload reciept");

		$(By.xpath("//*[@id=\"loginContainer\"]/*/*/input")).setValue("Michael.Ledwith@snipp.com");
		$(By.xpath("//*[@id=\"loginContainer\"]/form/div[2]/input")).setValue("Snipp123!");
		$(By.name("loginButton")).click();
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Home");
		test.pass("Passed");
	}

	@Test(priority = 3) // 7
	public void loginTitleTest() throws InterruptedException {
		ExtentTest test = extent.createTest("loginTitleTest", "Upload reciept");
		System.out.println("");
		System.out.println("**************** Start of testing ****************");

		String loginTitle = driver.getTitle();
		System.out.println("Title displayed is " + loginTitle);
		Thread.sleep(3000);

		// AssertJUnit.assertEquals(driver.getTitle().contentEquals("Details"), true);
		AssertJUnit.assertEquals(loginTitle, "Home");
		System.out.println("Title is correct and this line is printed");
		test.pass("Passed");
	}

	@Test(priority = 4)
	public void WelcomeMessage() {
		ExtentTest test = extent.createTest("Welcome", "Upload reciept");
		System.out.println("Check welcome message");
		String Hi = driver.findElement(By.xpath("//*[contains(text(), 'Hi Michael')]")).toString();
		System.out.println(Hi + " is displayed");
		String title = driver.getTitle();
		System.out.println("Title displayed is " + title);
		System.out.println("Welcome message is displayed correctly");
		AssertJUnit.assertEquals(title, "Details");
		test.pass("Passed");
	}

	@Test(priority = 5)
	public void uploadReciept() throws InterruptedException {
		ExtentTest test = extent.createTest("Upload a reciept ", "Upload reciept");
		Thread.sleep(2000);
		System.out.println("Receipt Up load page");
		// driver.findElement(By.linkText("Upload Receipt")).click();
		driver.findElement(By.xpath("//*[contains(text(), 'Upload Receipt')]")).click();
		// driver.findElement(By.xpath("//button[@class='btn btn-default btn-bavaria'
		// and @text() ='UPLOAD')]")).click();
		// driver.findElement(By.xpath("//*[contains(text(), 'Upload Receipt' and
		// @class='main-navigation-list']")).click();
		// driver.findElement(By.xpath("//*[contains(text(), 'Upload')]")).click();
		String title = driver.getTitle();
		System.out.println("Title displayed is " + title);

		AssertJUnit.assertEquals(title, "Upload Receipt");
		test.pass("Passed");
	}

	@Test(priority = 6)
	public void uploadButtomSelection() throws InterruptedException {
		ExtentTest test = extent.createTest("uploadButtomSelection ", "Upload reciept");
		Thread.sleep(2000);
		System.out.println("Select the Choose files button");

		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));

		uploadElement.sendKeys("src/testfiles/demo.jpg");
		// uploadElement.sendKeys("C:\\01SelenideWorkSpace\\CatalystLogin\\src\\files\\REST-101.pdf");
		driver.findElement(By.xpath("//*[contains(text(), 'Submit')]")).click();

		// Confirm upload
		String confirmationPage = driver.getPageSource();
		Boolean uploadSuccessful = confirmationPage
				.contains("Please allow up to 48 hours for processing and verification.");
		System.out.println("Document upload successful = " + uploadSuccessful);

		Assert.fail("This dont work yet");

		// AssertJUnit
		// .assertTrue(confirmationPage.contains("Please allow up to 48 hours for
		// processing and verification."));

	}

	@Test(priority = 7)
	// @Test(dependsOnMethods = "uploadButtomSelection")
	public void verifyUploadWorked() throws InterruptedException {
		ExtentTest test = extent.createTest("verifyUploadWorked ", "Upload reciept");
		Thread.sleep(10000);
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		System.out.println("The date is " + timeStamp);
		System.out.println("Refresh page here ");
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String timeUploaded = driver
				.findElement(By.xpath(
						"//*[@id=\"receiptPage\"]/div/div/div[4]/div/div/div/div/div/div/table/tbody/tr[1]/td[1]/span"))
				.getText();
		AssertJUnit.assertTrue(timeUploaded.contains(timeStamp));

		System.out.println("Upload is working today");
		Assert.fail("Fail");
	}

	@Test(priority = 8)
	// @Test(dependsOnMethods = "verifyUploadWorked")
	public void verifyUploadQUEUEDStatus() throws InterruptedException {
		ExtentTest test = extent.createTest("verifyUploadStatus ", "Upload reciept");
		String timeUploaded = driver.findElement(By.xpath(
				"//*[@id=\"receiptPage\"]/div/div/div[4]/div/div/div/div/div/div/table/tbody/tr[1]/td[2]/span[1]"))
				.getText();
		AssertJUnit.assertTrue(timeUploaded.contentEquals("QUEUED"));

		System.out.println("Upload is QUEUED");
		test.pass("QUEUED is displayed");

	}

	// To be completed
	@Test(priority = 9)
	// @Test(dependsOnMethods = "verifyUploadStatus")
	public void viewReciept() throws InterruptedException {
		ExtentTest test = extent.createTest("viewReciept ", "Upload reciept");
		System.out.println("Switch to the alert");
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		String pageSourcePopUp = driver.getPageSource();
		System.out.println("Pop up source is = " + pageSourcePopUp);
		// driver.close();

		// Switch back to original browser (first window)
		// driver.switchTo().window(winHandleBefore);
		// assertTrue(pageSourcePopUp
		// .contains("Your receipt is queued for processing, this area will be updated
		// once it gets processed"));
		// Thread.sleep(1000);
		// System.out.println("Reciept upload confirmed");
		
		// To do
		test.fail("This part needs to be fixed before the test completed assertion can be completed");
		Assert.fail("Failed viewReciept does not work????");
	} // End viewReciept

	@Test(priority = 10)
	public void logOutOfSite() throws InterruptedException {
		// Select myProfile = new Select (driver.findElement(By.xpath("//a[text()='My
		// Profile']")));
		// Force logout Log out
		open("http://bavarianinn.snipp.ie/umbraco/Surface/LoginSurface/Logout");

	}

	@AfterTest
	public void tearDownTest() {
		driver.close();
		driver.quit();
		System.out.println("Test completed successfully");
	}

	@AfterSuite // Exent 5
	public void tearDown() {
		extent.flush();

	}

	// -------------------------------------
	//
	// @Test
	// public void assertEquals() {
	// Assert.assertEquals("This assertion will pass", "This assertion will pass");
	// System.out.println("This line is executed because assertEquals " + "passed
	// since both the strings are same");
	//
	// }
	//
	// @Test
	// private void anotherAssert() {
	// Assert.assertEquals("KKThis assertion will not pass", "KKThis assertion will
	// not pass");
	// System.out.println("This line is executed because assertEquals " + "passed
	// since both the strings are same");
	//
	// }
	//
	// @Test
	// public void assertNotEquals() {
	// Assert.assertNotEquals("This assertion will pass", "Since the " + "expected
	// and actual result do not match");
	// System.out.println("This line is executed because assertNotEquals" + "
	// assertion pass for the given situation");
	// }
	//
	// @Test
	// public void assertTrue() {
	// Assert.assertTrue(3 < 5);
	// System.out.println(
	// "This line will be executed as assertTrue will" + " pass because the
	// 3<5(which will return true)");
	// }
	//
	// @Test
	// public void assertFalse() {
	// Assert.assertFalse(3 > 5);
	// System.out.println("This line is executed because assertFalse"
	// + "assertion passes as the given condition will return false");
	// }
	//
	//// @Test
	//// public void assertNull() {
	//// Assert.assertNull(null);
	//// System.out.println("Since we we set null in the condition, the assertion "
	// + "assertNull will pass");
	//// }
	//
	// @Test
	// public void assertNotNull() {
	// Assert.assertNotNull("This assertion will pass because this " + "string don't
	// returns a null value");
	// System.out.println("This line is executed because assertNotNull have have
	// passed");
	// }

} // End LoginTestAssertions
