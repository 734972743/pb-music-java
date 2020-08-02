package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="t_cyric")
@Entity
public class Cyric implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)   //表示是ID自增
	private Integer id;
	private String cyricName;
	private String cyricContent;
	private String cyricAuthor;
	private Date createDate;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCyricName() {
		return cyricName;
	}
	public void setCyricName(String cyricName) {
		this.cyricName = cyricName;
	}
	public String getCyricContent() {
		return cyricContent;
	}
	public void setCyricContent(String cyricContent) {
		this.cyricContent = cyricContent;
	}
	public String getCyricAuthor() {
		return cyricAuthor;
	}
	public void setCyricAuthor(String cyricAuthor) {
		this.cyricAuthor = cyricAuthor;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
	
}
