package mystik;

public class EntranceLoad {
	    public int tile_after;
	    public int x;
	    public int y;
	    public int item_req;
	    public int onMap;

	    public int getTileAfter() { return tile_after; }
	    public int getEntX() { return x; }
	    public int getEntY() { return y; }
	    public int getItemReq_ent() { return item_req; }
	    public int getEntMap() { return onMap; }

	    public void setTileAfter(int tile_after) { this.tile_after = tile_after; }
	    public void setEntX(int x) { this.x = x; }
	    public void setEntY(int y) { this.y = y; }
	    public void setItemReq_ent(int item_req) { this.item_req = item_req; }
	    public void setEntMap(int onMap) { this.onMap = onMap; }
	}