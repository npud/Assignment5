package org.example.Assignment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterInvoice {

    QueryInvoicesDAO dao;
    Database db;

    // We want to stub the dao to avoid interacting with database, however it is hard to do so, since dao is initialized internally
    // we need some way to inject dependency which is a stub, so we don't interact with database explicitly
    // we want it to depend on concretion, but only an abstraction.
    public FilterInvoice() {
        // this class doesn't need db, only dao needs it... there is a tight coupling
        // this is called dependency instantiation not injection
        this.db = new Database();
        this.dao = new QueryInvoicesDAO(db);
    }
    public List<Invoice> lowValueInvoices() {
            List<Invoice> all = dao.all();

            return all.stream()
                    .filter(invoice -> invoice.getValue() < 100)
                    .collect(toList());
    }
    @Test
    public void filterInvoiceTest(){
        QueryInvoicesDAO mockDao = mock(QueryInvoicesDAO.class);
        FilterInvoice Fi = mock(FilterInvoice.class);

        Invoice invoice1 = new Invoice("Bob", 50);
        Invoice invoice2 = new Invoice("Steve", 75);
        when(mockDao.all()).thenReturn(Arrays.asList(invoice1, invoice2));
        assertEquals(2, mockDao.all().size());
    }

}

