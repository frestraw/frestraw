package com.example.frestraw.group;

import com.example.frestraw.card.Card;
import com.example.frestraw.group.Group;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CardGroup {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    private Card card;

    @ManyToOne
    private Group group;

    public CardGroup() {
    }

    public CardGroup(Card card, Group group) {
        this.card = card;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public Card getCard() {
        return card;
    }

    public Group getGroup() {
        return group;
    }
}
