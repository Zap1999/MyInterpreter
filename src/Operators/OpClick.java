package Operators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpClick extends Operator {

    public OpClick(String code) {
        super(code);
    }

    @Override
    public void exec(Interpretater inte) {
        code = code.trim();
        code = code.replace("\"", "");
        WebDriver driver = inte.getDriver();
        WebElement el = driver.findElement(By.id(code));
        el.click();
    }
}
