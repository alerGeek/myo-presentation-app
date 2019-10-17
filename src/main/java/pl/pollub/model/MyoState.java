package pl.pollub.model;

public enum MyoState {
  MYO_UNKNOWN("Brak informacji"),
  MYO_NOT_FOUND("Nie znaleziono urządzenia MYO"),
  MYO_FOUND("Znaleziono urządzenia MYO"),
  MYO_SEARCHING("Szukam dostępnych urządzeń MYO..."),
  MYO_CONNECTED("Podłączono urządzenie MYO"),
  MYO_DISCONNECTED("Odłączono urządzenie MYO"),
  MYO_WARMED_UP("Myo zostało rozgrzane");

  private String communicateText;


  MyoState(String communicateText) { this.communicateText = communicateText; }



  public String getCommunicateText() { return this.communicateText; }
}