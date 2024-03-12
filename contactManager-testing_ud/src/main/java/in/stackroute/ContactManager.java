package in.stackroute;

import java.util.HashMap;
import java.util.Map;


public class ContactManager {
    private final Map<String, Contact> contacts;

    public ContactManager() {
        contacts = new HashMap<>();
    }

    /**
     * Add a contact to the contact manager
     *
     * @param contact contact to be added
     * @return ContactValidation
     */
    public ContactValidation addContact(Contact contact) {
        ContactValidation validation = validate(contact);
        ContactValidation duplicateContact = contactExist(contact);
        if (validation == ContactValidation.VALID && duplicateContact == ContactValidation.CONTACT_DOES_NOT_EXIST) {
            contacts.put(contact.getName(), contact);
        }
        return validation;
    }

    public ContactValidation updateContact(Contact contact) {
        ContactValidation validation = validate(contact);
        ContactValidation duplicateContact = contactExist(contact);
        if (validation == ContactValidation.VALID && duplicateContact == ContactValidation.CONTACT_EXISTS) {
            contacts.put(contact.getName(), contact);
        }
        return validation;
    }

    public ContactValidation deleteContact(String name) {
        if (name == null || name.isEmpty()) {
            return ContactValidation.NAME_NOT_VALID;
        }
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            return ContactValidation.VALID;
        }
        return ContactValidation.CONTACT_DOES_NOT_EXIST;
    }

    public ContactValidation searchContact(String name) {

        if (name == null || name.isEmpty()) {
            return ContactValidation.NAME_NOT_VALID;
        }
        if (contacts.containsKey(name)) {
            return ContactValidation.VALID;
        }
        return ContactValidation.CONTACT_DOES_NOT_EXIST;
    }

    private ContactValidation contactExist(Contact contact) {
        if (contacts.containsKey(contact.getName())) {
            return ContactValidation.CONTACT_EXISTS;
        }
        return ContactValidation.CONTACT_DOES_NOT_EXIST;
    }

    /**
     * Validate a contact
     * <p>
     * A contact is valid if:
     *     <ul>
     *         <li>name is not null or empty and it should only contain alphabets</li>
     *         <li>email is not null or empty and is a valid email address</li>
     *         <li>phone number is not null or empty and is a 10 digit number</li>
     *     </ul>
     * </p>
     */
    private ContactValidation validate(Contact contact) {
        if (contact == null) {
            return ContactValidation.CONTACT_NULL;
        }
        if (!validateName(contact.getName())) {
            return ContactValidation.NAME_NOT_VALID;
        }
        if (!validateEmail(contact.getEmail())) {
            return ContactValidation.EMAIL_NOT_VALID;
        }
        if (!validatePhoneNumber(contact.getPhoneNumber())) {
            return ContactValidation.PHONE_NUMBER_NOT_VALID;
        }
        return ContactValidation.VALID;
    }

    private boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return name.matches("[a-zA-Z]+");
    }

    private boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches("[0-9]{10}");
    }


}
