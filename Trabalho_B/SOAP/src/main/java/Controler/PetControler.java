package Controler;

import Model.Especie;
import Model.Pet;
import Model.Tutor;

import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;

public class PetControler implements Pet {
    private String nome = "";
    private float peso = 0;
    private Integer idade = null;
    private Especie especie = Especie.NAO_INFORMADO;
    private List<Tutor> tutores = new ArrayList<>();

    public PetControler(String nome, float peso, int idade, Especie especie) {
        setNome(nome);
        setPeso(peso);
        setIdade(idade);
        setEspecie(especie);
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Erro: o nome precisa ser informado.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPeso(float peso) throws IllegalArgumentException {
        if (peso <= 0) {
            throw new IllegalArgumentException("Erro: o peso precisa ser maior que zero.");
        }
        this.peso = peso;
    }

    public float getPeso() {
        return peso;
    }

    public void setIdade(Integer idade) throws IllegalArgumentException {
        if (idade < 0) {
            throw new IllegalArgumentException("Erro: a idade precisa ser maior ou igual a zero.");
        }
        this.idade = idade;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setEspecie(Especie especie) throws IllegalArgumentException {
        if (especie == null) {
            throw new IllformedLocaleException("Erro: espécie precisa ser informada.");
        }

        this.especie = especie;
    }

    public Especie getEspecieENUM() {
        return especie;
    }

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


    @Override
    public void atualizarDadosPet(String nome, float peso, Integer idade, Especie especie) throws IllegalArgumentException {
        if (!nome.isBlank()) {
            setNome(nome);
        }

        if (peso >= 0) {
            setPeso(peso);
        }

        if (!(idade == null)) {
            setIdade(idade);
        }

        if (!(especie == null)) {
            setEspecie(especie);
        }
    }

    @Override
    public List<Tutor> consultarTutores() {
        return tutores;
    }

    @Override
    public void adicionarTutor(Tutor tutor) throws IllegalArgumentException {
        if (tutor == null) {
            throw new IllegalArgumentException("Erro: o tutor precisa ser informado.");
        }
        tutores.add(tutor);
    }

    @Override
    public String consultarDadosPet() {
        String dados = "Nome: " + getNome() + "\n";
        dados += "Idade: " + getIdade() + "\n";
        dados += "Peso: " + getPeso() + "\n";
        dados += "Espécie: " + getEspecieString() + "\n";
        return dados;
    }

    @Override
    public void removerTutor(Tutor tutor) throws IllegalArgumentException {
        if (!(tutores.contains(tutor))) {
            throw new IllegalArgumentException("Erro: Tutor não encontrado.");
        }
        tutores.remove(tutor);
    }
}
