package pl.pollub.model.factory;

public enum ModeType {
    DETAILS("details"),
    MOUSE("mouse"),
    PRESENTATION("presentation"),
    TUTORIAL("tutorial"),
    COMMUNICATE("communicate");

    private String lowerCase;

    ModeType(String lowerCase) {
        this.lowerCase = lowerCase;
    }

    public String toLower() {
        return lowerCase;
    }
}
