package Client;

import Interface.Tutor;
import Interface.Especie;
import Interface.Pet;
import jakarta.xml.ws.Service;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Cliente simples para testar o serviço SOAP de Tutor.
 * Execute antes o Publicador (Publisher.Publicador).
 */
public class Client {
    public static void main(String[] args) throws MalformedURLException {
        URL urlTutor = new URL("http://localhost:9876/tutor?wsdl");
        QName qnameTutor = new QName("http://Controler/", "TutorControlerService");
        Service wst = Service.create(urlTutor, qnameTutor);
        Tutor tutor = wst.getPort(Tutor.class);

        URL urlPet = new URL("http://localhost:9876/pet?wsdl");
        QName qnamePet = new QName("http://Controler/", "PetControlerService");
        Service wsp = Service.create(urlPet, qnamePet);
        Pet pet = wsp.getPort(Pet.class);

        // Validação das operações

        // Criando tutores
        int idLaura = tutor.criarTutor("Laura", "47999999999", "Rua Nereu Ramos");
        System.out.println("Tutor criado com id: " + idLaura);
        int idTiago = tutor.criarTutor("Tiago", "47988888888", "Rua Antônio da Veiga");
        System.out.println("Tutor criado com id: " + idTiago);
        int idGustavo = tutor.criarTutor("Gustavo", "47977777777", "Rua João Pessoa");
        System.out.println("Tutor criado com id: " + idGustavo + "\n");

        // Consultando os dados - tutores
        System.out.println(tutor.consultarDadosTutor(idLaura) + "\n");
        System.out.println(tutor.consultarDadosTutor(idTiago) + "\n");
        System.out.println(tutor.consultarDadosTutor(idGustavo) + "\n");

        // Atualizar dados de um tutor
        tutor.atualizarDadosTutor(idLaura, null, null, "Rua XV");
        System.out.println(tutor.consultarDadosTutor(idLaura) + "\n");

        // Criar Pets
        int idBatatinha = pet.criarPet("Batatinha", 4.5f, 2, Especie.CACHORRO);
        System.out.println("Pet criado com id: " + idBatatinha);
        int idMimi = pet.criarPet("Mimi", 10f, 17, Especie.GATO);
        System.out.println("Pet criado com id: " + idMimi + "\n");

        // Consultar dados - pets
        System.out.println(pet.consultarDadosPet(idBatatinha) + "\n");
        System.out.println(pet.consultarDadosPet(idMimi) + "\n");

        // Atualizar dados de um pet
        pet.atualizarDadosPet(idBatatinha, null, 6, -1, null);
        System.out.println(pet.consultarDadosPet(idBatatinha) + "\n");

        // Criar vinculo
        pet.adicionarTutor(idBatatinha, idLaura);
        pet.adicionarTutor(idMimi, idLaura);
        tutor.adicionarPet(idLaura, idMimi);
        tutor.adicionarPet(idLaura, idBatatinha);

        // Conferência dos vínculos
        System.out.println("Vínculos de Laura: " + tutor.consultarPets(idLaura));
        System.out.println("Ids dos pets: " + idBatatinha + ", " + idMimi + "\n");
        System.out.println("Vínculo de Batatinha: " + pet.consultarTutores(idBatatinha));
        System.out.println("Vínculo de Mimi: " + pet.consultarTutores(idMimi));
        System.out.println("Id de Laura: " + idLaura + "\n");

        // Removendo vínculos
        tutor.removerVinculoTutor(idLaura, idMimi);
        tutor.removerVinculoTutor(idLaura, idBatatinha);
        System.out.println("Vínculos de Laura: " + tutor.consultarPets(idLaura));
        pet.removerVinculoPet(idBatatinha, idLaura);
        System.out.println("Vínculos de Batatinha: " + pet.consultarTutores(idBatatinha));
        pet.removerVinculoPet(idMimi, idLaura);
        System.out.println("Vínculos de Mimi: " + pet.consultarTutores(idMimi));

        //Apagando Tutores e pets
        pet.removerPet(idMimi);
        pet.removerPet(idBatatinha);
        tutor.removerTutor(idLaura);
        tutor.removerTutor(idTiago);
        tutor.removerTutor(idGustavo);
    }
}
