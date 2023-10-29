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
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class SupplierServiceImplTest {

    @Autowired
    SupplierServiceImpl supplierService ;

    @Test
    @DatabaseSetup("/data-set/invoices-data.xml")
    void retrieveAllSuppliers() {
        final List<Supplier> suppliers = this.supplierService.retrieveAllSuppliers();
        assertEquals(suppliers.size(), 2);
    }

    @Test
    @DatabaseSetup("/data-set/invoices-data.xml")
    void addSupplier() {
        Supplier supplier = new Supplier();
        supplier.setCode("SUP003");
        supplier.setLabel("Supplier C");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);
        this.supplierService.addSupplier(supplier);

        assertEquals(this.supplierService.retrieveAllSuppliers().size(), 3);
        assertEquals(this.supplierService.retrieveSupplier(3L).getLabel() , "Supplier C");
    }

    @Test
    @DatabaseSetup("/data-set/invoices-data.xml")
    void updateSupplier() {
        // Get an operator from the database
        Supplier supplier  = this.supplierService.retrieveSupplier(1L);

        // Update the operator's first name
        supplier.setCode("SUP002");

        // Call the update method in your service
        this.supplierService.updateSupplier(supplier);

        // Retrieve the updated operator
        Supplier updatedSupplier = this.supplierService.retrieveSupplier(1L);

        // Verify that the first name has been updated
        assertEquals("SUP002", updatedSupplier.getCode());
    }

    @Test
    @DatabaseSetup("/data-set/invoices-data.xml")
    void deleteSupplier() {
        // Get the initial number of operators
        int initialSupplierCount = this.supplierService.retrieveAllSuppliers().size();

        // Delete an operator (assuming you have a delete method in your service)
        this.supplierService.deleteSupplier(1L);

        // Verify that the number of operators has decreased by one
        int updatedSupplierCount = this.supplierService.retrieveAllSuppliers().size();
        assertEquals(initialSupplierCount - 1, updatedSupplierCount);

    }

    @Test
    @DatabaseSetup("/data-set/invoices-data.xml")
    void retrieveSupplier() {
        final Supplier supplier  = this.supplierService.retrieveSupplier(1L);
        assertEquals("SUP001", supplier.getCode());
    }

}