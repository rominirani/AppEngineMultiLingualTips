package com.mindstorm.i18n.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.mindstorm.i18n.entity.Tips;
import com.mindstorm.i18n.util.PMF;

public enum TipsDAO {
	INSTANCE;
	public List<Tips> getTips() {
		List<Tips> Tips;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Tips.class);
		try {
			Tips = (List<Tips>) query.execute();
			System.out.println("Number of Tips: " + Tips.size());
        } finally {
            pm.close();
        }

		return (Tips);
	}
	
	public void deleteAll() {
		List<Tips> Tips;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Tips.class);
		try {
			Tips = (List<Tips>) query.execute();
			pm.deletePersistentAll(Tips);
        } finally {
            pm.close();
        }
	}

	public Long add(String tipCode, 
			        String tipTitle,
			        String tipTitleHindi,
			        List<String> tips, List<String> tipsHindi) {
		
		Tips _Tips = new Tips();
		_Tips.setTipCode(tipCode);
		_Tips.setTipTitle(tipTitle);
		_Tips.setTipTitleHindi(tipTitleHindi);
		_Tips.setTips(tips);
		_Tips.setTipsHindi(tipsHindi);
		synchronized (this) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
	            pm.makePersistent(_Tips);
	        } finally {
	            pm.close();
	        }
		}
		
		return (_Tips.getTipID().getId());
	}

	public void deleteTips(String tipCode) {
		List<Tips> Tips;
		Tips Tip = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Tips.class);
		query.setFilter("tipCode == '" + tipCode + "'");
		try {
			Tips = (List<Tips>) query.execute();
			if (Tips.size() > 0) {
				//Assume only one record returned and return the first one found
				Tip = Tips.get(0);
				pm.deletePersistent(Tip);
			}
		} finally {
            pm.close();
        }
	}

	public Tips getTips(String tipCode) {
		List<Tips> Tips;
		Tips Tip = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Tips.class);
		query.setFilter("tipCode == '" + tipCode + "'");
		try {
			Tips = (List<Tips>) query.execute();
			if (Tips.size() > 0) {
				//Assume only one record returned and return the first one found
				Tip = Tips.get(0);
			}
		} finally {
            pm.close();
        }
		return Tip;
	}

	public String getRandomTip(String tipCode, int language) {
		String strTip = "NONE";
		List<Tips> Tips;
		Tips Tip = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Tips.class);
		query.setFilter("tipCode == '" + tipCode + "'");
		try {
			Tips = (List<Tips>) query.execute();
			if (Tips.size() > 0) {
				Tip = Tips.get(0);
				List<String> currentTips = Tip.getTips();
				int Random = (int)(Math.random()* (currentTips.size()));
				switch (language) {
				case 0:
					strTip = Tip.getTips().get(Random);
					break;
				case 1:
					strTip = Tip.getTipsHindi().get(Random);
					break;
				default:
					strTip = Tip.getTips().get(Random);
					break;
				}
			}
		} finally {
            pm.close();
        }
		return strTip;
	}
}
