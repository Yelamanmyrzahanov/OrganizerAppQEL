package kz.djunglestones.organizerappqel.Models;

public class SoldOutCircleTickets {
    String ticketType;
    int ticketsSold;
    int ticketTotal;

    public SoldOutCircleTickets(String ticketType, int ticketsSold, int ticketTotal) {
        this.ticketType = ticketType;
        this.ticketsSold = ticketsSold;
        this.ticketTotal = ticketTotal;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public int getTicketTotal() {
        return ticketTotal;
    }

    public void setTicketTotal(int ticketTotal) {
        this.ticketTotal = ticketTotal;
    }
}
