package ENUMS;

public enum Areas {
    LIVING_ROOM("Living Room"),
    KITCHEN("Kitchen"),
    BEDROOM("Bedroom"),
    BATHROOM("Bathroom"),
    HOME_OFFICE("Home Office") ;

    private final String displayName;

    Areas(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}