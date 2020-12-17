package fxui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobotException;
import org.testfx.framework.junit5.ApplicationTest;

public class AppControllerTest extends ApplicationTest {

  final String dateInput = "#dateInput";
  final String moodDropDown = "#moodDropDown";
  final String registerNew = "#registerNew";
  final String moodRegistrations = "#moodRegistrations";
  final String moodSlider = "#moodSlider";
  final String moodReg = "#moodReg";
  final String editBtn = "#editBtn";
  final String applyEdit = "#applyEdit";
  final String tabPane = "#tabPane";
  final String openSettingsBtn = "#openSettingsBtn";
  final String deleteBtn = "#deleteBtn";
  final String moodtrackerModal = "#moodtrackerModal";
  final String closeModalReg = "#closeModalReg";
  final String allRegCheckBox = "#allRegCheckBox";
  final String showStatsBtn = "#showStatsBtn";
  final String statisticsPane = "#statisticsPane";
  final String statisticsModal = "#statisticsModal";
  final String closeModal = "#closeModal";
  final String statisticsTab = "#statisticsTab";

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader fxmlLoader =
        new FXMLLoader(getClass().getResource("FxApp.fxml"));
    final Parent root = fxmlLoader.load();
    final Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  /* Metode som får tak i javaFx elementer */
  public <T extends Node> T find(final String query)
      throws NoSuchElementException {
    return (T) lookup(query).query();
  }


  @Test
  public void clickOnWrongElement() {
    Assertions.assertThrows(FxRobotException.class, () -> clickOn("#noButton"));
  }


  @Test
  public void registrationsTests() {
    //Finds element queries
    Button openSettingsBtnQ = (Button) find(openSettingsBtn);
    Button deleteBtnQ = (Button) find(deleteBtn);
    Pane moodtrackerModalQ = (Pane) find(moodtrackerModal);
    Button closeModalRegQ = (Button) find(closeModalReg);
    Button editBtnQ = find(editBtn);
    Button applyEditQ = find(applyEdit);
    TextField dateInputQuery = (TextField) find(dateInput);
    Button registerNewQuery = (Button) find(registerNew);
    Accordion moodRegT = (Accordion) find(moodReg);


    //TESTING ADDING NEW REGISTRATION:

    clickOn(dateInputQuery);

    int randomDay = ThreadLocalRandom.current().nextInt(1, 30);
    int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);
    int randomYear = ThreadLocalRandom.current().nextInt(1500, 2020);

    String randomDayString =
        randomDay < 10 ? "0" + randomDay : String.valueOf(randomDay);
    String randomMonthString =
        randomMonth < 10 ? "0" + randomMonth : String.valueOf(randomMonth);

    String randomDate =
        (randomYear + "-" + randomMonthString + "-" + randomDayString);

    write(randomDate);
    clickOn(registerNewQuery);
    sleep(1000);

    //ObservableList of all TitledPanes in Accordion
    ObservableList<TitledPane> allTitledPanes = moodRegT.getPanes();

    //Tries to find the TitledPane to date we just registered
    TitledPane addedDay = allTitledPanes.stream()
        .filter(a -> a.getText()
            .equals(randomDate))
        .findFirst()
        .orElse(null);

    assertNotNull(addedDay);

    //TESTS EDITING:

    clickOn(openSettingsBtnQ);
    clickOn(editBtnQ);

    assertTrue(moodtrackerModalQ.isVisible());

    clickOn(closeModalRegQ);
    clickOn(openSettingsBtnQ);
    clickOn(addedDay);
    clickOn(editBtnQ);
    clickOn(applyEditQ);


    //TESTS DELETION:

    clickOn(openSettingsBtnQ);
    clickOn(deleteBtnQ);

    assertTrue(moodtrackerModalQ.isVisible());

    clickOn(closeModalRegQ);
    clickOn(openSettingsBtnQ);

    TitledPane paneToDel = allTitledPanes.stream()
        .filter(a -> a.getText()
            .equals(randomDate))
        .findFirst()
        .orElse(null);

    clickOn(paneToDel);
    clickOn(deleteBtnQ);

    TitledPane addedDayAfter = allTitledPanes.stream()
        .filter(a -> a.getText()
            .equals(randomDate))
        .findFirst()
        .orElse(null);

    assertNull(addedDayAfter);
  }


  @Test
  public void showStatsForDaysTest() {
    TabPane tabPaneQ = (TabPane) find(tabPane);
    tabPaneQ.getSelectionModel().selectNext();

    //Finds elements
    CheckBox allRegCheckBoxQ = (CheckBox) find(allRegCheckBox);
    Button showStatsBtnQ = (Button) find(showStatsBtn);
    Pane showStatsPaneQ = (Pane) find(statisticsPane);

    clickOn(showStatsBtnQ);
    assertTrue(showStatsPaneQ.getChildren() != null);
    clickOn(allRegCheckBoxQ);
    assertTrue(showStatsPaneQ.getChildren() != null);
  }


}
