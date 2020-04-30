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
@Table(name = "clinica")
public class Clinica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;
	@Column(name = "descripcion", nullable = true)
	private String descripcion;
	@Column(name = "telefono", length = 9, nullable = false)
	private String telefono;
	@JsonIgnore
	@OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
	private List<Ubicacion> ubicaciones;
	@JsonIgnore
	@OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
	private List<SeguroClinica> seguroClinicas;

	public List<SeguroClinica> getSeguroClinicas() {
		return seguroClinicas;
	}

	public void setSeguroClinicas(List<SeguroClinica> seguroClinicas) {
		this.seguroClinicas = seguroClinicas;
	}

	public Clinica() {
		this.ubicaciones = new ArrayList<>();
		this.seguroClinicas = new ArrayList<>();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
}
