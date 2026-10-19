

import Controler.Teste;
import jakarta.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        // 1. Define a URL onde a API ficará disponível
        String url = "http://localhost:8080/ws/Teste";
        
        System.out.println("Inicializando o servidor SOAP em: " + url);
        
        // 2. Publica o endpoint associando a URL à classe que implementa o serviço
        // O WSDL será gerado automaticamente em http://localhost:8080/ws/usuarios?wsdl
        Endpoint.publish(url, new Teste());
        
        System.out.println("Servidor SOAP rodando com sucesso!");
    }
}