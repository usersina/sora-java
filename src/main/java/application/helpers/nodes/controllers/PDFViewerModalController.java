package application.helpers.nodes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.fxml.Initializable;

public class PDFViewerModalController implements Initializable {
    @FXML
    private WebView webView;

    private WebEngine engine;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        engine = webView.getEngine();
        System.out.println("Loading the PDF Viewer...");
        engine.load("https://www.google.com");
        // engine.load("https://www.africau.edu/images/default/sample.pdf");
    }
}
