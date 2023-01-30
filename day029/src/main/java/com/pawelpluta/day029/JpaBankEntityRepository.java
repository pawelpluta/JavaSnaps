package com.pawelpluta.day029;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
interface JpaBankEntityRepository extends CrudRepository<JpaBankEntityRepository.BankEntity, String> {

    @Entity
    @Table(name = "banks")
    class BankEntity {

        @Id
        @Column(name = "bank_id")
        private String id;
        @Column(name = "country")
        private String country;
        @Column(name = "postal_code")
        private String postalCode;
        @Column(name = "city")
        private String city;
        @Column(name = "street")
        private String street;
        @Column(name = "building_number")
        private String buildingNumber;
        @OneToMany(
                mappedBy = "bank",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER
        )
        private Set<CustomerEntity> customers = new HashSet<>();

        static BankEntity from(Bank bank) {
            BankEntity bankEntity = new BankEntity();
            bankEntity.setId(bank.id());
            bankEntity.setCountry(bank.address().country());
            bankEntity.setPostalCode(bank.address().postalCode());
            bankEntity.setCity(bank.address().city());
            bankEntity.setStreet(bank.address().street());
            bankEntity.setBuildingNumber(bank.address().buildingNumber());
            bank.customers().forEach(customer -> {
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setCustomerId(customer.customerId());
                customerEntity.setBank(bankEntity);
                customerEntity.setFirstName(customer.firstName());
                customerEntity.setLastName(customer.lastName());
                customerEntity.setCountry(customer.address().country());
                customerEntity.setPostalCode(customer.address().postalCode());
                customerEntity.setCity(customer.address().city());
                customerEntity.setStreet(customer.address().street());
                customerEntity.setBuildingNumber(customer.address().buildingNumber());
                bankEntity.addCustomer(customerEntity);
            });
            return bankEntity;
        }

        void setId(String id) {
            this.id = id;
        }

        void setCountry(String country) {
            this.country = country;
        }

        void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        void setCity(String city) {
            this.city = city;
        }

        void setStreet(String street) {
            this.street = street;
        }

        void setBuildingNumber(String buildingNumber) {
            this.buildingNumber = buildingNumber;
        }

        void addCustomer(CustomerEntity customer) {
            customers.add(customer);
            customer.setBank(this);
        }

        Bank toModel() {
            return Bank.of(
                    id,
                    addressAsModel(),
                    customers.stream().map(CustomerEntity::toModel).toList()
            );
        }

        private Address addressAsModel() {
            return new Address(country, postalCode, city, street, buildingNumber);
        }
    }

    @Entity
    @Table(name = "customers")
    class CustomerEntity {
        @Id
        @Column(name = "customer_id")
        private String customerId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "bank_id")
        private BankEntity bank;
        @Column(name = "first_name")
        private String firstName;
        @Column(name = "last_name")
        private String lastName;
        @Column(name = "country")
        private String country;
        @Column(name = "postal_code")
        private String postalCode;
        @Column(name = "city")
        private String city;
        @Column(name = "street")
        private String street;
        @Column(name = "building_number")
        private String buildingNumber;

        void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        void setBank(BankEntity bank) {
            this.bank = bank;
        }


        void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        void setLastName(String lastName) {
            this.lastName = lastName;
        }

        void setCountry(String country) {
            this.country = country;
        }

        void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        void setCity(String city) {
            this.city = city;
        }

        void setStreet(String street) {
            this.street = street;
        }

        void setBuildingNumber(String buildingNumber) {
            this.buildingNumber = buildingNumber;
        }

        Customer toModel() {
            return new Customer(
                    customerId,
                    firstName,
                    lastName,
                    addressAsModel()
            );
        }

        private Address addressAsModel() {
            return new Address(country, postalCode, city, street, buildingNumber);
        }
    }
}
