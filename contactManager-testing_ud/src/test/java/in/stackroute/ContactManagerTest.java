package in.stackroute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
class ContactManagerTest {

    private ContactManager contactManager;
    private Contact invalidName, invalidEmail, invalidPhone;
    private Contact validContact;
    @BeforeEach
    public void  setup(){
        contactManager = new ContactManager();

//        invalidPhone.setPhoneNumber("486739");
        invalidPhone = new Contact("Name", "invalidphone@example.com", "12345");
        invalidEmail = new Contact("udit", "", "9876543210");
        invalidName = new Contact("", "invalidname@example.com", "9876543210");
        validContact = new Contact("Udit", "uditraj@gmail.com", "9548772358");

    }

    @Test
    @DisplayName("shouldAddContact")
    public void shouldAddContact(){
        ContactValidation result = contactManager.addContact(validContact);
        assertEquals(ContactValidation.VALID,result);
    }


    @Test
    @DisplayName("should not add contact when name is null")
    public void shouldNotAddWhenNameIsNull(){
        ContactValidation result=contactManager.addContact(invalidName);
        assertEquals(ContactValidation.NAME_NOT_VALID,result);
    }

    @Test
    @DisplayName("should not add contact when email is null")
    public void shouldNotAddWhenEmailIsNull(){

        ContactValidation result=contactManager.addContact(invalidEmail);
        assertEquals(ContactValidation.EMAIL_NOT_VALID,result);
    }


    @Test
    @DisplayName("should not add contact when phone is Invalid")
    public void shouldNotAddWhenPhoneIsInvalid(){
        ContactValidation result=contactManager.addContact(invalidPhone);
        assertEquals(ContactValidation.PHONE_NUMBER_NOT_VALID,result);
    }

    @Test
    @DisplayName("should not add contact when Name is Invalid")
    public void shouldNotAddWhenNameIsInvalid(){
        Contact contact = new Contact("uf@cs","a@gmail.com","8793703832");
        ContactValidation result=contactManager.addContact(contact);
        assertEquals(ContactValidation.NAME_NOT_VALID,result);
    }
    @Test
    @DisplayName("should not add contact when email is Invalid")
    public void shouldNotAddWhenEmailIsInvalid(){
        Contact contact = new Contact("udit","a@gmailcom","8464534272");
        ContactValidation result=contactManager.addContact(contact);
        assertEquals(ContactValidation.EMAIL_NOT_VALID,result);
    }



    @Test
    @DisplayName("shouldWeUpdateContact")
    public  void shouldWeUpdateContact(){
        Contact contact = new Contact("Udit", "rah@gmail.com", "2566483256");
        contactManager.addContact(contact);

        Contact updatedContact = new Contact("Raj", "udit@gmail.com", "1234563214");

        ContactValidation result = contactManager.updateContact(updatedContact);
        assertEquals(ContactValidation.VALID, result);
    }

//  Deletion

    @Test
    @DisplayName("case - should we Delete existing  contact")
    public  void shouldWeDeleteContact(){
        Contact contact = new Contact("Abhishek", "a@gmail.com", "5556667777");
        contactManager.addContact(contact);


        ContactValidation result = contactManager.deleteContact("Abhishek");

        assertEquals(ContactValidation.VALID, result);
    }

    @Test
    @DisplayName("Should we delete non existing contact")
    public void shouldWeDeleteNonExistingContact() {
        ContactValidation result = contactManager.deleteContact("Exception");
        assertEquals(ContactValidation.CONTACT_DOES_NOT_EXIST, result);
    }

    @Test
    @DisplayName("should we delete invalid contact")
    public void shouldWeDeleteContactWithInvalidName() {
        ContactValidation result = contactManager.deleteContact("");
        assertEquals(ContactValidation.NAME_NOT_VALID, result);

        result = contactManager.deleteContact("");
        assertEquals(ContactValidation.NAME_NOT_VALID, result);
    }



    @Test
    @DisplayName("should we search existing  contact")
    public  void shouldWeSearchContact(){

        contactManager.addContact(validContact);


        ContactValidation result = contactManager.searchContact("Udit");

        assertEquals(ContactValidation.VALID, result);
    }




    //contact class
    @Test
    public void testGetName() {
        Contact contact = new Contact("John Jo", "john@example.com", "1546879546");
        assertEquals("John Jo", contact.getName());
    }

    @Test
    public void testSetName() {
        Contact contact = new Contact();
        contact.setName("Alice Smith");
        assertEquals("Alice Smith", contact.getName());
    }

    @Test
    public void testGetEmail() {
        Contact contact = new Contact("Bob Johnson", "bob@example.com", "3256145236");
        assertEquals("bob@example.com", contact.getEmail());
    }

    @Test
    public void testSetEmail() {
        Contact contact = new Contact();
        contact.setEmail("abc@example.com");
        assertEquals("abc@example.com", contact.getEmail());
    }

    @Test
    public void testGetPhoneNumber() {
        Contact contact = new Contact("shishir", "sk@example.com", "3214565415");
        assertEquals("3214565415", contact.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        Contact contact = new Contact();
        contact.setPhoneNumber("3334446669");
        assertEquals("3334446669", contact.getPhoneNumber());
    }
}

