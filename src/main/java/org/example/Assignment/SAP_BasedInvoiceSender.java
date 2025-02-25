package org.example.Assignment;

import java.util.ArrayList;
import java.util.List;

// Class responsible for sending low-valued invoices to the SAP system
public class SAP_BasedInvoiceSender {

    private final FilterInvoice filter;  // Dependency for filtering invoices
    private final SAP sap;  // Dependency for sending invoices to the SAP system

    // Constructor that uses dependency injection to initialize the filter and sap objects
    public SAP_BasedInvoiceSender(FilterInvoice filter, SAP sap) {
        this.filter = filter;
        this.sap = sap;
    }

    // Method to send all low-valued invoices to the SAP system
    public List<Invoice> sendLowValuedInvoices() throws FailToSendSAPInvoiceException {
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();
        List<Invoice> failedValueInvoices = new ArrayList<>();
        for (Invoice invoice : lowValuedInvoices) {  // Iterates through each invoice in the list
            sap.send(invoice);
        }
        return failedValueInvoices;
    }

}
