package get_data;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import models.SuKienLichSu;
import models.NhanVatLichSu;
import models.DiaDiem;


public class getSuKienLichSu {
	public static void getHrefSuKienLichSu(WebDriver driver,String web,List<String> listHref) {
		driver.navigate().to(web);
		int i = 0;
		while(true) {
			
		try {
		List<WebElement> list=driver.findElements(By.xpath("/html/body/div/div[3]/div[2]/div[1]/div/div/div/div/div/a"));

        list.forEach(e -> {
        	String href = e.getAttribute("href");
        	listHref.add(href);
        });
    	i=i+1;
 	  	if(i==3)break;


 	  	WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'»')]"));
 	  	JavascriptExecutor executor = (JavascriptExecutor)driver;
 	  	executor.executeScript("arguments[0].click();", ele);
 	  	if(i==1) {
 	  		ele = driver.findElement(By.xpath("//a[contains(text(),'»')]"));
 	 	  	executor = (JavascriptExecutor)driver;
 	 	  	executor.executeScript("arguments[0].click();", ele);
 	  	}
    	}catch (Exception e) {
			break;
		}
        
		}
	}
	
	public static void getDataEvent(WebDriver driver,String href,List<SuKienLichSu> eventList) {
		driver.navigate().to(href);
		SuKienLichSu event = new SuKienLichSu(null, null, null, null, null, null, null);
		 WebElement title =driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div[1]/div[2]/div/div[1]/h3"));
		 
 		 
 		 // get Name
		 String fullName = title.getText().trim();
 		 String[] str = fullName.split("\\(");
 		 String name = str[0].trim();
// 		 System.out.println(name);
     	 event.setTen(name);
     	 
     	 // get Time
     	 if(str[0].trim() != fullName) {
     		String[] time = null;
     		String startTime = null;
     		String	endTime = null;
     		if(str[1].trim().charAt(0) == '-') 
     			{
     				if(str[1].trim().length() > 6) {
     					time = str[1].trim().split("\\)")[0].split(" - ");
     					startTime = time[0].trim();
     					endTime = time[1].trim();
     				}
     				else {
     					startTime = endTime = str[1].trim().split("\\)")[0].trim();
     				}
     			}
     		else {
     		if(str[1].contains("-")) {
     			time = str[1].trim().split("\\-");
     			startTime = time[0].trim();
     			endTime = time[1].split("\\)")[0].trim();
     		}
     		else {
     			startTime = endTime = str[1].split("\\)")[0].trim();
     		}
     		}
//     		System.out.println(startTime);
//     		System.out.println(endTime);
     		Year year = null;
     		if(startTime != null) { 
	        		year = Year.parse(startTime);
     		}
 			event.setNamBatDau(year);
 			if(endTime != null) { 
        		year = Year.parse(endTime);
 		}
			event.setNamKetThuc(year);
			
			
			//get description
			WebElement description = driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div[1]/div[3]/div/div[2]"));
        	String descript = description.getText().trim();
//        	System.out.println(descript);
        	event.setMoTa(descript);
        	
        	Boolean check = false;
        	// get related places
        	List<DiaDiem> listRelatedLocation = new ArrayList<>();
        	try {
				WebElement relatedLocation = driver.findElement(By.xpath("//h3[contains(text(),'Địa điểm liên quan')]"));
				
				if (relatedLocation != null){
				check = true;
			    List<WebElement> listLocation =driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/div[5]/div[1]/div/div[1]/div[2]/div[1]/a[1]/h3[1]"));
			    listLocation.forEach(e -> {
		        	DiaDiem pl = new DiaDiem();
			    	String stro = e.getText().trim();
			    	String place = stro.split("\\(")[0].trim();
//			    	System.out.println(place);
			    	pl.setTen(place);
			    	listRelatedLocation.add(pl);
			    });
			   event.setDiaDiemLienQuan(listRelatedLocation);
				}
			} catch (Exception e) {
//				System.out.println(e);
			}
        	
        	// get related figures
        	List<NhanVatLichSu> listRelatedFigures = new ArrayList<>();
        	try {
				WebElement relatedFigure = driver.findElement(By.xpath("//h3[contains(text(),'Nhân vật liên quan')]"));
				if(relatedFigure != null) {
					List<WebElement> listFigures;
					if(check) {
					 listFigures = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/div[6]/div[1]/div/div[1]/div[2]/div[1]/a[1]/h4[1]"));
					}
					else {
						listFigures = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/div[5]/div[1]/div/div[1]/div[2]/div[1]/a[1]/h4[1]"));
					}
			    listFigures.forEach(e -> {
		        	NhanVatLichSu figure = new NhanVatLichSu(null);
			    	String stro = e.getText().trim();
			    	String figuree = stro.split("\\(")[0].trim();
//			    	System.out.println(figuree);
			    	figure.setTen(figuree);
			    	listRelatedFigures.add(figure);
			    });
			   event.setNhanVatLienQuan(listRelatedFigures);
				}
			} catch (Exception e) {
//				System.out.println(e);
			}
        	eventList.add(event);
	}
	}
	
	public static void main(String[] args) throws InterruptedException {
			List<SuKienLichSu> eventList = new ArrayList<>();
			List<String> listHref= new ArrayList<String>();
		// TODO Auto-generated method stub
			
		    System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		    ChromeOptions options = new ChromeOptions();
		    options.setHeadless(true);
		    WebDriver driver = new ChromeDriver(options);
	        String url = "https://thuvienlichsu.com/su-kien";
	        
	        getHrefSuKienLichSu(driver, url, listHref);
	        
		    for(int i=0;i<listHref.size();i++) {    	
		    	getDataEvent(driver,listHref.get(i),eventList);
		    }
		    
		    driver.quit();
		    
		    for(int i = 0; i < eventList.size();i++) {
		    	System.out.print("Sự kiện " + (i+1) + ": ");
		    	SuKienLichSu e = eventList.get(i);
		    	System.out.println("Tên sự kiện: " + e.getTen());
		    	System.out.println("Thời điểm bắt đầu: " + e.getNamBatDau());
		    	System.out.println("Thời điểm kết thúc: " + e.getNamKetThuc());
		    	System.out.println("Mô tả: " + e.getMoTa());
		    	if(e.getDiaDiemLienQuan() != null) {
		    		System.out.print("Địa điểm liên quan: ");
		    		for(int j = 0; j < e.getDiaDiemLienQuan().size(); j++) {
		    			System.out.println(e.getDiaDiemLienQuan().get(j).getTen());
		    		}
		    	}
		    	
		    	if(e.getNhanVatLienQuan() != null) {
		    		System.out.print("Nhân vật liên quan: ");
		    		for(int j = 0; j < e.getNhanVatLienQuan().size(); j++) {
		    			System.out.println(e.getNhanVatLienQuan().get(j).getTen());
		    		}
		    	}
		    	System.out.println("\n");

		    }

	}

}