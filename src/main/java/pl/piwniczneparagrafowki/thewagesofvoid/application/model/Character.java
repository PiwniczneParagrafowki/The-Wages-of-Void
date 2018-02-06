package pl.piwniczneparagrafowki.thewagesofvoid.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class created by Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Entity
@Table(name = "character_")
public class Character {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="health")
    @NotNull
    private String health;

    public Character() {
        //
    }

    public Character(int id, String name, String health) {
        this.id = id;
        this.name = name;
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health='" + health + '\'' +
                '}';
    }
}

