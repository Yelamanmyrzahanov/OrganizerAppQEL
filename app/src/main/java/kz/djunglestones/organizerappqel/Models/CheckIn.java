package kz.djunglestones.organizerappqel.Models;


public class CheckIn {
    String username, ticketType;
    int checkedIn=0;

    public CheckIn(String username, String ticketType) {
        this.username = username;
        this.ticketType = ticketType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(int checkedIn) {
        this.checkedIn = checkedIn;
    }
}
