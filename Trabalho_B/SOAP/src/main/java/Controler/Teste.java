package Controler;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class Teste {

    @WebMethod
    public String olamundo() {
        return "ola mundo";
    }
}
