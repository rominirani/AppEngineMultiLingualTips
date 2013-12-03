
package com.mindstorm.i18n.services;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindstorm.i18n.util.LoadTips;

public class LoadTipsService extends HttpServlet {
	
	private static final Logger _logger = Logger.getLogger(LoadTipsService.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String tipCode = (String)req.getParameter("tipCode");
		String tipTitle = (String)req.getParameter("tipTitle");
		String tipFile = (String)req.getParameter("tipFile");
		String tipTitleHindi = (String)req.getParameter("tipTitleHindi");
		try {
			LoadTips.loadData(tipCode,tipTitle,tipTitleHindi, tipFile);
			res.getWriter().print("Loaded the tips successfully");
		}
		catch (Exception ex) {
			_logger.info(ex.getMessage());
			res.getWriter().print("Exception while loading tips : " + ex.getMessage());
		}		
	}
}
