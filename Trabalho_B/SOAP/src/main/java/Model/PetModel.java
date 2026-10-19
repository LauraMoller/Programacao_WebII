package Model;

import java.util.LinkedHashSet;
import java.util.Set;
import Interface.*;

/**
 * Representa um pet e seus dados.
 * Os tutores do pet são guardados apenas como ids (sem referência direta aos
 * objetos).
 * Todos os set lançam IllegalArgumentException quando o valor informado é
 * inválido.
 * 
 */
public class PetModel {
    /**
     * Variável de instância que armazena o nome do pet.
     */
    private String nome;
    /**
     * Variável de instância que armazena o peso do pet.
     */
    private float peso;
    /**
     * Variável de instância que armazena o id do pet.
     */
    private int idade;
    /**
     * Variável de instância que armazena a espécia do pet.
     */
    private Especie especie = Especie.NAO_INFORMADO;
    /**
     * Variável de instância que armazena os ids dos tutores na ordem que foram
     * inseridos.
     */
    private final Set<Integer> tutores = new LinkedHashSet<>();

    /**
     * Cria um pet validando todos os campos.
     * 
     * @param nome    nome do pet.
     * @param peso    peso do pet.
     * @param idade   idade do pet.
     * @param especie espécie do pet.
     * @throws IllegalArgumentException caso exista algum campo inválido.
     */
    public PetModel(String nome, float peso, int idade, Especie especie) throws IllegalArgumentException {
        setNome(nome);
        setPeso(peso);
        setIdade(idade);
        setEspecie(especie);
    }

    /**
     * Define o nome do pet.
     * 
     * @param nome nome do pet.
     * @throws IllegalArgumentException caso o nome esteja vázio.
     */
    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Erro: o nome precisa ser informado.");
        }
        this.nome = nome;
    }

    /**
     * Define o peso do pet.
     * 
     * @param peso peso do pet.
     * @throws IllegalArgumentException caso o peso seja negativo.
     */
    public void setPeso(float peso) throws IllegalArgumentException {
        if (peso <= 0) {
            throw new IllegalArgumentException("Erro: o peso precisa ser maior que zero.");
        }
        this.peso = peso;
    }

    /**
     * Define a idade do pet.
     * 
     * @param idade idade do pet.
     * @throws IllegalArgumentException caso a idade seja negativa.
     */
    public void setIdade(int idade) throws IllegalArgumentException {
        if (idade < 0) {
            throw new IllegalArgumentException("Erro: a idade precisa ser maior ou igual a zero.");
        }
        this.idade = idade;
    }

    /**
     * Define a espécie do pet.
     * 
     * @param especie espécie do pet.
     * @throws IllegalArgumentException caso não tenha sido informada uma das opções
     *                                  de espécie.
     */
    public void setEspecie(Especie especie) throws IllegalArgumentException {
        if (especie == null) {
            throw new IllegalArgumentException("Erro: espécie precisa ser informada.");
        }
        this.especie = especie;
    }

    /**
     * Acessa o nome do pet.
     * 
     * @return nome do pet.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Acessa o peso do pet.
     * 
     * @return peso do pet.
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Acessa a idade do pet.
     * 
     * @return idade do pet.
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Acessa a espécie do pet.
     * 
     * @return
     */
    public Especie getEspecie() {
        return especie;
    }

    /**
     * Acessa os tutores do pet.
     * 
     * @return lista de id dos tutores.
     */
    public Set<Integer> getTutores() {
        return tutores;
    }

    /**
     * Transforma a Espécie de Enum para texto.
     * 
     * @return nome da espécie em texto.
     */
    public String getEspecieString() {
        switch (especie) {
            case CACHORRO:
                return "Cachorro";
            case GATO:
                return "Gato";
            case AVES:
                return "Aves";
            case ANFIBIOS:
                return "Anfíbios";
            case ROEDOR:
                return "Roedor";
            case REPTIL:
                return "Réptil";
            case PEIXE:
                return "Peixe";
            default:
                return "Não Informado";
        }
    }
}