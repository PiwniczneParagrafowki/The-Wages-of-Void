package pl.piwniczneparagrafowki.thewagesofvoid.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="amount")
    @NotNull
    private int amount;

    @ManyToOne
    private Hero hero;

    public Item() {
        //
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int amount, Hero hero) {
        this.name = name;
        this.amount = amount;
        this.hero = hero;
    }

    public Item(long id, String name, int amount, Hero hero) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.hero = hero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", hero=" + hero +
                '}';
    }
}
