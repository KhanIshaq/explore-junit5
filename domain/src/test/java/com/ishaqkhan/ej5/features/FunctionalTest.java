package com.ishaqkhan.ej5.features;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		strict = true,
		plugin = {"pretty"},
		features = {"classpath:features"})
public class FunctionalTest {

}
