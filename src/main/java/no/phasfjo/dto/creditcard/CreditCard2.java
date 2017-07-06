package no.phasfjo.dto.creditcard;

import no.phasfjo.constraint.AllCardTypes;
import no.phasfjo.constraint.Ccv;
import no.phasfjo.constraint.CreditType;
import no.phasfjo.constraint.DateVal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static no.phasfjo.dto.creditcard.CreditCard2.GET_ALL_CREDITCARD_TWO;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
//@Table(name = "creditcard")
@NamedQuery(name = GET_ALL_CREDITCARD_TWO, query = "select c from CreditCard2 c")
@SequenceGenerator(name = "SEQ_ORDER", initialValue = 50)
public class CreditCard2 {

    public static final String GET_ALL_CREDITCARD_TWO = "Creditcard2.getAl";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER")
    private int id;

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    @AllCardTypes
    private String cardNumber;
    @NotNull
    @DateVal
    private Date expiryDate;
    @NotNull
    @Ccv
    private String cvvNumber;
    @Enumerated(EnumType.STRING)
    @CreditType
    private CreditcardType cardType;

    // ======================================
    // =            Constructors            =
    // ======================================

    public CreditCard2() {
    }

    public CreditCard2(String cardNumber, Date expiryDate, String cvvNumber, CreditcardType cardType) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvvNumber = cvvNumber;
        this.cardType = cardType;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public CreditcardType getCardType() {
        return cardType;
    }

    public void setCardType(CreditcardType cardType) {
        this.cardType = cardType;
    }


    // ======================================
    // =          To String                 =
    // ======================================


    @Override
    public String toString() {
        return "CreditCard{" +
                "id='" + id + '\'' +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", cvvNumber='" + cvvNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
