package com.cuke.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(
		
		
		plugin= {"pretty", "html:target/cucumber"},
		features="src/test/resources/features",
		glue= {"steps"},
		tags="@Test",
		dryRun=false
		
		)


public class Runner {
	
	

}
