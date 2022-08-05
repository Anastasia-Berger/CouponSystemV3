package com.jb.csv3;

import com.jb.csv3.utils.Titles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Csv3Application {

	public static void main(String[] args) {
		SpringApplication.run(Csv3Application.class, args);
		System.out.println("Spring IoC Container was loaded");
//		System.out.println(Titles.START);
	}

}
