package kz.djunglestones.organizerappqel.Models;

public class PastEventsTickets {
    String eventName,date,time;
    int ticketsSold,ticketsTotal,ticketCost;

    public PastEventsTickets(String eventName, String date, String time, int ticketsSold, int ticketsTotal, int ticketCost) {
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.ticketsSold = ticketsSold;
        this.ticketsTotal = ticketsTotal;
        this.ticketCost = ticketCost;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public int getTicketsTotal() {
        return ticketsTotal;
    }

    public void setTicketsTotal(int ticketsTotal) {
        this.ticketsTotal = ticketsTotal;
    }

    public int getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(int ticketCost) {
        this.ticketCost = ticketCost;
    }
}
