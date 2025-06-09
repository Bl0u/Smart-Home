package ENUMS;

public enum Resolution {
    SD("780"),
    HD("1080"),
    FHD("1440"),
    UHD("2160"),
    ULTRA("4320");

    private final String resolution;

    Resolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDisplayName() {
        return resolution;
    }
}