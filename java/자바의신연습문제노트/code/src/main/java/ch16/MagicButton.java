package ch16;

public class MagicButton {

  public MagicButton() {

  }

  private EventListener eventListener;

  public void setEventListener(EventListener eventListener) {
    this.eventListener = eventListener;
  }

  public void onClieckProcess() {
    if (eventListener != null) {
      eventListener.onClick();
    }
  }
}
