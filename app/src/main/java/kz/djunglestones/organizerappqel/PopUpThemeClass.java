package kz.djunglestones.organizerappqel;

public class PopUpThemeClass {
    String themeName;
    int image;

    public PopUpThemeClass(String themeName, int image) {
        this.themeName = themeName;
        this.image = image;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
