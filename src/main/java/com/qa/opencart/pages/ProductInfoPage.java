package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader= By.cssSelector("div#content h1");
	private By productImagesCount = By.cssSelector("div#content a.thumbnail");
	private By productMetaData = By.xpath("//div");
	private By productPricingData = By.xpath("");
	private Map<String, String> productMap;

	
	//2. create public const.. of the page
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	public String getProductHeader() {
		String header = eleUtil.doGetText(productHeader);
		System.out.println("Product header : " + header);
		return header;
	}
	
	public int getProductImageCount() {
		int imageCount = eleUtil.waitForVisibilityOfElemenetsLocated(productImagesCount, TimeUtil.DEFAULT_MEDIUM_TIME).size();
			System.out.println("Product Image Count : " + imageCount);
				return imageCount;
	}
	
	public Map<String,String> getProductInfoMap() {
		//productMap=new HashMap<String ,String>();
		//productMap = new LinkedHashMap<String, String>(); - given sequence
		productMap=new TreeMap<String, String>();//ordered
		
		
		productMap.put("productname", getProductHeader());
		productMap.put("productImagecount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductMetaPrice();
		return productMap;
	}
	
	public void getProductMetaData() {
		List<WebElement>	metaList=eleUtil.getElements(productMetaData);
		for(WebElement e: metaList) {
			String metaData=e.getText();
			String meta[]=metaData.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productMap.put(metaKey,metaValue);
		}
	}
	
	//Tax : $2,000
	public void getProductMetaPrice() {
		List<WebElement> priceList=eleUtil.getElements(productPricingData);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText();
		
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
	}
}
