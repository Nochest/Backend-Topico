package pe.com.hospital.backTopic.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medico")
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", length = 20, nullable = false)
	private String nombre;
	@Column(name = "apellido_paterno", length = 20, nullable = false)
	private String apellidoPaterno;
	@Column(name = "apellido_materno", length = 20, nullable = false)
	private String apellidoMaterno;
	@JsonIgnore
	@OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
	private List<Cita> citas;
	@JsonIgnore
	@OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
	private List<MedicoEspecialidad> medicoEspecialidades;

	@Lob
	@Column(name = "imagen")
	private byte[] img;
	
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

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public List<MedicoEspecialidad> getMedicoEspecialidades() {
		return medicoEspecialidades;
	}

	public void setMedicoEspecialidades(List<MedicoEspecialidad> medicoEspecialidades) {
		this.medicoEspecialidades = medicoEspecialidades;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	
}