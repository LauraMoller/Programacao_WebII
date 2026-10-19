package Interface;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

/**
 * Serviço SOAP para o gerenciamento de pets do Pet Shop.
 * Oferece o CRUD de pets e o vínculo com os tutores. Cada pet é identificado
 * por um id gerado no método criarPet, e o vínculo com os tutores é feito pelos
 * id dos tutores.
 * Em caso de dado inválido/id inexistente, as operações lançam
 * IllegalArgumentException que o cliente recebe como SOAP Fault.
 * WSDL: {@code http://localhost:9876/pet?wsdl}
 */
@WebService
public interface Pet {
        /**
         * Cadastra um pet no sistema.
         * 
         * @param nome    nome do pet.
         * @param peso    peso do pet.
         * @param idade   idade do pet.
         * @param especie espécie do pet.
         * @return id do pet.
         */
        @WebMethod
        int criarPet(String nome, float peso, int idade, Especie especie);

        /**
         * Atualiza os dados de um pet cadastrado.
         * 
         * @param id      id do pet qual deseja atualizar.
         * @param nome    opcional; nome do pet atualizado.
         * @param peso    opcional; peso atualizado do pet.
         * @param idade   opcional; idade atualizada do pet.
         * @param especie opcional; especie atualizada do pet.
         */
        @WebMethod
        void atualizarDadosPet(int id, String nome, float peso, int idade, Especie especie);

        /**
         * Consulta os tutores vínculados ao pet.
         * 
         * @param idPet id do pet qual deseja consultar os vínculos.
         * @return lista de vínculos do pet (tutores).
         */
        @WebMethod
        List<Integer> consultarTutores(int idPet);

        /**
         * Adiciona um vínculo entre um pet e um tutor.
         * 
         * @param idPet   id do pet que deseja vincular.
         * @param idTutor id do duttor que deseja vincular.
         */
        @WebMethod
        void adicionarTutor(int idPet, int idTutor);

        /**
         * Consulta os dados de um pet.
         * 
         * @param id id do pet que deseja consultar.
         * @return dados do pet.
         */
        @WebMethod
        String consultarDadosPet(int id);

        /**
         * Remove um vínculo de tutor com um pet.
         * 
         * @param idPet   id do pet.
         * @param idTutor id do tutor.
         */
        @WebMethod
        void removerTutor(int idPet, int idTutor);
}