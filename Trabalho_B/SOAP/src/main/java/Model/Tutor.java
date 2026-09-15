package Model;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Tutor {
    @WebMethod void atualizarDadosTutor (String nome, String endereco);
    @WebMethod List<Pet> consultarPets();
    @WebMethod boolean adicionarPet (Pet pet);
    @WebMethod String consultarDadosTutor();
    @WebMethod boolean removerPet(Pet pet);
}
