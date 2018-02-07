package pl.piwniczneparagrafowki.thewagesofvoid.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class created by Arkadiusz Parafiniuk
 * @Author arkadiusz.parafiniuk@gmail.com
 */
@Entity
@Table(name = "character_")
public class Character {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="health")
    @NotNull
    private int health;

    public Character() {
        //
    }

    public Character(long id, String name, int health) {
        this.id = id;
        this.name = name;
        this.health = health;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
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

