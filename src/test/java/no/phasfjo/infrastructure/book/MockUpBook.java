package no.phasfjo.infrastructure.book;


import java.util.Calendar;

/**
 * Created by paulito on 06.06.2017.
 */
public class MockUpBook {


    public Calendar newCreationDate( ) {
        Calendar creationDate = Calendar.getInstance();
        return creationDate;
    }

    public Calendar newPaymentDate(Calendar creationDate) {
        Calendar newPaymentDate = Calendar.getInstance();
        int inThreeDays = 259200000;

        newPaymentDate.setTimeInMillis(creationDate.getTimeInMillis() + inThreeDays);
        return newPaymentDate;
    }

    public Calendar newDeliveryDate(Calendar paymentDate) {
        Calendar newDeliveryDate = Calendar.getInstance();
        int inSevenDays = 604800000;

        newDeliveryDate.setTimeInMillis(paymentDate.getTimeInMillis() + inSevenDays);
        return newDeliveryDate;
    }

    public boolean newShippingOrder(Calendar creationDate, Calendar paymentDate, Calendar deliveryDate) {

        return
                creationDate.getTimeInMillis() < paymentDate.getTimeInMillis() &&
                paymentDate.getTimeInMillis()  < deliveryDate.getTimeInMillis();
    }
}
