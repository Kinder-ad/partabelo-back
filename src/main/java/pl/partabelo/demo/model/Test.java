package pl.partabelo.demo.model;

public class Test {
    private String trackName;
    private String userName;

    public Test(String trackName, String userName) {
        this.trackName = trackName;
        this.userName = userName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
