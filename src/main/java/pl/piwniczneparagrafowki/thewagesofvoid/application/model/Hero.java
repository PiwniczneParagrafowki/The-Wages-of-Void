package pl.piwniczneparagrafowki.thewagesofvoid.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * @Author arkadiusz.parafiniuk@gmail.com
 */
@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="sex")
    @NotNull
    private String sex;

    @Column(name="hp")
    @NotNull
    private int hp;

    @Column(name="strength")
    @NotNull
    private int strength;

    @Column(name="dexterity")
    @NotNull
    private int dexterity;

    @Column(name="intelligence")
    @NotNull
    private int intelligence;

    @Column(name="wisdom")
    @NotNull
    private int wisdom;

    @Column(name="charisma")
    @NotNull
    private int charisma;

    @Column(name="willpower")
    @NotNull
    private int willpower;

    @Column(name="stamina")
    @NotNull
    private int stamina;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "hero")
    private List<Item> item = new ArrayList<>();

    public Hero() {
        //
    }

    public Hero(String name, int hp, User user) {
        this.name = name;
        this.hp = hp;
        this.user = user;
    }

    public Hero(long id, String name, int hp, User user) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.user = user;
    }

    public Hero(long id, String name, String sex, int hp, int strength, int dexterity, int intelligence, int wisdom, int charisma, int willpower, int stamina, User user) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.hp = hp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.willpower = willpower;
        this.stamina = stamina;
        this.user = user;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getWillpower() {
        return willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", hp=" + hp +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                ", willpower=" + willpower +
                ", stamina=" + stamina +
                ", user=" + user +
                ", item=" + item +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hero hero = (Hero) o;

        return id == hero.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

