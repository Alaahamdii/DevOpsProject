package tn.esprit.devops_project.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class InvoiceServiceImplTest {

    @Autowired
    private InvoiceServiceImpl invoiceService;
    @Autowired
    private InvoiceRepository invoiceRepository ;
    @Autowired
    private SupplierServiceImpl supplierService ;
    @Autowired
    private SupplierRepository supplierRepository ;

    @Test
    @DatabaseSetup("/data-set/invoices-data.xml")
    void retrieveAllInvoices() {

        final List<Invoice> allInvoices = invoiceService.retrieveAllInvoices();
        assertEquals(2, allInvoices.size());
    }

    @Test
    void cancelInvoice() {
        //a terminer
    }

    @Test
    @DatabaseSetup("/data-set/invoices-data.xml")
    void retrieveInvoice() {
        assertThrows(NullPointerException.class,() -> this.invoiceService.retrieveInvoice(33L));
        final Invoice invoice = this.invoiceService.retrieveInvoice(1L);
        assertEquals(false ,invoice.getArchived());
    }

    @Test
    @DatabaseSetup("/data-set/supplier-data.xml")
    void getInvoicesBySupplier() {

        final Supplier supplier = supplierService.retrieveSupplier(1L); // Assurez-vous d'utiliser le bon ID de fournisseur
        final Set<Invoice> invoicesBySupplierSet = supplier.getInvoices();

        // Convertissez l'ensemble en une liste
        List<Invoice> invoicesBySupplierList = new ArrayList<>(invoicesBySupplierSet);

        assertEquals(2, invoicesBySupplierList.size());
    }


    @Test
    void assignOperatorToInvoice() {
        //a terminer
    }

    @Test
    void getTotalAmountInvoiceBetweenDates() {
        //a terminer
    }
}