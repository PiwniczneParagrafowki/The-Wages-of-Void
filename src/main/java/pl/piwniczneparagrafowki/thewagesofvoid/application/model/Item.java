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
    private Character character;

    public Item() {
        //
    }

    public Item(String name, int amount, Character character) {
        this.name = name;
        this.amount = amount;
        this.character = character;
    }

    public Item(long id, String name, int amount, Character character) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.character = character;
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", character=" + character +
                '}';
    }
}
