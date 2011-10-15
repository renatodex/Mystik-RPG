package mystik;

import java.awt.Image;

public class Player {
      public String username;
      public String command;
      public int currentMap;
      public int x;
      public int y;
      public Image playerImage = null;

      public String getUsername() {
            return username;
      }

      public String getCommand() {
		  return command;
	  }

	  public void setCommand(String command) {
		  this.command = command;
	  }

      public void setUsername(String username) {
            this.username = username;
      }

      public int getX() {
            return x;
      }

      public Image getPlayerImage() {
            return playerImage;
      }

      public void setPlayerImage(Image playerImage) {
            this.playerImage = playerImage;
      }

      public int getMap() {
            return currentMap;
      }

      public void setMap(int currentMap) {
            this.currentMap = currentMap;
      }

      public void setX(int x) {
            this.x = x;
      }

      public int getY() {
            return y;
      }

      public void setY(int y) {
            this.y = y;
      }

}