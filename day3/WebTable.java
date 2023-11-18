package week4.day3;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class WebTable {

	public static void main(String[] args) {
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://erail.in/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		WebElement from = driver.findElement(By.xpath("//input[@id='txtStationFrom']"));
		from.clear();
		from.sendKeys("MAS" + Keys.ENTER);
		action.keyDown(Keys.ENTER).perform();

		WebElement to = driver.findElement(By.xpath("//input[@id='txtStationTo']"));
		to.clear();
		to.sendKeys("MDU" + Keys.ENTER); 
		action.keyDown(Keys.ENTER).perform();

		//- Uncheck the "Sort on Date" checkbox.
		driver.findElement(By.xpath("//input[@id='chkSelectDateOnly']")).click();

		// - Retrieve the train names from the web table.

		List<WebElement> table = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']/tbody/tr/td[2]"));
		int size = table.size();
		System.out.println("Column size : "  + size);

		Set<String> trainNames= new TreeSet<String>();
		for (int i = 2; i <=table.size(); i++) 
		{
			String trainName = driver.findElement(By.xpath("//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']/tbody/tr["+i+"]/td[2]")).getText();
			trainNames.add(trainName);
			System.out.println(trainName);		

		}
		int size2 = trainNames.size();
		System.out.println(trainNames.size());
		if (size==size2)
		{
			System.out.println("There are duplicate train names in the web table.");
		}
		else
			System.out.println("No duplicate train names found in the web table.");
	}


}
