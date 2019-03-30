package br.com.caleum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.tarefas.Tarefa;
import br.com.caleum.jdbc.FabricaDeConexoes;


public class JdbcTarefaDao {

	private Connection connection;
	private PreparedStatement stmt;
	private ResultSet rs;

	public JdbcTarefaDao() throws ClassNotFoundException {
		this.connection = new FabricaDeConexoes().getConnetion();
	}

	public void adicionar(Tarefa tarefa) {
		String sql = "INSERT INTO tarefas (descricao, finalizado, dataFinalizacao) VALUES (?,?,?)";
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefa> getList() {
		try {
			stmt = connection.prepareStatement("SELECT * FROM tarefas");
			rs = stmt.executeQuery();

			List<Tarefa> tarefa = new ArrayList<>();

			while (rs.next()) {
				Tarefa t = new Tarefa();
				t.setId(rs.getLong("id"));
				t.setDescricao(rs.getString("descricao"));
				t.setFinalizado(rs.getBoolean("finalizado"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				t.setDataFinalizacao(data);
				tarefa.add(t);
			}
			rs.close();
			stmt.close();
			return tarefa;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Tarefa tarefa) {
		String sql = "update tarefas set descricao=?, finalizado=?, dataFinalizacao=? where	id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			stmt.setLong(4, tarefa.getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Tarefa tarefa) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete	" + "from	tarefas	where	id=?");
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Tarefa bucartarefaById(Long id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tarefas WHERE id=?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				Tarefa t = new Tarefa();
				t.setId(rs.getLong("id"));
				t.setDescricao(rs.getString("descricao"));
				t.setFinalizado(rs.getBoolean("finalizado"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataFinalizacao"));
				t.setDataFinalizacao(data);
				
				return t;
			} else {
				return null;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void finalizar(Long id) {
		boolean b = true;
		
		
		String sql = "UPDATE tarefas SET finalizado=?, dataFinalizacao=? WHERE id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, b);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
