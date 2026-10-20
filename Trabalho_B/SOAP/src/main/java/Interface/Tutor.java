package Interface;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

/**
 * Serviço SOAP para o gerenciamento de Tutores do Pet Shop.
 * Oferece o CRUD de tutores e o vínculo com os pets. Cada tutor é identificado
 * por um id gerado no método criarTutor, e o vínculo com os pets é feito pelos
 * id dos pets.
 * Em caso de dado inválido/id inexistente, as operações lançam
 * IllegalArgumentException que o cliente recebe como SOAP Fault.
 * WSDL: {@code http://localhost:9876/tutor?wsdl}
 */
@WebService
public interface Tutor {
    /**
     * Cadastra um novo tutor no sistema.
     * 
     * @param nome     nome do tutor.
     * @param telefone telefone de contato do tutor.
     * @param endereco endereço do tutor;
     * @return id do tutor.
     */
    @WebMethod
    int criarTutor(String nome, String telefone, String endereco);

    /**
     * Consulta os dados de um tutor a partir de seu id de cadastro.
     * 
     * @param id id do tutor.
     * @return dados do tutor.
     */
    @WebMethod
    String consultarDadosTutor(int id);

    /**
     * Atualiza os dados de um tutor cadastrado.
     * 
     * @param id       id do tutor qual deseja atualizar.
     * @param nome     opcional; nome do tutor atualizado.
     * @param telefone opcional; telefone do tutor atualizado.
     * @param endereco opcional; endereço do tutor atualizado.
     */
    @WebMethod
    void atualizarDadosTutor(int id, String nome, String telefone, String endereco);

    /**
     * Remove um tutor do sistema.
     * 
     * @param id id do tutor qual deseja remover do sistema.
     */
    @WebMethod
    void removerTutor(int id);

    /**
     * Víncula um pet a um tutor.
     * 
     * @param idTutor id do tutor.
     * @param idPet   id do pet.
     */
    @WebMethod
    void adicionarPet(int idTutor, int idPet);

    /**
     * Consulta os pets vínculados ao tutor.
     * 
     * @param idTutor id do tutor que deseja consultar.
     * @return lista de pets vínculados ao tutor.
     */
    @WebMethod
    List<Integer> consultarPets(int idTutor);

    /**
     * Remove um vínculo entre pet e tutor.
     * 
     * @param idTutor id do tutor.
     * @param idPet   id do pet.
     */
    @WebMethod
    void removerVinculoTutor(int idTutor, int idPet);

}
