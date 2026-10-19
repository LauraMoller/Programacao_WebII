package Publisher;

import Controler.*;
import jakarta.xml.ws.Endpoint;

/**
 * Publicador dos serviços SOAP em um servidor HTTP local.
 * Os serviços ficam no ar enquanto este programa estiver em execução.
 * Ao encerrá-lo, os dados em memória são perdidos.
 * É necessário executar antes do cliente.
 * 
 */
public class Publicador {
    /**
     * Ponto de entrada: publica os serviços Pet e Tutor e mostra no console as URLs dos respectivos WSDLs.
     * @param args
     */
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9876/pet",
                new PetControler());

        Endpoint.publish("http://localhost:9876/tutor",
                new TutorControler());

        System.out.println("WSDL do Pet:   http://localhost:9876/pet?wsdl");
        System.out.println("WSDL do Tutor: http://localhost:9876/tutor?wsdl");
    }
}
