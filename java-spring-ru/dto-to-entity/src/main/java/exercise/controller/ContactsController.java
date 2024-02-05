package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

   @PostMapping
    private ResponseEntity<ContactDTO> createContact(ContactCreateDTO request) {
       var contact = contactRequestToEntity(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(contactEntityToDto(contact));
   }

   private Contact contactRequestToEntity(ContactCreateDTO request) {
       var contact = new Contact();
       contact.setPhone(request.getPhone());
       contact.setFirstName(request.getFirstName());
       contact.setLastName(request.getLastName());
       return contactRepository.save(contact);
   }

   private ContactDTO contactEntityToDto(Contact contact) {
       var contactDTO = new ContactDTO();
       contactDTO.setId(contact.getId());
       contactDTO.setCreatedAt(contact.getCreatedAt());
       contactDTO.setUpdatedAt(contact.getUpdatedAt());
       contactDTO.setPhone(contact.getPhone());
       contactDTO.setLastName(contact.getLastName());
       contactDTO.setFirstName(contact.getFirstName());
       return contactDTO;
   }
}
