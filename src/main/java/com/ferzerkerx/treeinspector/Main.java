package com.ferzerkerx.treeinspector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.BuilderFactory;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final Injector injector = Guice.createInjector(new ModuleConfig());
    private final BuilderFactory builderFactory = new JavaFXBuilderFactory();

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = getFxmlLoader("view/main.fxml");
        Parent root = loader.load();


        primaryStage.setTitle("Tree Inspector");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        loader.getController();
    }

    private FXMLLoader getFxmlLoader(String viewName) {
        return new FXMLLoader(getClass().getResource(viewName), null, builderFactory, injector::getInstance);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
