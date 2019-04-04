import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLInputElement;


public class webView extends Application{

    private WebView view1;
    private WebEngine engine;

    public void loadLink(String link) {
        engine.load(link);
    }

    public void enterInput(String id, String input) {
        setInputById(id, input);
    }

    public void pushButton(String id) {

    }


    @Override
    public void start(Stage stage) throws Exception {

        view1 = new WebView();
        engine = view1.getEngine();

        VBox root = new VBox();
        root.getChildren().addAll(view1);

        Scene scene1 = new Scene(root, 1400, 600);
        stage.setScene(scene1);

        stage.show();
    }

    void setInputById(String id, String val) {
        Document dom = engine.getDocument();
        HTMLInputElement input = (HTMLInputElement) dom.getElementById(id);
        input.setValue(val);
    }

}
