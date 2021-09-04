package com.sonar.vishal;

import java.io.IOException;

import com.sonar.vishal.pos.util.POSConstant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MedicoPos extends Application {

	@Override
	@SuppressWarnings("exports")
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(POSConstant.MEDICO_POS_FXML_PATH));
		stage.setScene(new Scene(loader.load()));
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}