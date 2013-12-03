package com.mindstorm.i18n.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.mindstorm.i18n.dao.TipsDAO;

public class LoadTips {
	private static final Logger _logger = Logger.getLogger(LoadTips.class.getName());
	
	public static void loadData(String tipsCode, String tipsTitle, String tipsTitleHindi,String fileName) {
		try {
			BufferedReader rdr = new BufferedReader(new InputStreamReader(new FileInputStream("WEB-INF/sampleData/"+fileName+".txt")));
			String nextLine;
			
			//English Tips
			List<String> tipsEnglish = new ArrayList<String>();
			while ((nextLine = rdr.readLine()) != null) {
				String data = nextLine;
				tipsEnglish.add(data);
			}
			rdr.close();
			
			//Hindi Tips
			List<String> tipsHindi = new ArrayList<String>();
			tipsHindi.clear();
			rdr = new BufferedReader(new InputStreamReader(new FileInputStream("WEB-INF/sampleData/"+fileName + "_hindi.txt"),"UTF-8"));
			while ((nextLine = rdr.readLine()) != null) {
				String data = nextLine;
				tipsHindi.add(data);
			}
			rdr.close();
			
			//First remove the tips
			TipsDAO.INSTANCE.deleteTips(tipsCode);

			//Then add them
			TipsDAO.INSTANCE.add(tipsCode, tipsTitle, tipsTitleHindi,tipsEnglish,tipsHindi);
			
		} catch (Exception e) {
			_logger.info("Exception in loading tips : " + e.getMessage());
		} 
	}
}
