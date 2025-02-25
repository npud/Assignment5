package org.example.Assignment;

public class FailToSendSAPInvoiceException extends Exception {
    public FailToSendSAPInvoiceException() {super("Failed to send the SAP invoice");}

    public FailToSendSAPInvoiceException(String message) {super(message);}

}
