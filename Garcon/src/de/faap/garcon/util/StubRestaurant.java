package de.faap.garcon.util;

public class StubRestaurant implements IRestaurant {

  @Override
  public String getName() {
    return "Der Wirt am Platz";
  }

  @Override
  public String getShortDescription() {
    return "Kleines Wirtshaus mit traditionellen Speisen und einheimischem Flair.";
  }

  @Override
  public String getDistance() {
    return "13,37 km";
  }

  @Override
  public long getGlobalId() {
    return 0;
  }

}
