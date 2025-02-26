public class Tile {
    private String value;
    private boolean isShowing;
    private boolean isMatched;

    // Constructor
    public Tile(String value) {
        this.value = value;
        this.isShowing = false;
        this.isMatched = false;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Check if tile is showing
    public boolean isShowing() {
        return isShowing;
    }

    // Check if tile is matched
    public boolean isMatched() {
        return isMatched;
    }

    // Show the tile
    public void showValue() {
        isShowing = true;
    }

    // Hide the tile
    public void hide() {
        if (!isMatched) {
            isShowing = false;
        }
    }

    // Mark tile as matched
    public void foundMatch() {
        isMatched = true;
    }
}
