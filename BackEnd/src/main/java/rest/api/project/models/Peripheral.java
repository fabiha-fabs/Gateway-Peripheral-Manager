package rest.api.project.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Peripheral {

	@Id
	@NotNull
	private String uid;
	private String vendor;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="YYYY-dd-MM")
	private Date createdDate;
	
	private String status;
	
	@ManyToOne
	private Gateway gateway;
	
	public Peripheral() {}
	
	public Peripheral(String uid, String vendor, Date createdDate, String status, String serialNo){
		this.uid = uid;
		this.vendor = vendor;
		this.createdDate = createdDate;
		this.status = status;
		this.gateway = new Gateway(serialNo, "", "");
	}
	
	public Peripheral(String uid, String vendor, Date createdDate, String status){
		this.uid = uid;
		this.vendor = vendor;
		this.createdDate = createdDate;
		this.status = status;
		this.gateway = null;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}
	
	
}
