import org.example.Assignment.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SAP_BasedInvoiceSenderTest {

    @Test
    public void testWhenLowInvoicesSent() throws FailToSendSAPInvoiceException {

                FilterInvoice Fi = mock(FilterInvoice.class);
                SAP sap = mock(SAP.class);
                SAP_BasedInvoiceSender invoiceSer = new SAP_BasedInvoiceSender(Fi, sap);

                Invoice invoice1 = new Invoice("Ben", 100);
                Invoice invoice2 = new Invoice("Steve", 150);
                List<Invoice> invoices = Arrays.asList(invoice1, invoice2);
                when(Fi.lowValueInvoices()).thenReturn(invoices);

                invoiceSer.sendLowValuedInvoices();

                verify(sap, times(1));


    }
    @Test
    public void testWhenNoInvoices() throws FailToSendSAPInvoiceException {

                FilterInvoice filter = mock(FilterInvoice.class);
                SAP sap = mock(SAP.class);
                SAP_BasedInvoiceSender invoiceService = new SAP_BasedInvoiceSender(filter, sap);

                when(filter.lowValueInvoices()).thenReturn(Collections.emptyList());

                invoiceService.sendLowValuedInvoices();

                verify(sap, times(0));
            }
    @Test
    public void testThrowExceptionWhenBadInvoice() throws FailToSendSAPInvoiceException {

        FilterInvoice Fi = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(Fi, sap);

        Invoice badInvoice = new Invoice("Alex", 50);

        doThrow(new FailToSendSAPInvoiceException()).when(sap).send(badInvoice);


        List<Invoice> failedInvoices = sender.sendLowValuedInvoices();
        assertTrue(failedInvoices.contains(badInvoice));

        assertEquals(1, failedInvoices.size());
    }


}
