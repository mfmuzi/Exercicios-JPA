package part2;

import classes.Aluno;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAPart2 {
    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Adições na tabela Aluno.
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        Aluno alunoParaAdicionar = new Aluno("Daniel", 29, estadoParaAdicionar);
        Aluno alunoParaAdicionar2 = new Aluno("Maria", 19, estadoParaAdicionar);


        // Iniciar uma transação de adição.
        entityManager.getTransaction().begin();

        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar);
        entityManager.persist(alunoParaAdicionar2);


        entityManager.getTransaction().commit();

        // Resgatar instâncias no BD.
        Estado estadoEncontrado = entityManager.find(Estado.class, 1);
        Aluno alunoEncontrado = entityManager.find(Aluno.class, 1);
        Aluno alunoEncontrado2 = entityManager.find(Aluno.class, 2);

        System.out.println(estadoEncontrado);
        System.out.println(alunoEncontrado);
        System.out.println(alunoEncontrado2);

        // Alterar uma entidade no BD.
        entityManager.getTransaction().begin();

        alunoEncontrado.setNome("Joao"); //muda através do objeto alunoEncontrado que se refere ao Daniel.
        alunoEncontrado.setIdade(20);

        entityManager.getTransaction().commit();

        // Remover uma entidade do BD.
        entityManager.getTransaction().begin();

        entityManager.remove(alunoEncontrado);// Vai remover o aluno que se refere ao alunoEncontrado.

        entityManager.getTransaction().commit();

        // 6 - Encerra o gerenciador e a fábrica de entidades.
        entityManager.close();
        entityManagerFactory.close();

    }
}

