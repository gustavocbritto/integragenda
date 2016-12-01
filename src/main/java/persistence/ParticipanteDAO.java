package persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.Participante;


public class ParticipanteDAO extends DAO {


	public ArrayList<Participante> getParticipantes(int idAgenda) throws Exception{
		
		ArrayList<Participante> participantes = new ArrayList<Participante>();

		open();
		
		stmt = con.prepareStatement("SELECT PARTICIPANTE.ID, PARTICIPANTE.EMAIL FROM PARTICIPANTE"+
									" INNER JOIN AGENDA_PARTICIPANTE ON PARTICIPANTE.ID = AGENDA_PARTICIPANTE.IDPARTICIPANTE"+
									" INNER JOIN AGENDA ON AGENDA_PARTICIPANTE.IDAGENDA = AGENDA.ID"+
									" WHERE AGENDA.ID = "+idAgenda);
		
		stmt.executeQuery();
		ResultSet rs = stmt.getResultSet();
		
		while(rs.next())
		{
			Participante participante = new Participante();
			participante.setIdParticipantes(rs.getInt(1));
			participante.setEmail(rs.getString(2));
			participantes.add(participante);
		}
		
		close();
		
		return participantes;
	}

	public void inserir(int idAgenda, Participante participante) throws Exception {
			
		open();
		
		stmt = con.prepareStatement("INSERT INTO PARTICIPANTE (EMAIL) VALUES (?) RETURNING ID;");

		stmt.setString(1, participante.getEmail());
		
		rs = stmt.executeQuery();
		
		while(rs.next())
		{
			participante.setIdParticipantes(rs.getInt(1));
		}
		
		associarAgendaParticipante(idAgenda, participante.getIdParticipantes());
		
		close();
		
	}

	private void associarAgendaParticipante(int idAgenda, int idParticipante) throws Exception{
		
		open();
		
		stmt = con.prepareStatement("INSERT INTO AGENDA_PARTICIPANTE(IDAGENDA, IDPARTICIPANTE) VALUES ("+idAgenda+", "+idParticipante+");");
		
		stmt.execute();
			
		close();
		
	}

	public void remover(Participante participante) throws Exception{
		
		open();
		
		stmt = con.prepareStatement("DELETE FROM PARTICIPANTE WHERE ID = ?;");
		
		stmt.setInt(1, participante.getIdParticipantes());
		
		stmt.execute();
			
		close();
		
	}

	public void desassociarAgendaParticipante(int id, Participante participante) throws Exception{
		
		open();
		
		stmt = con.prepareStatement("DELETE FROM AGENDA_PARTICIPANTE WHERE IDAGENDA = ? AND IDPARTICIPANTE = ?;");
		
		stmt.setInt(1, id);
		stmt.setInt(2, participante.getIdParticipantes());
		
		stmt.execute();
			
		close();
		
		remover(participante);
		
	}

}
