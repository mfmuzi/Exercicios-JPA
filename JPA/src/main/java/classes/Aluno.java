package classes;

import javax.persistence.*;

@Entity //diz que essa classe Aluno tem uma representação dela no BD.
public class Aluno {

    @Id //representação no BD
    @GeneratedValue(strategy= GenerationType.IDENTITY) // A ID vai ser gerada pelo BD e não pelo JAVA
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    //Como Aluno e Estado são classes diferentes, usa as anotações de relacionamento.
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne porque pode ter vários alunos para um mesmo estado.
    private Estado estado;

    public Aluno() { }

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Aluno(String nome, int idade, Estado estado) {
        this.nome = nome;
        this.idade = idade;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", estado=" + estado +
                '}';
    }
}
