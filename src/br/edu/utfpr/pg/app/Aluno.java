
package br.edu.utfpr.pg.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Vinicius
 * @UTFPR
 */

public class Aluno {

    private Integer ra;
    private String nome;
    private String curso;
    private Integer periodo;
    private Double coeficiente;
    private String situacao;

    public boolean cadastrarAluno() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.prepareStatement("INSERT INTO aluno(ra, nome, curso, periodo, coeficiente, situacao) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, this.getRa());
            stmt.setString(2, this.getNome());
            stmt.setString(3, this.getCurso());
            stmt.setInt(4, this.getPeriodo());
            stmt.setDouble(5, this.getCoeficiente());
            stmt.setString(6, this.getSituacao());

            stmt.execute();
            return true;

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public boolean atualizarCadastroAluno() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.prepareStatement("UPDATE aluno SET nome = ?, curso = ?, periodo = ?, coeficiente = ?, situacao = ? WHERE ra = ?");
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getCurso());
            stmt.setInt(3, this.getPeriodo());
            stmt.setDouble(4, this.getCoeficiente());
            stmt.setString(5, this.getSituacao());
            stmt.setInt(6, this.getRa());

            stmt.execute();
            return true;

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public boolean excluirCadastroAluno() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.prepareStatement("DELETE FROM aluno WHERE aluno.ra = ?");
            stmt.setInt(1, this.getRa());

            stmt.execute();
            return true;

        } finally {

            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public Aluno consultarCadastroAluno(int ra) throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM aluno WHERE aluno.ra = " + ra;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Aluno aluno = null;

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {

                aluno = new Aluno();

                aluno.setRa(rs.getInt("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setPeriodo(rs.getInt("periodo"));
                aluno.setCoeficiente(rs.getDouble("coeficiente"));
                aluno.setSituacao(rs.getString("situacao"));
            }

            return aluno;

        } finally {

            if ((rs != null) && !rs.isClosed()) {
                rs.close();
            }
            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public ArrayList<Aluno> listarAlunos() throws ClassNotFoundException, SQLException {

        String sql = "SELECT * FROM aluno";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Aluno> alunos = new ArrayList<>();

        try {

            Conexao conexao = new Conexao();
            conn = conexao.conectar();

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Aluno aluno = new Aluno();

                aluno.setRa(rs.getInt("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCurso(rs.getString("curso"));
                aluno.setPeriodo(rs.getInt("periodo"));
                aluno.setCoeficiente(rs.getDouble("coeficiente"));
                aluno.setSituacao(rs.getString("situacao"));

                alunos.add(aluno);
            }

            return alunos;

        } finally {

            if ((rs != null) && !rs.isClosed()) {
                rs.close();
            }
            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
