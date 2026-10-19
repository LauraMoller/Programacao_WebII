package Model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Representa o tutor e seus dados.
 * Os pets do tutor são guardados apenas como ids (sem referência direta aos
 * objetos).
 * Todos os set lançam IllegalArgumentException quando o valor informado é
 * inválido.
 */
public class TutorModel {
    /**
     * Variável de instância que armazena o nome do tutor.
     */
    private String nome;
    /**
     * Variável de instância que armazena o telefone do tutor.
     */
    private String telefone;
    /**
     * Variável de instância que armazena o endereço do tutor.
     */
    private String endereco;
    /**
     * Variável de instância que armazena a lista de ids dos pets do tutor.
     */
    private final Set<Integer> pets = new LinkedHashSet<>();

    /**
     * Cria um tutor.
     * 
     * @param nome     nome do tutor.
     * @param telefone telefone do tutor.
     * @param endereco endereço do tutor.
     * @throws IllegalArgumentException caso algum dos valores seja inválido.
     */
    public TutorModel(String nome, String telefone, String endereco) throws IllegalArgumentException {
        setNome(nome);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    /**
     * Define o nome do tutor.
     * 
     * @param nome nome do tutor
     * @throws IllegalArgumentException caso o nome seja inválido.
     */
    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Erro: o nome do tutor precisa ser informado.");
        }
        this.nome = nome;
    }

    /**
     * Define o telefone do tutor.
     * 
     * @param telefone telefone do tutor.
     * @throws IllegalArgumentException caso o telefone seja inválido.
     */
    public void setTelefone(String telefone) throws IllegalArgumentException {
        if (telefone == null || !telefone.matches("^[1-9][1-9](9\\d{8}|[2-5]\\d{7})$")) {
            throw new IllegalArgumentException("Erro: telefone inválido - informe dd e número (somente dígitos).");
        }
        this.telefone = telefone;
    }

    /**
     * Define o endereço do tutor.
     * 
     * @param endereco endereço do tutor.
     * @throws IllegalArgumentException caso o endereço não seja informado.
     */
    public void setEndereco(String endereco) throws IllegalArgumentException {
        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Erro: o endereço do tutor precisa ser informado.");
        }
        this.endereco = endereco;
    }

    /**
     * Acessa o nome do tutor.
     * 
     * @return nome do tutor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Acessa o telefone do tutor.
     * 
     * @return telefone do tutor.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Acessa o endereço do tutor.
     * 
     * @return endereço do tutor.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Acessa a lista de pets do tutor.
     * 
     * @return lista de pets do tutor.
     */
    public Set<Integer> getPets() {
        return pets;
    }
}