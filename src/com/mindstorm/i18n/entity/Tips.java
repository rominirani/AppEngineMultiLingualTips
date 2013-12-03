package com.mindstorm.i18n.entity;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable="true")
public class Tips {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key tipID;
	@Persistent
	private String tipCode; 
	@Persistent
	private String tipTitle;
	@Persistent
	private String tipTitleHindi;
	@Persistent(defaultFetchGroup = "true")
	private List<String> tips;
	@Persistent(defaultFetchGroup = "true")
	private List<String> tipsHindi;
	public Key getTipID() {
		return tipID;
	}
	public void setTipID(Key tipID) {
		this.tipID = tipID;
	}
	public String getTipCode() {
		return tipCode;
	}
	public void setTipCode(String tipCode) {
		this.tipCode = tipCode;
	}
	public String getTipTitle() {
		return tipTitle;
	}
	public void setTipTitle(String tipTitle) {
		this.tipTitle = tipTitle;
	}
	public String getTipTitleHindi() {
		return tipTitleHindi;
	}
	public void setTipTitleHindi(String tipTitleHindi) {
		this.tipTitleHindi = tipTitleHindi;
	}
	public List<String> getTips() {
		return tips;
	}
	public void setTips(List<String> tips) {
		this.tips = tips;
	}
	public List<String> getTipsHindi() {
		return tipsHindi;
	}
	public void setTipsHindi(List<String> tipsHindi) {
		this.tipsHindi = tipsHindi;
	}
	
}
