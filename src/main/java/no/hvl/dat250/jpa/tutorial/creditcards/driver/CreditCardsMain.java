package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }

  }

  private static void createObjects(EntityManager em) {
    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);

    Customer customer = new Customer();
    customer.setName("Max Mustermann");
    customer.addAddress(address);
    em.persist(customer);
    address.addOwner(customer);
    em.persist(address);

    Pincode pincode = new Pincode();
    pincode.setPincode("123");
    pincode.setCount(1);
    em.persist(pincode);

    Bank bank = new Bank();
    bank.setName("Pengebank");
    em.persist(bank);


    CreditCard card1 = new CreditCard();
    card1.setNumber(12345);
    card1.setBalance(-5000);
    card1.setCreditLimit(-10000);
    card1.setPincode(pincode);
    card1.setBank(bank);
    em.persist(card1);
    customer.addCard(card1);

    CreditCard card2 = new CreditCard();
    card2.setNumber(123);
    card2.setBalance(1);
    card2.setCreditLimit(2000);
    card2.setPincode(pincode);
    card2.setBank(bank);
    em.persist(card2);
    customer.addCard(card2);

    em.persist(customer);

    bank.addCard(card1);
    bank.addCard(card2);
  }
}
