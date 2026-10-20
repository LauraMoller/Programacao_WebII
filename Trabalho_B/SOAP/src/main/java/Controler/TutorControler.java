package Controler;

import Interface.Tutor;
import Model.TutorModel;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * Implementação do serviço SOAP.
 * Guarda os tutores em um map (volátil).
 * Cada tutor recebe um id sequencial, começando em 1, gerado em criarTutor.
 * A validação dos campos fica na classe TutorModel. O controller cuida apenas
 * de localizar o tutor pelo id e de manter o vínculo com os pets, que
 * é guardado como ids.
 * Todas as operações lançam IllegalArgumentException quando algum campo é
 * inválido.
 */
@WebService(endpointInterface = "Interface.Tutor")
public class TutorControler implements Tutor {
    /**
     * Map que relaciona o id do tutor ao tutor em si.
     */
    private final Map<Integer, TutorModel> tutores = new ConcurrentHashMap<>();
    /**
     * Gerador dos ids.
     */
    private final AtomicInteger proximoId = new AtomicInteger(1);

    /**
     * Localiza um tutor pelo id.
     * 
     * @param id id do tutor.
     * @return o objeto tutor.
     * @throws IllegalArgumentException caso o tutor não exista no sistema.
     */
    private TutorModel buscar(int id) throws IllegalArgumentException {
        TutorModel tutor = tutores.get(id);
        if (tutor == null) {
            throw new IllegalArgumentException("Erro: tutor " + id + " não encontrado.");
        }
        return tutor;
    }

    /** {@inheritDoc} */
    @Override
    public int criarTutor(String nome, String telefone, String endereco) {
        TutorModel tutor = new TutorModel(nome, telefone, endereco); // valida nos sets
        int id = proximoId.getAndIncrement();
        tutores.put(id, tutor);
        return id;
    }

    /** {@inheritDoc} */
    @Override
    public String consultarDadosTutor(int id) {
        TutorModel t = buscar(id);
        if (t != null) {
            return "Nome: " + t.getNome() + "\n"
                    + "Telefone: " + t.getTelefone() + "\n"
                    + "Endereço: " + t.getEndereco() + "\n";
        }
        return "Tutor inválido.";
    }

    /** {@inheritDoc} */
    @Override
    public void atualizarDadosTutor(int id, String nome, String telefone, String endereco) {
        TutorModel t = buscar(id);
        if (t != null) {
            if (nome != null) {
                t.setNome(nome);
            }

            if (telefone != null) {
                t.setTelefone(telefone);
            }

            if (endereco != null) {
                t.setEndereco(endereco);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void removerTutor(int id) {
        buscar(id);
        tutores.remove(id);
    }

    /** {@inheritDoc} */
    @Override
    public void adicionarPet(int idTutor, int idPet) {
        if (!buscar(idTutor).getPets().add(idPet)) {
            throw new IllegalArgumentException("Erro: pet já vinculado a este tutor.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<Integer> consultarPets(int idTutor) {
        return new ArrayList<>(buscar(idTutor).getPets());
    }

    /** {@inheritDoc} */
    @Override
    public void removerVinculoTutor(int idTutor, int idPet) {
        if (!buscar(idTutor).getPets().remove(idPet)) {
            throw new IllegalArgumentException("Erro: pet não encontrado neste tutor.");
        }
    }
}