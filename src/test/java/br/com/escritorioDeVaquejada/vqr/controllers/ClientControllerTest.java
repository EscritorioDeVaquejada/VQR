package br.com.escritorioDeVaquejada.vqr.controllers;

import br.com.escritorioDeVaquejada.vqr.exceptions.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.models.Address;
import br.com.escritorioDeVaquejada.vqr.services.ClientServices;
import br.com.escritorioDeVaquejada.vqr.vo.ClientVo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    private ClientVo newClient;
    @Mock
    private BindingResult bindingResult;
    @Mock
    ClientServices clientServices;
    @InjectMocks
    ClientController clientController;

    @BeforeEach
    void setup(){
        newClient = new ClientVo();

        newClient.setName("NameTest");
        newClient.setAddress(new Address("StateTest", "CityTest"));
        newClient.setEmail("teste@gmail.com");
        newClient.setContactNumber("99999999999");
    }

    @Test
    @DisplayName("Should successfully save a client to the database and return the saved client with HTTP status code 201")
    void saveClientWithAllCorrectData(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(clientServices.saveClient(newClient)).thenReturn(newClient);

        ResponseEntity<ClientVo> result = clientController.saveClient(newClient, bindingResult);

        Mockito.verify(clientServices, times(1)).saveClient(newClient);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(result.getBody()).isEqualTo(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the name field is null")
    void saveClientWithOnlyIncorrectNameAttribute() {
        newClient.setName(null);

        when(bindingResult.hasErrors()).thenReturn(true);
        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientServices, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the address field is null")
    void saveClientWithOnlyIncorrectAddressData(){
        newClient.setAddress(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientServices, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the city field of the address attribute is null")
    void saveClientWithOnlyCityAttributeFromIncorrectAddressAttribute(){
        newClient.getAddress().setCity(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientServices, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the state field of the address attribute is null")
    void saveClientWithOnlyStateAttributeFromIncorrectAddressAttribute(){
        newClient.getAddress().setState(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientServices, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because the contactNumber field is null")
    void saveClientWithOnlyIncorrectNumberAttribute(){
        newClient.setContactNumber(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientServices, Mockito.never()).saveClient(newClient);
    }

    @Test
    @DisplayName("Should throw a BadRequestException because all fields with validation are null")
    void saveClientWithAllIncorrectData(){
        newClient.setContactNumber(null);
        newClient.setName(null);
        newClient.setAddress(null);

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThatThrownBy(() -> clientController.saveClient(newClient, bindingResult))
                .isInstanceOf(BadRequestException.class);
        Mockito.verify(clientServices, Mockito.never()).saveClient(newClient);
    }
}