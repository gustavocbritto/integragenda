package persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Agenda;
import model.Participante;


public class AgendaDAO extends DAO {

	public ArrayList<Agenda> getAgendaUsuario(int idUsuario) throws Exception{
		
		ArrayList<Agenda> agendas = new ArrayList<Agenda>();
		SalaDAO salaDAo = new SalaDAO();
		ParticipanteDAO participanteDAO = new ParticipanteDAO();
		open();
		
		st = con.createStatement();
		rs = st.executeQuery("SELECT AGENDA.ID, HORAINICIO, HORAFIM, IDSALA, STATUS, DATAINICIO, DATAFIM"+
									" FROM AGENDA" +
									" WHERE IDLOCADOR = "+idUsuario);
		while(rs.next())
		{
			Agenda agenda = new Agenda();
			agenda.setId(rs.getInt(1));
			agenda.setHoraInicio(rs.getString(2));
			agenda.setHoraFim(rs.getString(3));
			agenda.setSala(salaDAo.consulta(rs.getInt(4)));
			agenda.setStatus(rs.getInt(5) == 1);
			agenda.setDataInicio(rs.getDate(6));
			agenda.setDataFim(rs.getDate(7));		
			agenda.setParticipantes(participanteDAO.getParticipantes(agenda.getId()));
			agendas.add(agenda);
		}
		
		close();
		
		return agendas;
	}

	public void salvarStatus(Agenda agenda) throws Exception {
		
		int statusUpdate = 0;
		if (agenda.getStatus())
			statusUpdate = 1;
		
		open();
		
		st = con.createStatement();
		st.executeUpdate("UPDATE AGENDA SET STATUS = "+statusUpdate+" WHERE AGENDA.ID = "+agenda.getId());
		
		close();
		
	}
	
	public void desassociarParticipantesAgenda(int idAgenda, List<Participante> participantes) throws Exception
	{
		ParticipanteDAO participanteDAO = new ParticipanteDAO();
		
		for(Participante p : participantes)
		{
			participanteDAO.desassociarAgendaParticipante(idAgenda, p);
		}
		
	}

	public void remover(Agenda agenda) throws Exception 
	{
		desassociarParticipantesAgenda(agenda.getId(), agenda.getParticipantes());
		open();
		
		st = con.createStatement();
		st.executeUpdate("DELETE FROM AGENDA WHERE ID = "+agenda.getId());
		
		close();
	}

	public void salvar(Agenda agenda, int idLocador) throws Exception{
		
		open();
		
		stmt = con.prepareStatement("INSERT INTO AGENDA (IDSALA, STATUS, DATAINICIO, DATAFIM, IDLOCADOR, HORAINICIO, HORAFIM) VALUES (?,?,?,?,?,?,?) RETURNING ID;");
		
		int status = 0;
		if(agenda.getStatus())
			status = 1;
		
		stmt.setInt(1, agenda.getSala().getIdSala());
		stmt.setInt(2, status);
		stmt.setDate(3, agenda.getDataInicio());
		stmt.setDate(4, agenda.getDataFim());
		stmt.setInt(5, idLocador);
		stmt.setString(6, agenda.getHoraInicio());
		stmt.setString(7, agenda.getHoraFim());
				
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next())
		{
			int idAgenda = rs.getInt(1);
			agenda.setId(idAgenda);
		}
		
		close();
		
	}

}
