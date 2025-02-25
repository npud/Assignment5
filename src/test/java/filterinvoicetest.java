import org.example.Assignment.Database;
import org.example.Assignment.FilterInvoice;
import org.example.Assignment.Invoice;
import org.example.Assignment.QueryInvoicesDAO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class filterinvoicetest {
    @Test
    public void filterInvoiceTest(){
        QueryInvoicesDAO mockDao = mock(QueryInvoicesDAO.class);
        Invoice invoice1 = new Invoice("Bob", 50);
        Invoice invoice2 = new Invoice("Steve", 75);
        when(mockDao.all()).thenReturn(Arrays.asList(invoice1, invoice2));
        assertEquals(2, mockDao.all().size());
    }
    @Test
    public void filterInvoicesStubbedTest(){
        Database db = mock(Database.class);
        QueryInvoicesDAO mockDao = new QueryInvoicesDAO(db);
        Invoice invoice4 = new Invoice("Sal", 50);
        Invoice invoice5 = new Invoice("Jef", 20);
        Invoice invoice6 = new Invoice("Mark", 5);
        mockDao.save(invoice4);
        mockDao.save(invoice5);
        mockDao.save(invoice6);
        assertEquals(invoice4, mockDao.all());


    }

}
