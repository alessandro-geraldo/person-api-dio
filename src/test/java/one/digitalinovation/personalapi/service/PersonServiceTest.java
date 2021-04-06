package one.digitalinovation.personalapi.service;

import one.digitalinovation.personalapi.dto.MessageResponseDTO;
import one.digitalinovation.personalapi.dto.request.PersonDTO;
import one.digitalinovation.personalapi.entity.Person;
import one.digitalinovation.personalapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinovation.personalapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinovation.personalapi.utils.PersonUtils.createFakeEntity;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSaveMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        Mockito.when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedMessage = expectedMethodResponse(expectedSavedPerson.getId());

        MessageResponseDTO messageSucesso = personService.createPerson(personDTO);

        assertEquals(expectedMessage, messageSucesso);
    }

    private MessageResponseDTO expectedMethodResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("created person with ID " + id)
                .build();
    }


}
