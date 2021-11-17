/**
 * 
 */
package controllers;

import java.util.ArrayList;

import models.Professor;
import shared.ADatabase;
import shared.IDao;
import shared.database.MySqlDatabase;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author enio
 *
 */
public class ProfessorController implements IDao<Professor> {
	
	ArrayList<Professor> professores = new ArrayList<Professor>();
	
	private Connection connection = null;
	private PreparedStatement pstdados = null;
	private static final String sqlinserir = "INSERT INTO professor (id, nome, email, disciplina) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE clientes SET nome = ?, nomepet = ?, email = ?, datecreated = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM clientes WHERE id = ?";
	
	public ProfessorController() {
		String path = System.getProperty("user.dir");
        File fileName = new File(path + "/src/shared/database/configBd.properties");
		try {
			ADatabase.init(fileName);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Professor> Lista() {
		// TODO Auto-generated method stub
		return (ArrayList<Professor>) professores.clone();
	}
	

	
	@Override
	public boolean Cria(Professor professor) {
        try {
            connection = MySqlDatabase.getConnection();
            connection.setAutoCommit(false);
            pstdados.setInt(1, professor.getId());
            pstdados.setString(2, professor.getNome());
            pstdados.setString(3, professor.getEmail());
            pstdados.setString(4, professor.getDisciplina());
            pstdados = (PreparedStatement) connection.prepareStatement(sqlinserir, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            pstdados.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return professores.add(professor);
	}
	
	@Override
	public Professor Modificar(Professor professor) {
		int index = 0;
		
		for(Professor prof : professores) {
			if(prof.getId() == professor.getId())
				index = professores.indexOf(prof);
		}
		
		return professores.set(index, professor);
	}
	
	@Override
	public Professor Excluir(Professor professor) {
		int index = 0;
		
		for(Professor prof : professores) {
			if(prof.getId() == professor.getId())
				index = professores.indexOf(prof);
		}
		
		return professores.remove(index);
	}
}
