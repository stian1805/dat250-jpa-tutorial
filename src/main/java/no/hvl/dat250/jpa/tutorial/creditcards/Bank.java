package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bank")
    private Collection<CreditCard> cards = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<CreditCard> getOwnedCards() {
        return new HashSet<>(this.cards);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCards(Collection<CreditCard> cards) {
        this.cards = cards;
    }

    public void addCard(CreditCard card) {
        cards.add(card);
    }
}
