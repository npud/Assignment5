import org.example.Assignment.FilterInvoice;
import org.example.Assignment.Invoice;
import org.example.Assignment.SAP;
import org.example.Assignment.SAP_BasedInvoiceSender;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class SAP_BasedInvoiceSenderTest {

    @Test
    public void testWhenLowInvoicesSent(){

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
    public void testWhenNoInvoices(){

                FilterInvoice filter = mock(FilterInvoice.class);
                SAP sap = mock(SAP.class);
                SAP_BasedInvoiceSender invoiceService = new SAP_BasedInvoiceSender(filter, sap);

                when(filter.lowValueInvoices()).thenReturn(Collections.emptyList());

                invoiceService.sendLowValuedInvoices();

                verify(sap, times(0));
            }


}
