package com.contable.service;

import com.contable.domain.Contact;
import com.contable.repository.ContactRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.contable.domain.Contact}.
 */
@Service
@Transactional
public class ContactService {

    private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Save a contact.
     *
     * @param contact the entity to save.
     * @return the persisted entity.
     */
    public Contact save(Contact contact) {
        LOG.debug("Request to save Contact : {}", contact);
        return contactRepository.save(contact);
    }

    /**
     * Update a contact.
     *
     * @param contact the entity to save.
     * @return the persisted entity.
     */
    public Contact update(Contact contact) {
        LOG.debug("Request to update Contact : {}", contact);
        return contactRepository.save(contact);
    }

    /**
     * Partially update a contact.
     *
     * @param contact the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Contact> partialUpdate(Contact contact) {
        LOG.debug("Request to partially update Contact : {}", contact);

        return contactRepository
            .findById(contact.getId())
            .map(existingContact -> {
                if (contact.getName() != null) {
                    existingContact.setName(contact.getName());
                }
                if (contact.getIdentification() != null) {
                    existingContact.setIdentification(contact.getIdentification());
                }
                if (contact.getEmail() != null) {
                    existingContact.setEmail(contact.getEmail());
                }
                if (contact.getPhone() != null) {
                    existingContact.setPhone(contact.getPhone());
                }

                return existingContact;
            })
            .map(contactRepository::save);
    }

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Contact> findAll(Pageable pageable) {
        LOG.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable);
    }

    /**
     * Get one contact by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Contact> findOne(Long id) {
        LOG.debug("Request to get Contact : {}", id);
        return contactRepository.findById(id);
    }

    /**
     * Delete the contact by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Contact : {}", id);
        contactRepository.deleteById(id);
    }
}
