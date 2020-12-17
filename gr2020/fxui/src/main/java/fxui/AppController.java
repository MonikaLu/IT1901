package fxui;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import moodtracker.core.Day;
import moodtracker.core.Days;
import moodtracker.core.Statistics;

public class AppController {
  @FXML
  Pane statisticsModal;
  @FXML
  Button closeModal;
  @FXML
  TextField dateInput;
  @FXML
  ComboBox<LocalDate> fromDate;
  @FXML
  ComboBox<LocalDate> toDate;
  @FXML
  Slider moodSlider;
  @FXML
  Slider sleepSlider;
  @FXML
  Slider motivationSlider;
  @FXML
  Button registerNew;
  @FXML
  Accordion moodReg;
  @FXML
  Pane settings;
  @FXML
  Button editBtn;
  @FXML
  Button deleteBtn;
  @FXML
  Button doneBtn;
  @FXML
  Button openSettingsBtn;
  @FXML
  Pane statisticsPane;
  @FXML
  CheckBox allRegCheckBox;
  @FXML
  Button showStatsBtn;
  @FXML
  Text modalText;
  @FXML
  Pane moodtrackerModal;
  @FXML
  Text modalTextReg;
  @FXML
  Button applyEdit;
  @FXML
  Button cancelEdit;
  @FXML
  Tab statisticsTab;
  @FXML
  TabPane tabPane;

  private AccessInterface serverConnection;

  /**
   * Tries to connect to rest-server.
   * If server is Offline then stores data temporarily.
   * Sets up UI with data from the rest-server.
   */

  @FXML
  public void initialize() {
    try {
      serverConnection = new RemoteAccess(new URI("http://localhost:8080"));
      serverConnection.getDays();
    } catch (URISyntaxException | RuntimeException e) {
      serverConnection = new LocalAccess();
    }

    updateRegistrations();
    settings.setVisible(false);
    allRegCheckBox.setSelected(false);
    applyEdit.setVisible(false);
    cancelEdit.setVisible(false);

    statisticsModal.setVisible(false);
    moodtrackerModal.setVisible(false);

    fromDate.getSelectionModel().select(1);
    toDate.getSelectionModel().select(0);

    allRegCheckBox.selectedProperty().addListener(
        (ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal)
            -> allRegistrationsChecked(oldVal));
  }

  /**
   * Gets Days from restserver and updates the UI to match the returned Days object.
   */
  public void updateRegistrations() {
    ObservableList<TitledPane> allPanes = moodReg.getPanes();
    moodReg.getPanes().removeAll(allPanes);

    toDate.getItems().clear();
    fromDate.getItems().clear();
    for (Day day : getDaysObject().getDays()) {
      toDate.getItems().addAll(day.getDate());
      fromDate.getItems().add(day.getDate());

      int i = 0;

      GridPane grid = new GridPane();
      grid.setHgap(8);
      grid.setVgap(10);

      for (Map.Entry<String, Integer> daysMap : day.getStats().entrySet()) {

        Label label = new Label(daysMap.getKey() + ": " + daysMap.getValue());

        ProgressBar prB = new ProgressBar(daysMap.getValue() / 5.0);

        grid.add(label, 0, i);
        grid.add(prB, 1, i);

        i++;
      }

      TitledPane pane = new TitledPane(day.getDate().toString(), grid);
      moodReg.getPanes().add(pane);
    }
  }


  /**
   * adds the input as a Day object in the rest-server.
   */
  @FXML
  public void newMoodRegistration() {
    try {
      serverConnection.addDay(new Day(getDateFromInput(), createMapFromInput()));
    } catch (DateTimeParseException e) {
      toggleMTModal();
    }

    updateRegistrations();

    dateInput.setText("");
    moodSlider.setValue(1.0);
    motivationSlider.setValue(1.0);
    sleepSlider.setValue(1.0);


  }

  /**
   * Deletes the Day that the active Pane-element represents in the rest-server.
   * If no Pane-element is selected then shows an error message to the user.
   */
  @FXML
  public void deleteRegistration() {
    try {
      TitledPane paneToDelete = getActivePane();
      Day dayToRemove = getDayFromPane(paneToDelete);
      if (serverConnection.deleteDay(dayToRemove) != null) {
        moodReg.getPanes().remove(paneToDelete);
        fromDate.getItems().remove(dayToRemove.getDate());
        toDate.getItems().remove(dayToRemove.getDate());
      }

    } catch (NullPointerException e) {
      toggleMTModal();
      toggleSettings();
    }
  }

