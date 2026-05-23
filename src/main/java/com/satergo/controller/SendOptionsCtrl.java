package com.satergo.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import org.ergoplatform.appkit.Address;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SendOptionsCtrl implements Initializable {

	private final List<AddressData> addresses;
	private final List<Integer> selected;
	private final Address change;

	@FXML public FlowPane candidates;
	@FXML public ComboBox<AddressData> changeAddress;

	public record AddressData(int index, String name, Address value) {
		@Override
		public String toString() {
			return name.isBlank() ? "(#" + index + ")" : name;
		}
	}

	public SendOptionsCtrl(List<AddressData> addresses, List<Integer> selected, Address change) {
		this.addresses = addresses;
		this.selected = selected;
		this.change = change;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addresses.forEach(addressData -> {
			ToggleButton button = new ToggleButton(addressData.toString());
			button.setSelected(selected.contains(addressData.index()));
			button.setUserData(addressData.index());
			candidates.getChildren().add(button);
		});
		changeAddress.getItems().addAll(addresses);
		changeAddress.setValue(addresses.stream().filter(a -> a.value().equals(change)).findAny().orElseThrow());
	}
}
