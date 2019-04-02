package cn.tjucic.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestStuInfo {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	class Student {
		public String id;
		public String pwd;
		public String name;
		public String url;

		public Student() {
		}
	}

	@Before
	public void setUp() throws Exception {
		String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		baseUrl = "http://121.193.130.195:8800/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testStuInfo() throws Exception {
		Student[] stu = new Student[143];
		for (int i = 0; i < 143; i++) {
			stu[i] = new Student();
		}
		String excel = "D:/软件测试名单.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(excel)));
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell id = row.getCell(1);
			XSSFCell name = row.getCell(2);
			XSSFCell url = row.getCell(3);
			id.setCellType(CellType.STRING);
			String pwd = id.toString().substring(4);
			stu[i - 2].id = id.toString();
			stu[i - 2].pwd = pwd;
			stu[i - 2].name = name.toString();
			stu[i - 2].url = url.toString();
		}

		driver.get(baseUrl + "/");
		for (int k = 0; k < 143; k++) {
			driver.findElement(By.name("id")).clear();
			driver.findElement(By.name("id")).sendKeys(stu[k].id);
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys(stu[k].pwd);
			driver.findElement(By.id("btn_login")).click();
			assertEquals(stu[k].id, driver.findElement(By.id("student-id")).getText());
			assertEquals(stu[k].name, driver.findElement(By.id("student-name")).getText());
			assertEquals(stu[k].url, driver.findElement(By.id("student-git")).getText());
			driver.findElement(By.id("btn_logout")).click();
			driver.findElement(By.id("btn_return")).click();
		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		// if (!"".equals(verificationErrorString)) {
		// fail(verificationErrorString);
		// }
	}

}
