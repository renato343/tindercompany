package org.renato.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.renato.Navigation;
import org.renato.model.pojos.Candidate;
import org.renato.model.pojos.Company;
import org.renato.model.pojos.Match;
import org.renato.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class LogedController implements Initializable {

    @Autowired
    private UserService userService;

    @Autowired
    private Navigation navigation;

    private List<Company> companysList;
    private List<Candidate> candidates;
    private List<Match> matches;
    private boolean isCompany;
    int iterator = -1;

    @Autowired
    public LogedController(UserService userService, Navigation navigation) {
        this.userService = userService;
        this.navigation = navigation;
    }

    public LogedController() {
    }

//    @FXML
//    private MenuItem logout;
//
//    @FXML
//    private MenuItem quit;
//
//    @FXML
//    private Label welcomelabel;
//
//    @FXML
//    private Button previousButton;
//
//    @FXML
//    private Button nextButton;
//
//    @FXML
//    private Button moreInfoButton;
//
//    @FXML
//    private Button matchButton;

    @FXML
    private Label textToShow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.isCompany = userService.getIsCompany();
        companysList = userService.getCompanies();
        candidates = userService.getCadets();
        matches = userService.getMatches();

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public boolean getisCompany() {
        return isCompany;
    }

    @Autowired
    public void setisCompany(boolean company) {
        isCompany = company;
    }

    public void previous(ActionEvent actionEvent) {

        iterator--;

        if (!isCompany) {

            if (iterator >= companysList.size()) {
                iterator = companysList.size() - 1;
            }

            if (iterator >= 0 && iterator < companysList.size()) {
                textToShow.setText(companysList.get(iterator).getMotto());

            } else {
                textToShow.setText("NO MORE ITEMS TO SHOW ");
                iterator = -1;
            }

        } else {

            if (iterator >= candidates.size()) {
                iterator = candidates.size() - 1;
            }

            if (iterator >= 0 && iterator < candidates.size()) {

                textToShow.setText(candidates.get(iterator).getMotto());

            } else {
                textToShow.setText("NO MORE ITEMS TO SHOW ");
                iterator = -1;
            }

        }

    }

    public void next(ActionEvent actionEvent) {

        iterator++;

        if (!isCompany) {

            if (iterator < 0) {
                iterator = 0;
            }

            if (iterator < companysList.size()) {

                textToShow.setText(companysList.get(iterator).getMotto());
            } else {

                textToShow.setText("NO MORE ITEMS TO SHOW ");

            }
        } else {

            if (iterator < 0) {
                iterator = 0;
            }

            if (iterator < candidates.size()) {

                textToShow.setText(candidates.get(iterator).getMotto());

            } else {
                textToShow.setText("NO MORE ITEMS TO SHOW ");

            }

        }
    }

    public void moreInfo(ActionEvent actionEvent) {

        if (iterator >= 0) {

            if (isCompany) {

                if (iterator < candidates.size()) {
                    textToShow.setText(candidates.get(iterator).getName());

                } else {
                    textToShow.setText("NOTHING TO SHOW");
                }

            } else {

                if (iterator < companysList.size()) {
                    textToShow.setText(companysList.get(iterator).getName());

                } else {
                    textToShow.setText("NOTHING TO SHOW");
                }
            }

        } else {
            textToShow.setText("NOTHING TO SHOW");
        }
    }

    public void match(ActionEvent actionEvent) {

        if (!isCompany) {

            Match match = userService.checkMatch(userService.getCandidateLogged(), companysList.get(iterator));

            if (match == null) {

                userService.match(userService.getCandidateLogged(), companysList.get(iterator));

            } else {

                if (match.isCompany_bol()) {
                    textToShow.setText("YOU'VE GOT A MATCH");

                } else {
                    userService.updateMatch(match);
                }
            }

        } else {


            Match match = userService.checkMatch(candidates.get(iterator), userService.getCompanyLogged());

            if (match == null) {
                userService.match(candidates.get(iterator), userService.getCompanyLogged());

            } else {

                if (match.isCandidate_bol()) {
                    textToShow.setText("YOU'VE GOT A MATCH WITH ");

                } else {
                    userService.match(candidates.get(iterator), userService.getCompanyLogged());

                }
            }
        }

    }

    public void logout(ActionEvent actionEvent) {
        iterator = -1;
        navigation.loadScreen("first");
    }

    public void quit(ActionEvent actionEvent) {
        System.exit(0);

    }
}
