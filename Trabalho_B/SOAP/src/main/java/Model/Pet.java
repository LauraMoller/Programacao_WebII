package Model;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.glassfish.ha.store.annotations.Attribute;

import java.util.List;
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Pet {

        @WebMethod void atualizarDadosPet (String nome, float peso, Integer idade, Especie especie);
        @WebMethod List<Tutor> consultarTutores ();
        @WebMethod void adicionarTutor (Tutor tutor);
        @WebMethod String consultarDadosPet ();
        @WebMethod void removerTutor(Tutor tutor);
}