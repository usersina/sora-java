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
        // Run javascript code to load the PDF Viewer with a file instead of google
        // @see https://github.com/mozilla/pdf.js/blob/master/examples/node/getinfo.js
        // engine.load("https://www.africau.edu/images/default/sample.pdf");
        // engine.executeScript("window.location = https://www.google.com");
    }
}
