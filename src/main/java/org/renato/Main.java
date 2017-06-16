package org.renato;

import javafx.application.Application;
import javafx.stage.Stage;
import org.renato.persistence.hibernate.HibernateSessionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Renato on 18/03/17.
 */
public class Main extends Application {

    //updated version

    HibernateSessionManager hibernateSessionManager;


    @Override
    public void start(Stage primaryStage) throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springconfig/spring-config.xml");

        Navigation navigation = applicationContext.getBean(Navigation.class);

        hibernateSessionManager = applicationContext.getBean("hibernateSessionManager",HibernateSessionManager.class);

        navigation.setStage(primaryStage);
        navigation.loadScreen("first");

    }

    @Override
    public void stop() throws Exception {
        hibernateSessionManager.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
