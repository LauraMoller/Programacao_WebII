import Interface.Especie;
import Interface.Pet;
import Interface.Tutor;

import jakarta.xml.ws.Service;

import javax.xml.namespace.QName;
import java.net.URL;

class Client {

    public static void main(String args[]) throws Exception {
        /**
         * Endereço do WSDL do serviço pet.
         */
        URL urlPet = new URL("http://localhost:9876/pet?wsdl");
        /**
         * Identifica o serviço dentro do WSDL (namespace + nome do serviço).
         */
        QName qnamePet = new QName("http://Controler/", "PetControlerService");
        /**
         * Cria o serviço pet.
         */
        Pet pet = Service.create(urlPet, qnamePet).getPort(Pet.class);

        /**
         * Endereço do WSDL do serviço tutor.
         */
        URL urlTutor = new URL("http://localhost:9876/tutor?wsdl");
        /**
         * Identifica o serviço dentro do WSDL (namespace + nome do serviço).
         */
        QName qnameTutor = new QName("http://Controler/", "TutorControlerService");
        /**
         * Cria o serviço tutor.
         */
        Tutor tutor = Service.create(urlTutor, qnameTutor).getPort(Tutor.class);

        /**
         * criarPet envia uma mensagem SOAP ao servidor para criar um pet.
         */
        int idPet = pet.criarPet("Rex", 12.5f, 3, Especie.CACHORRO);
        /**
         * criarTutor envia uma mensagem SOAP ao servidor para criar um tutor.
         */
        int idTutor = tutor.criarTutor("Maria Silva", "47999999999", "Rua das Flores, 123");
        System.out.println("Pet criado, id: " + idPet);
        System.out.println("Tutor criado, id: " + idTutor);

        /**
         * Consulta os dados de um pet.
         */
        System.out.println("Pet:\n" + pet.consultarDadosPet(idPet));
        /**
         * Consulta os dados de um tutor.
         */
        System.out.println("Tutor:\n" + tutor.consultarDadosTutor(idTutor));

        /**
         * Atualiza os dados de um pet cadastrado.
         */
        pet.atualizarDadosPet(idPet, "Rex Junior", 14f, 4, Especie.CACHORRO);
        /**
         * Atualiza os dados de um tutor cadastrado.
         */
        tutor.atualizarDadosTutor(idTutor, "Maria S. Souza", "1132345678", "Av. Brasil, 456");
        System.out.println("Pet atualizado:\n" + pet.consultarDadosPet(idPet));
        System.out.println("Tutor atualizado:\n" + tutor.consultarDadosTutor(idTutor));

        /**
         * Cria um vínculo entre pet e tutor.
         */
        pet.adicionarTutor(idPet, idTutor);
        tutor.adicionarPet(idTutor, idPet);
        System.out.println("Tutores do pet: " + pet.consultarTutores(idPet));
        System.out.println("Pets do tutor: " + tutor.consultarPets(idTutor));

        /**
         * Testa os erros esperados.
         */
        try {
            /**
             * Testa peso negativo.
             */
            pet.criarPet("Mimi", -1f, 2, Especie.GATO);
        } catch (Exception e) {
            System.out.println("Peso inválido: " + e.getMessage());
        }
        try {
            /**
             * Testa telefone inválido.
             */
            tutor.criarTutor("Joao", "123", "Rua A, 1");
        } catch (Exception e) {
            System.out.println("Telefone inválido: " + e.getMessage());
        }
        try {
            /**
             * Testa busca de pet inexistente.
             */
            pet.consultarDadosPet(999);
        } catch (Exception e) {
            System.out.println("Pet inexistente: " + e.getMessage());
        }

        /**
         * Desfaz os vínculos e depois apaga os registros.
         */
        pet.removerTutor(idPet, idTutor);
        tutor.removerPet(idTutor, idPet);
        pet.removerPet(idPet);
        tutor.removerTutor(idTutor);
        System.out.println("Pet e tutor removidos.");
    }
}