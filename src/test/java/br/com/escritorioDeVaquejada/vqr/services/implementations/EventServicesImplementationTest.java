package br.com.escritorioDeVaquejada.vqr.services.implementations;

import br.com.escritorioDeVaquejada.vqr.mappers.Mapper;
import br.com.escritorioDeVaquejada.vqr.models.EventModel;
import br.com.escritorioDeVaquejada.vqr.repositories.EventRepository;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.services.TicketServices;
import br.com.escritorioDeVaquejada.vqr.vo.EventVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventServicesImplementationTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private ClientServices clientServices;
    @Mock
    private TicketServices ticketServices;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private EventServicesImplementation eventServicesImplementation;
    private EventModel eventModelMock;

    private EventVo eventVoMock;

    @BeforeAll
    static void setup(){

    }

    @Test
    void saveEvent() {
    }

    @Test
    void findEventsByClientId() {
    }

    @Test
    void findEventByID() {
    }
}