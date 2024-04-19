package ch16;

public class AnonymousSample {

  public static void main(String[] args) {
    AnonymousSample sample = new AnonymousSample();
    sample.setButtonListener();
  }

  public void setButtonListener() {
    MagicButton magicButton = new MagicButton();
    magicButton.setEventListener(new MagicButtonListener());
    magicButton.onClieckProcess();
  }

}
