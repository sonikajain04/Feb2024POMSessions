package com.qa.opencart.tests;

import org.openqa.selenium.By;

public class DemoPage {
	By demo = By.id("demo");
	By cart = By.id("cart");
	
	public void getDemo() {
		System.out.println("demo");
		System.out.println("demo details");
		System.out.println("demo ngrok server setup updated 29072024");
	}
	
	public void getCart() {
		System.out.println("cart");
		System.out.println("cart details");
	}

}
