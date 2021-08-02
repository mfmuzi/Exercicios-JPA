package part3;

import classes.Aluno;
import classes.Aluno_; //Metamodel
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPAPart3 {

    public static void main(String[] args) {

        // Adição de dados à tabela aluno.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(new Estado("Sao Paulo", "SP"));
        entityManager.persist(new Aluno("Daniel", 29, estadoParaAdicionar));
        entityManager.persist(new Aluno("Joao", 20, estadoParaAdicionar));
        entityManager.persist(new Aluno("Pedro", 30, estadoParaAdicionar));
        entityManager.getTransaction().commit();


        // Parâmetro de busca que será utilizado nas consultas.
        String nome = "Daniel";

        // =============================================================================================================

        // Utilizando o método find do entityManager.
        // Retorna um resultado por id.
        Aluno alunoEntityManager = entityManager.find(Aluno.class, 1);

        System.out.println("Consulta alunoEntityManager: " + alunoEntityManager);

        // =============================================================================================================

        // Utilizando SQL nativo.

        // Trazendo somente 1 resultado.
/*
        String sql = "SELECT * FROM Aluno WHERE nome = :nome ";
        Aluno alunoSQL = (Aluno) entityManager
                .createNativeQuery(sql, Aluno.class)
                .setParameter(nome, "nome")
                .getSingleResult();

         System.out.println("Consulta alunoSQL: " + alunoSQL);

        //Trazendo uma lista como resultado.

        String sqlList = "SELECT * FROM Aluno";
        List<Aluno> alunoSQLList = entityManager
                .createNativeQuery(sqlList, Aluno.class)
                .getResultList();

        alunoSQLList.forEach(Aluno -> System.out.println("Consulta alunoSQLList: " + Aluno));*/


        // =============================================================================================================
        // Utilizando JPQL.

        // Trazendo somente 1 resultado.
        String jpql = "select a from Aluno a where a.nome = :nome";
        Aluno alunoJPQL = entityManager
                .createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();

        System.out.println("Consulta aluno selecionado: " + alunoJPQL);

        // Trazendo uma lista como resultado.
        String jpqlList = "select a from Aluno a where a.estado = :estado";
        List<Aluno> alunoJPQLList = entityManager
                .createQuery(jpqlList, Aluno.class)
                .setParameter("estado", estadoParaAdicionar)
                .getResultList();

        alunoJPQLList.forEach(Aluno -> System.out.println("Consulta lista alunos: " + Aluno));

        // =============================================================================================================

        // Utilizando JPA Criteria API + JPA Metamodel.

        // Trazendo somente 1 resultado
        CriteriaQuery<Aluno> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> alunoRoot = criteriaQuery.from(Aluno.class);
        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(alunoRoot.get(Aluno_.nome));
        inClause.value(nome);
        criteriaQuery.select(alunoRoot).where(inClause);
        Aluno alunoAPICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();

        System.out.println("Consulta aluno selecionado: " + alunoAPICriteria);

        // Trazendo uma lista como resultado.
        CriteriaQuery<Aluno> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> alunoRootList = criteriaQueryList.from(Aluno.class);
        List<Aluno> alunoAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();

        alunoAPICriteriaList.forEach(Aluno -> System.out.println("Consulta lista alunos: " + Aluno));

    }

}