  private void allRegistrationsChecked(Boolean oldv) {
    if (oldv == true) {
      fromDate.setDisable(false);
      toDate.setDisable(false);
    } else {
      fromDate.getSelectionModel().select(moodReg.getPanes().size() - 1);
      toDate.getSelectionModel().select(0);
      fromDate.setDisable(true);
      toDate.setDisable(true);
    }
  }

  /**
   * Gets the selected dates from the UI and show the average stats of all Day-object between the
   selected dates.
   */

  @FXML
  public void showStatsforDays() {
    if (fromDate.getSelectionModel().getSelectedItem() == null
        || toDate.getSelectionModel().getSelectedItem() == null) {

      toggleStatsModal();
      modalText.setText("You have to choose two dates.");
    } else {
      LocalDate startDate =
          (LocalDate) fromDate.getSelectionModel().getSelectedItem();
      LocalDate endDate =
          (LocalDate) toDate.getSelectionModel().getSelectedItem();
      showStats(startDate, endDate);
    }
  }

  private void showStats(LocalDate startDate, LocalDate endDate) {
    if (endDate.isAfter(startDate)) {
      statisticsPane.getChildren().clear();

      int i = 0;

      GridPane grid = new GridPane();
      grid.setHgap(8);
      grid.setVgap(10);
      DecimalFormat df = new DecimalFormat("##.##");

      for (Map.Entry<String, Double> statisticsMap : Statistics
          .calculateAverageOverPeriod(getDaysObject(), startDate, endDate).entrySet()) {

        Label label = new Label(statisticsMap.getKey() + ": "
            + df.format(statisticsMap.getValue()));

        ProgressBar prB = new ProgressBar(statisticsMap.getValue() / 5.0);

        grid.add(label, 0, i);
        grid.add(prB, 1, i);

        i++;
      }
      statisticsPane.getChildren().add(grid);
    } else {
      toggleStatsModal();
      modalText.setText("The start date has to be after the end date.");


    }
  }

  /**
   * Enter edit mode and select the active Pane as the date to edit.
   */
  @FXML
  public void editRegistration() {
    toggleSettings();
    try {
      dateInput.setText(getActivePane().getText());
      toggleEditMode();
      moodSlider.requestFocus();
    } catch (NullPointerException e) {
      //TODO: show modal with error: "you have to select a registration"
      toggleMTModal();
    }
  }

  /**
   * Sends a PUT request to update the selected day with the new stats the rest-server
   and ends edit mode in UI.
   */
  @FXML
  public void updateEditedRegistration() {
    Day dayToEdit = getDay(getDateFromInput());
    dayToEdit.setStats(createMapFromInput());

    serverConnection.updateDay(dayToEdit);
    updateRegistrations();

    cancelEdit();
  }

  /**
   * Closes edit mode.
   */
  @FXML
  public void cancelEdit() {
    dateInput.clear();
    moodSlider.setValue(1.0);
    motivationSlider.setValue(1.0);
    sleepSlider.setValue(1.0);
    toggleEditMode();
  }

  /**
   * Toggles the popup for error message.
   */

  @FXML
  public void toggleMTModal() {
    boolean show = moodtrackerModal.isVisible();
    moodtrackerModal.setVisible(!show);
    openSettingsBtn.setDisable(!show);
    registerNew.setDisable(!show);
  }

  /**
   * Toggles the popup for error message.
   */
  @FXML
  public void toggleStatsModal() {
    boolean show = statisticsModal.isVisible();
    statisticsModal.setVisible(!show);
    showStatsBtn.setDisable(!show);
  }

  @FXML
  public void toggleSettings() {
    boolean show = settings.isVisible();
    settings.setVisible(!show);
  }

  private TitledPane getActivePane() {
    return moodReg.getPanes().stream().filter(tp -> tp.isExpanded()).findFirst().orElseGet(null);
  }

  private Day getDayFromPane(TitledPane pane) {
    return getDay(LocalDate.parse(pane.getText()));
  }

  private void toggleEditMode() {
    Boolean boo = registerNew.isVisible();
    registerNew.setVisible(!boo);

    applyEdit.setVisible(boo);
    cancelEdit.setVisible(boo);
    dateInput.setDisable(boo);
  }

  private Map<String, Integer> createMapFromInput() {
    Map<String, Integer> stats = new HashMap<String, Integer>();

    stats.put("Mood", (int) moodSlider.getValue());
    stats.put("Motivation", (int) motivationSlider.getValue());
    stats.put("Sleep", (int) sleepSlider.getValue());

    return stats;
  }

  private LocalDate getDateFromInput() throws DateTimeParseException {
    return LocalDate.parse(dateInput.getText());
  }

  private Day getDay(LocalDate date) {
    return serverConnection.getDay(date);
  }

  private Days getDaysObject() {
    return serverConnection.getDays();
  }

}
