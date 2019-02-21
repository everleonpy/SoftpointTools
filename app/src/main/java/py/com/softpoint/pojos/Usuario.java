package py.com.softpoint.pojos;

import java.io.Serializable;

/**
* 
* Pojo que respesenta a usuarios de dispositivos mibiles
* @author eleon
*
*/

public class Usuario implements Serializable {

	private static final long serialVersionUID = 5879815770232196131L;
	
	private Long id;
	private String name;
	private String nombreCompleto;
	private Long orgId;
	private Long unitId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	
}
