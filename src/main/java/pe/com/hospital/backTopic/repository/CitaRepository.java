package pe.com.hospital.backTopic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.hospital.backTopic.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
	
	@Query("select ci from Cita ci where ci.reserva = 1")
	List<Cita> fetchByCitasDisponibles() throws Exception;
	//List<Cita> findByLugarSeguroEspecialidadHora(String nombreSeguro, String nombreEspecialidad, String nombreLugar, Date hora) throws Exception;
	
	@Query("select ci from Cita ci, Especialidad esp, Seguro se where ci.fecha = :fecha and ci.ubicacion.distrito = :distrito and esp.nombre = :especialidad and se.nombre = :seguro")
	List<Cita> citaIdeal(Date fecha, String distrito, String especialidad, String seguro) throws Exception;
	
	@Query("select ci from Cita ci where ci.paciente.usuario.id = :id")
	List<Cita> findByUSerId(int id) throws Exception;
}
