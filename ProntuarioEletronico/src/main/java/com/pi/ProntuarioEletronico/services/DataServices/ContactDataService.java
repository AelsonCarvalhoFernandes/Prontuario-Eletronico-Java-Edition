package com.pi.ProntuarioEletronico.services.DataServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.ProntuarioEletronico.models.user.UserModel;
import com.pi.ProntuarioEletronico.models.user.contactUsers.ContactModel;
import com.pi.ProntuarioEletronico.repositories.UserRepository.IContactRepository;

@Service
public class ContactDataService {

    @Autowired
    private IContactRepository contactRepository;

    public ContactModel findById(Long id) {
        return contactRepository.findById(id).get();
    }

    public ContactModel findByUser(UserModel model) {
        try {

            ContactModel contact = contactRepository.findByUser(model);
            
            if (contact == null) {
                return null;
            }

            return contact;

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    public ContactModel create(ContactModel model) {
        try {

            return contactRepository.save(model);

        } catch (Exception ex) {

            System.out.println("Error: " + ex);
            return null;

        }
    }

    public ContactModel update(ContactModel model) {
        try {

            return contactRepository.save(model);

        } catch (Exception ex) {

            System.out.println("Error: " + ex);
            return null;

        }
    }

    public boolean delete(Long id) {
        try {

            ContactModel contact = this.findById(id);
            contactRepository.delete(contact);

            return true;

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return false;
        }
    }

}
