package part1;

import classes.Aluno;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAPart1 {
    public static void main(String[] args) {

        // Caso não tenha nenhuma implementação habilitada no build.gradle será retornado um erro,
        // pois aqui tem apenas o JPA puro, sem nenhuma implementação.
        // Com o hibernate habilitado é feita a adição do aluno no BD.

        // Criando um gerenciador de entidades com o BD especificado no arquivo "persistence.xml".
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part1-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Criando instâncias para serem adicionadas no BD.
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        Aluno alunoParaAdicionar = new Aluno("Daniel", 29, estadoParaAdicionar);

        // Iniciando uma trasação para adicionar as instâncias no BD.
        entityManager.getTransaction().begin();

        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar);

        entityManager.getTransaction().commit();

        // Encerrando o gerenciador de entidades e fábrica de gerenciadores de entidade.
        entityManager.close();
        entityManagerFactory.close();

    }
}
