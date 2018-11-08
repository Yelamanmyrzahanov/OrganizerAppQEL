package kz.djunglestones.organizerappqel.Models;

import java.io.Serializable;

public class PromoCode implements Serializable {
    String promo_code_name;
    int percentage_discount;
    String ticket_name;

    public PromoCode(String promo_code_name, int percentage_discount, String ticket_name) {
        this.promo_code_name = promo_code_name;
        this.percentage_discount = percentage_discount;
        this.ticket_name = ticket_name;
    }

    public String getPromo_code_name() {
        return promo_code_name;
    }

    public void setPromo_code_name(String promo_code_name) {
        this.promo_code_name = promo_code_name;
    }

    public int getPercentage_discount() {
        return percentage_discount;
    }

    public void setPercentage_discount(int percentage_discount) {
        this.percentage_discount = percentage_discount;
    }

    public String getTicket_name() {
        return ticket_name;
    }

    public void setTicket_name(String ticket_name) {
        this.ticket_name = ticket_name;
    }
}
