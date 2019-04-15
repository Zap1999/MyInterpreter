import Operators.Interpretater;
import VarTypes.StringVar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        // file
        if (/*args[0] == "-f"*/true) {
            String fileName = "D:\\PE\\Java\\parser2\\src\\light.machine";

            try {
                BufferedReader fileReader =
                        new BufferedReader(
                                new FileReader(fileName));

                Interpretater interpretater = new Interpretater();
                while (true) {
                    String line = fileReader.readLine();
                    if (line == null) break;
                    if (!line.contains("{") && !line.contains("[")) {
                        interpretater.parse(line);
                    }else if (line.contains("{")){
                        ArrayList<String> lines = new ArrayList<>();
                        try {
                            do {
                                if (line == null) {
                                    break;
                                }
                                lines.add(line);
                                line = fileReader.readLine();
                            } while (!line.contains("}"));
                            lines.add(line);
                            interpretater.parse(lines);
                        }catch (NullPointerException e) {
                            System.err.println("Error: missing '}'.");
                        }
                    }else if(line.contains("[")) {
                        ArrayList<String> lines = new ArrayList<>();
                        try {
                            do {
                                if (line == null) {
                                    break;
                                }
                                lines.add(line);
                                line = fileReader.readLine();
                            } while (!line.contains("]"));
                            lines.add(line);
                            interpretater.parse(lines);
                        }catch (NullPointerException e) {
                            System.err.println("Error: missing ']'.");
                        }
                    }
                }
                interpretater.run();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // commands
        else if(/*args[0] == "-c"*/false) {

            Scanner s = new Scanner(System.in);
            Interpretater interpretater = new Interpretater();
            while(true) {
                String line = s.nextLine();
                interpretater.parse(line);
            }

        }
    }


}
