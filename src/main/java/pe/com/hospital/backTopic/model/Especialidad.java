package pe.com.hospital.backTopic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "especialidad")
public class Especialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	@JsonIgnore
	@OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY)
	private List<MedicoEspecialidad> medicoEspecialidades;

	public Especialidad() {
		this.medicoEspecialidades = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MedicoEspecialidad> getMedicoEspecialidades() {
		return medicoEspecialidades;
	}

	public void setMedicoEspecialidades(List<MedicoEspecialidad> medicoEspecialidades) {
		this.medicoEspecialidades = medicoEspecialidades;
	}
}
