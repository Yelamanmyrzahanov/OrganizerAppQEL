package kz.djunglestones.organizerappqel.Models;

public class TicketsForChoosePromocode {
    String ticketName;
    boolean isFree, isChecked = false;

    public TicketsForChoosePromocode(String ticketName, boolean isFree) {
        this.ticketName = ticketName;
        this.isFree = isFree;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
