package no.phasfjo.dto.creditcard;

import no.phasfjo.constraint.Amex;
import no.phasfjo.constraint.CardType;
import no.phasfjo.constraint.Ccv;
import no.phasfjo.constraint.DateVal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static no.phasfjo.dto.creditcard.CreditCard.GET_ALL_CREDITCARD_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
@NamedQuery(name = GET_ALL_CREDITCARD_ONE, query = "select c from CreditCard c")
@SequenceGenerator(name = "SEQ_ORDER", initialValue = 50)
public class CreditCard {

    public static final String GET_ALL_CREDITCARD_ONE = "Creditcard.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER")
    private int id;

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    @Amex
    //@BCGlobal @CarteBlanche @Discovery @DinersClub @InstaPayment @JCB @KoreanLocal @Laser @Maestro @MasterCard @Solo @SwitchCard @UnionPay @VisaCard @VisaMaster
    private String cardNumber;
    @NotNull
    @DateVal
    private Date expiryDate;
    @NotNull
    @Ccv
    private String cvvNumber;
    @NotNull
    @CardType
    //  @Enumerated(EnumType.STRING)
    private String cardType;

    // ======================================
    // =            Constructors            =
    // ======================================

    public CreditCard() {
    }

    public CreditCard(String cardNumber, Date expiryDate, String cvvNumber, String cardType) {
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


    // ======================================
    // =          To String                 =
    // ======================================


    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", cvvNumber='" + cvvNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
