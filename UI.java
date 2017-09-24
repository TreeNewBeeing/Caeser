import java.io.File;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UI extends Application {
	public static Stage stage;
	private static File fileA;
	private static File fileB;
	private static String error;
	private static boolean mode = true;
	private static boolean level = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void start(Stage stage) {
		this.stage = stage;
		prepare(stage);
	}

	public void prepare(Stage stage) {
		// UI
		GridPane pane = new GridPane();
		FileChooser fc = new FileChooser();
		fc.setTitle("choose a file");
		pane.setPadding(new Insets(10, 30, 20, 30));
		pane.setVgap(10);
		pane.setHgap(10);

		// Label
		Label space = new Label("Encoding and Decoding");
		Label fileA = new Label("Encode file:");
		Label fileB = new Label("Decode file:");
		Label mode = new Label("Mode:");
		Label level = new Label("Level:");

		// Button
		Button chooseA = new Button("choose file");
		Button chooseB = new Button("choose file");
		Button clear = new Button("Clear");
		Button submit = new Button("Submit");

		// TextField
		TextField fileAPath = new TextField();
		TextField fileBPath = new TextField();

		// ChoiceBox
		ChoiceBox select = new ChoiceBox();
		select.setItems(FXCollections.observableArrayList("Encoding", "Decoding"));

		// ToggleGroup
		ToggleGroup group1 = new ToggleGroup();
		ToggleGroup group2 = new ToggleGroup();

		// ToggleButton
		ToggleButton tb1 = new ToggleButton("essayEncoding");
		ToggleButton tb2 = new ToggleButton("hardEncoding");
		ToggleButton tb3 = new ToggleButton("essayDecoding");
		ToggleButton tb4 = new ToggleButton("hardDecoding");

		pane.add(space, 1, 0);
		pane.add(select, 1, 1);
		pane.add(fileA, 0, 2);
		pane.add(fileB, 0, 2);
		pane.add(mode, 0, 1);
		pane.add(level, 0, 3);
		pane.add(tb1, 1, 3);
		pane.add(tb2, 1, 3);
		pane.add(tb3, 1, 3);
		pane.add(tb4, 1, 3);
		pane.add(fileAPath, 1, 2);
		pane.add(fileBPath, 1, 2);
		pane.add(chooseA, 1, 2);
		pane.add(chooseB, 1, 2);
		pane.add(submit, 1, 4);
		pane.add(clear, 1, 4);

		// css
		space.setPadding(new Insets(10, 0, 10, 10));
		space.setFont(new Font(30));
		pane.setMinWidth(560);
		pane.setMargin(mode, new Insets(15, 0, 0, 0));
		pane.setMargin(select, new Insets(15, 0, 0, 0));
		pane.setMargin(level, new Insets(15, 0, 0, 0));
		pane.setMargin(clear, new Insets(30, 0, 0, 210));
		pane.setMargin(submit, new Insets(30, 0, 0, 60));
		pane.setMargin(fileA, new Insets(15, 0, 0, 0));
		pane.setMargin(fileAPath, new Insets(15, 0, 0, 0));
		pane.setMargin(chooseA, new Insets(15, 0, 0, 300));
		pane.setMargin(fileB, new Insets(15, 0, 0, 0));
		pane.setMargin(fileBPath, new Insets(15, 0, 0, 0));
		pane.setMargin(chooseB, new Insets(15, 0, 0, 300));
		pane.setMargin(tb1, new Insets(15, 0, 0, 0));
		pane.setMargin(tb2, new Insets(15, 0, 0, 130));
		pane.setMargin(tb3, new Insets(15, 0, 0, 0));
		pane.setMargin(tb4, new Insets(15, 0, 0, 130));
		fileAPath.setPromptText("Enter the path of encode file");
		fileAPath.setMaxWidth(300);
		fileBPath.setPromptText("Enter the path of decode file");
		fileBPath.setMaxWidth(300);
		tb1.setToggleGroup(group1);
		tb1.setMaxWidth(130);
		tb2.setToggleGroup(group1);
		tb2.setMaxWidth(130);
		tb3.setToggleGroup(group2);
		tb3.setMaxWidth(130);
		tb4.setToggleGroup(group2);
		tb4.setMaxWidth(130);
		select.setValue("Encoding");
		fileB.setVisible(false);
		fileBPath.setVisible(false);
		chooseB.setVisible(false);
		tb3.setVisible(false);
		tb4.setVisible(false);
		fileAPath.setText("");
		fileBPath.setText("");
		System.out.println(fileAPath.getText());
		tb1.setSelected(true);
		tb3.setSelected(true);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();

		// set Interactions
		select.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number newValue) {
				if (newValue.intValue() == 1) {
					fileB.setVisible(true);
					fileBPath.setVisible(true);
					chooseB.setVisible(true);
					tb3.setVisible(true);
					tb4.setVisible(true);
					fileA.setVisible(false);
					fileAPath.setVisible(false);
					chooseA.setVisible(false);
					tb1.setVisible(false);
					tb2.setVisible(false);
					UI.mode = false;
				} else {
					fileB.setVisible(false);
					fileBPath.setVisible(false);
					chooseB.setVisible(false);
					tb3.setVisible(false);
					tb4.setVisible(false);
					fileA.setVisible(true);
					fileAPath.setVisible(true);
					chooseA.setVisible(true);
					tb1.setVisible(true);
					tb2.setVisible(true);
					UI.mode = true;
				}
			}
		});
		chooseA.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				File file = UI.fileA;
				FileChooser fc = new FileChooser();
				fc.setTitle("choose the original file");
				UI.fileA = fc.showOpenDialog(stage);
				if (UI.fileA != null) {
					// if (checkFile(UI.fileA)) {
					fileAPath.setText(UI.fileA.getAbsolutePath());
					// }
				}
			}
		});
		chooseB.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				File file = UI.fileB;
				FileChooser fc = new FileChooser();
				fc.setTitle("choose the model file");
				UI.fileB = fc.showOpenDialog(stage);
				if (UI.fileB != null) {
					// if (checkFile(UI.fileB)) {
					fileBPath.setText(UI.fileB.getAbsolutePath());
					// }
				}
			}
		});
		tb1.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				UI.level = true;
			}
		});
		tb2.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				UI.level = false;
			}
		});
		tb3.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				UI.level = true;
			}
		});
		tb4.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				UI.level = false;
			}
		});
		clear.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				fileAPath.setText("");
				fileBPath.setText("");
			}
		});

		submit.setOnMouseClicked(new EventHandler() {
			public void handle(Event e) {
				UI.fileA = new File(fileAPath.getText());
				UI.fileB = new File(fileBPath.getText());
				if (UI.handle()) {
					alert(AlertType.INFORMATION, 
							"You have updated the information successfully!",
							"Thanks for using our service.", "Success");
				} else {
					alert(AlertType.ERROR, "Ooops, there is an error!", error, "Error");
				}
			}
		});
	}

	public static boolean checkFile(File file) {
		if (file.isDirectory()) {
			error = "File:" + file.getAbsolutePath() + " is a directory.";
			return false;
		}
		if (!file.exists()) {
			error = "File:" + file.getAbsolutePath() + " doesn't exist.";
			return false;
		}

		return true;
	}

	//alert
	public static void alert(AlertType alertType, 
			String header, String context, String title) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(context);
		alert.showAndWait();
	}

	
	//submit button 
	public static boolean handle() {
		try {
			if (mode) {
				if (level) {
					if (checkFile(fileA)) {
						new Caeser().encodeFile(
								fileA, new File(fileA.getParent() + "/EncodedFile.txt"));
					} else {
						return false;
					}
				} else {
					if (checkFile(fileA)) {
						Encode en = new Encode();
						Common rev = new Common();
						rev.createReverseMapping();
						en.encodeFile(fileA.getAbsolutePath(), 
								fileA.getParent() + "/BinaryFile.txt",
								fileA.getParent() + "/EncodedFile.txt", 5);
					} else {
						return false;
					}
				}
			} else {
				if (level) {
					if (checkFile(fileB)) {
						new Caeser().decodeFile(
								fileB, new File(fileB.getParent() + "/DecodedFile.txt"));
					} else {
						return false;
					}
				} else {
					if (checkFile(fileB)) {
						Decode de = new Decode();
						Common com = new Common();
						com.createReverseMapping();
						de.decodeFile(fileB.getAbsolutePath(), 
								fileB.getParent() + "/BinaryFile.txt",
								fileB.getParent() + "/DecodedFile.txt", 5);
					} else {
						return false;
					}
				}
			}
			return true;

		} catch (Exception e) {
			error = e.toString();
			return false;
		}
	}
}
