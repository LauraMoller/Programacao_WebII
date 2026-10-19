package Interface;

/**
 * Espécies de animais aceitas no cadastro de pets do Pet Shop.
 * Usado como parâmetro nas operações do serviço Pet e guardado em PetModel.
 * Quando a espécie não é conhecida, usa-se NAO_INFORMADO. Informar espécie como
 * null resulta em IllegalArgumentException.
 */
public enum Especie {
    /**
     * Cachorro.
     */
    CACHORRO,
    /**
     * Gato
     */
    GATO,
    /**
     * Aves (periquito, calopsita, papagaio, etc).
     */
    AVES,
    /**
     * Anfíbios (sapo, rã, salamandra, etc).
     */
    ANFIBIOS,
    /**
     * Roedores (hamster, porquinha-daíndia, coelho, etc)
     */
    ROEDOR,
    /**
     * Répteis (tartaruga, cobra, iguana, etc).
     */
    REPTIL,
    /**
     * Peixes
     */
    PEIXE,
    /**
     * Espécie não informada ou desconhecida.
     */
    NAO_INFORMADO
}
