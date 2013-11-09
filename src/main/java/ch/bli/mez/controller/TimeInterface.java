package ch.bli.mez.controller;

/*
 * Dieses Interface zeigt an was das Time-Gui alles implemetieren muss. Die Eingabe sowie die Liste sind genau das gleiche.
 */
public interface TimeInterface {

  public void showSuccess();

  public void showError();

  public void showDateError();

  public void showWorktimeError();

  public void showPositionError();

  public void showMissionError();

  public String getDate();

  public String getPosition();

  public String getMission();

  public String getWorktime();

  public void setDate(String date);

  public void setPosition(String position);

  public void setMission(String mission);

  public void setWorktime(String worktime);

  public void showErrorMessage(String message);
}
