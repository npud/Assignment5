import org.example.Assignment.Invoice;
import org.example.Assignment.QueryInvoicesDAO;
import org.junit.Test;

import java.util.Arrays;

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


    }

}
