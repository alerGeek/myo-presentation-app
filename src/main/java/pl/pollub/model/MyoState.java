package pl.pollub.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum MyoState {
  MYO_UNKNOWN("Brak informacji o urządzeniu MYO"),
  MYO_NOT_FOUND("Nie znaleziono urządzenia MYO"),
  MYO_FOUND("Znaleziono urządzenia MYO"),
  MYO_SEARCHING("Szukam dostępnych urządzeń MYO..."),
  MYO_CONNECTED("Podłączono urządzenie MYO"),
  MYO_DISCONNECTED("Odłączono urządzenie MYO"),
  MYO_SYNCHRONIZED("Urządzenie jest zsynchronizowane"),
  MYO_UNSYNCHRONIZED("Wykonaj gest WAVE_OUT i przytrzymaj w celu synchronizacji urządzenia"),
  MYO_WARMED_UP("Myo zostało rozgrzane");

  private final StringProperty communicateText;


  MyoState(String communicateText) { this.communicateText = new SimpleStringProperty(communicateText); }


  public String getCommunicateText() {
    return communicateText.get();
  }

  public StringProperty communicateTextProperty() {
    return communicateText;
  }

  public void setCommunicateText(String communicateText) {
    this.communicateText.set(communicateText);
  }
}