package mystik;

public class NumberLoad {
	    public int currentID;
	    public int leftMap;
	    public int rightMap;
	    public int upMap;
	    public int downMap;

        public int getCurrentMap() { return currentID; }
        public int getLeftMap() { return leftMap; }
        public int getRightMap() { return rightMap; }
        public int getUpMap() { return upMap; }
        public int getDownMap() { return downMap; }

        public void setRightMap(int rightMap) { this.rightMap = rightMap; }
        public void setLeftMap(int leftMap) { this.leftMap = leftMap; }
        public void setUpMap(int upMap) { this.upMap = upMap; }
        public void setDownMap(int downMap) { this.downMap = downMap; }
        public void setMapID(int currentID) { this.currentID = currentID; }
	}