package Operators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpEnterInput extends Operator {


    public OpEnterInput(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {

        code = code.trim();
        String split[] = code.split(" ");
        String id = split[0];
        String input = split[1];
        id = id.replace("\"", "");
        input = input.replace("\"", "");

        WebDriver driver = inte.getDriver();
        WebElement el = driver.findElement(By.id(id));
        el.sendKeys(input);
    }
}
