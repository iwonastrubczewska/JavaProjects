package com.company;

import com.company.dao.*;
import com.company.dao.jpa.*;
import com.company.model.*;
import java.util.Date;
import java.util.LinkedList;

public class App 
{
    public static void main( String[] args )
    {

        CustomerDao customerDao = new JpaCustomerDao();
        DeliveryCompanyDao deliveryCompanyDao = new JpaDeliveryCompanyDao();
        ProductDao productDao = new JpaProductDao();
        PurchaseDao purchaseDao = new JpaPurchaseDao();
        PurchaseItemDao purchaseItemDao = new JpaPurchaseItemDao();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Iwona");
        customerDTO.setLastName("Strubczewska");
        customerDTO.setEmail("mail");

        DeliveryCompanyDTO deliveryCompanyDTO = new DeliveryCompanyDTO();

        deliveryCompanyDTO.setStreet("Street");
        deliveryCompanyDTO.setZipCode("12345");
        deliveryCompanyDTO.setNumber(1);
        deliveryCompanyDTO.setName("name");
        deliveryCompanyDTO.setLocation("Warsaw");
        deliveryCompanyDTO.setPhoneNo(123132142);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(123422323121.00);
        productDTO.setName("name");

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setStreet("street");

        purchaseDTO.setDeliveryCompany(deliveryCompanyDTO);
        purchaseDTO.setDate(new Date());
        purchaseDTO.setZipCode("1234");
        purchaseDTO.setLocation("Warsaw");
        purchaseDTO.setCustomer(customerDTO);

        PurchaseItemDTO purchaseItemDTO = new PurchaseItemDTO();
        purchaseItemDTO.setQuantity(1234);
        purchaseItemDTO.setProduct(productDTO);
        purchaseItemDTO.setPurchase(purchaseDTO);

        LinkedList<PurchaseItemDTO> items= new LinkedList<>();
        items.add(purchaseItemDTO);

        LinkedList<PurchaseDTO> purchases = new LinkedList<>();
        purchases.add(purchaseDTO);
        deliveryCompanyDTO.setPurchases(purchases);

        purchaseDTO.setPurchaseItems(items);


        customerDao.save(customerDTO);
        deliveryCompanyDao.save(deliveryCompanyDTO);
        productDao.save(productDTO);
        purchaseDao.save(purchaseDTO);
        purchaseItemDao.save(purchaseItemDTO);





     //UPDATE:
        /*

        PurchaseDTO updatedPurchase = new PurchaseDTO();
        long id=1;
        updatedPurchase= purchaseDao.findById(id);
        updatedPurchase.setLocation("Gdansk");
        updatedPurchase.setZipCode("80-801");
        purchaseDao.update(updatedPurchase);

        */
    }
}
