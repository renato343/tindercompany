package org.renato;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Renato on 18/03/17.
 */
@Component
@Scope (value = "singleton")
public class Navigation {

    private static Navigation instance = null;

    private final int MIN_WIDTH = 600;
    private final int MIN_HEIGHT = 300;
    private boolean isCompany;

    public boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(boolean company) {
        isCompany = company;
    }

    //org.renato.controller.Navigation History
    private LinkedList<Scene> scenes = new LinkedList<Scene>();

    public Map<String, Initializable> getControllers() {
        return controllers;
    }

    public void setControllers(Map<String, Initializable> controllers) {
        this.controllers = controllers;
    }

    private Map<String, Initializable> controllers = new HashMap<>();
    private Stage stage;
    Parent root;


    //Constructor (is private so it can't be instantiated outside this class)
    private Navigation() {

    }

    public static synchronized Navigation getInstance() {
        if (instance == null) {
            instance = new Navigation();
        }
        return instance;
    }

    public Initializable getControllers(String name) {

        return controllers.get(name);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadScreen(String view) {

        try {

            FXMLLoader fxmlLoader;

            fxmlLoader = new FXMLLoader(getClass().getResource("/view/" + view + ".fxml"));

            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {

                    return controllers.get(param.getSimpleName());
                }
            });

            root = fxmlLoader.load();

            Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
            scenes.push(scene);

            setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setScene(Scene scene) {

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }

    public void back(){

        if(scenes.isEmpty()){
            return;
        }
        scenes.pop();
        setScene(scenes.peek());
    }

}
