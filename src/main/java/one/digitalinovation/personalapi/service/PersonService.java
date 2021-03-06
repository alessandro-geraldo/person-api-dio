package one.digitalinovation.personalapi.service;

import lombok.AllArgsConstructor;
import one.digitalinovation.personalapi.dto.MessageResponseDTO;
import one.digitalinovation.personalapi.dto.request.PersonDTO;
import one.digitalinovation.personalapi.entity.Person;
import one.digitalinovation.personalapi.exception.PersonNotFoundException;
import one.digitalinovation.personalapi.mapper.PersonMapper;
import one.digitalinovation.personalapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "created person with ID ");
    }
    public List<PersonDTO> listAll(){
         List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                  .map(personMapper::toDTO)
                  .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delet(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);

    }

    public MessageResponseDTO upDateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpDate = personMapper.toModel(personDTO);
        Person upDatePerson = personRepository.save(personToUpDate);
        return createMessageResponse(upDatePerson.getId(), "Update person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(()->new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
