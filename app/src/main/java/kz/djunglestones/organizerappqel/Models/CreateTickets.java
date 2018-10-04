package kz.djunglestones.organizerappqel.Models;

public class CreateTickets {
    String ticketType;
    int soldOutAmount,totalTicketAmount,price;

    public CreateTickets(String ticketType, int soldOutAmount, int totalTicketAmount, int price) {
        this.ticketType = ticketType;
        this.soldOutAmount = soldOutAmount;
        this.totalTicketAmount = totalTicketAmount;
        this.price = price;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getSoldOutAmount() {
        return soldOutAmount;
    }

    public void setSoldOutAmount(int soldOutAmount) {
        this.soldOutAmount = soldOutAmount;
    }

    public int getTotalTicketAmount() {
        return totalTicketAmount;
    }

    public void setTotalTicketAmount(int totalTicketAmount) {
        this.totalTicketAmount = totalTicketAmount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
