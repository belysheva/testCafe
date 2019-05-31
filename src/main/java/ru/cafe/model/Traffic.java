package ru.cafe.model;

import java.time.LocalDate;



public class Traffic extends AbstractBaseEntity {

    private int cafeId;
    private int amount;
    private LocalDate date;


    public Traffic(int id, int cafeId, int amount, LocalDate date) {
        super(id);
        this.cafeId = cafeId;
        this.amount = amount;
        this.date = date;
    }

    public int getCafeId() {
        return cafeId;
    }
    public int getAmount() {
        return amount;
    }
    public LocalDate getDate() { return date; }

    //TODO @Override
    // toString
    //  isNew

}
