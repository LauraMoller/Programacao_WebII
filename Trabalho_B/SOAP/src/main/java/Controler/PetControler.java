package Controler;

import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import Interface.Especie;
import Interface.Pet;
import Model.PetModel;

/**
 * 
 * Implementação do serviço SOAP.
 * Guarda os pets em um map (volátil).
 * Cada pet recebe um id sequencial, começando em 1, gerado em criarPet.
 * A validação dos campos fica na classe PetModel. O controller cuida apenas
 * de localizar o tutor pelo id e de manter o vínculo com os tutores, que
 * é guardado como ids.
 * Todas as operações lançam IllegalArgumentException quando algum campo é
 * inválido.
 */
@WebService(endpointInterface = "Interface.Pet")
public class PetControler implements Pet {
    /**
     * Map que relaciona o id do pet com o objeto pet.
     */
    private final Map<Integer, PetModel> pets = new ConcurrentHashMap<>();
    /**
     * Gerador de ids para pets.
     */
    private final AtomicInteger proximoId = new AtomicInteger(1);

    /**
     * Procura um pet nos pets cadastrados pelo seu id.
     * 
     * @param id id do pet.
     * @return objeto pet.
     * @throws IllegalArgumentException caso o pet não exista no sistema.
     */
    private PetModel buscar(int id) throws IllegalArgumentException {
        PetModel pet = pets.get(id);
        if (pet == null) {
            throw new IllegalArgumentException("Erro: pet " + id + " não encontrado.");
        }
        return pet;
    }

    /** {@inheritDoc} */
    @Override
    public int criarPet(String nome, float peso, int idade, Especie especie) {
        PetModel pet = new PetModel(nome, peso, idade, especie);
        int id = proximoId.getAndIncrement();
        pets.put(id, pet);
        return id;
    }

    /** {@inheritDoc} */
    @Override
    public void atualizarDadosPet(int id, String nome, float peso, int idade, Especie especie) {
        PetModel p = buscar(id);
        if (p != null) {
            if (nome != null) {
                p.setNome(nome);
            }

            if (peso > 0) {
                p.setPeso(peso);
            }

            if (idade >= 0) {
                p.setIdade(idade);
            }

            if (especie != especie) {
                p.setEspecie(especie);
            }

        }

    }

    /** {@inheritDoc} */
    @Override
    public List<Integer> consultarTutores(int idPet) {
        return new ArrayList<>(buscar(idPet).getTutores());
    }

    /** {@inheritDoc} */
    @Override
    public void adicionarTutor(int idPet, int idTutor) {
        PetModel p = buscar(idPet);
        if (!p.getTutores().add(idTutor)) {
            throw new IllegalArgumentException("Erro: tutor já vinculado a este pet.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public String consultarDadosPet(int id) {
        PetModel p = buscar(id);
        return "Nome: " + p.getNome() + "\n"
                + "Idade: " + p.getIdade() + "\n"
                + "Peso: " + p.getPeso() + "\n"
                + "Espécie: " + p.getEspecieString() + "\n";
    }

    /** {@inheritDoc} */
    @Override
    public void removerVinculoPet(int idPet, int idTutor) {
        if (!buscar(idPet).getTutores().remove(idTutor)) {
            throw new IllegalArgumentException("Erro: tutor não encontrado neste pet.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void removerPet(int idPet) {
        buscar(idPet);
        pets.remove(idPet);
    }

}
