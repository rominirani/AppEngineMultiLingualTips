
package com.mindstorm.i18n.services;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mindstorm.i18n.dao.TipsDAO;
import com.mindstorm.i18n.entity.Tips;

public class TipsService extends HttpServlet {
	
	private static final Logger _logger = Logger.getLogger(TipsService.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//If tipCode is provided, this is a specific row else all rows
		String tipCode = req.getParameter("tipCode");
		String tipRandom = req.getParameter("tipRandom");
		String tipLanguage = req.getParameter("tipLanguage");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json; charset=UTF-8");
		try {
			if (tipCode != null && !tipCode.equals("")) {
				if (tipRandom != null && tipRandom.equals("Y")) {
					String tip = TipsDAO.INSTANCE.getRandomTip(tipCode,Integer.parseInt(tipLanguage));
					res.getWriter().print(new Gson().toJson(tip));
				}
				else {
					Tips tip = TipsDAO.INSTANCE.getTips(tipCode);
					res.getWriter().print(new Gson().toJson(tip));
				}
			}
			else {
				List<Tips> tips = TipsDAO.INSTANCE.getTips();
				res.getWriter().print("{\"results\":" +new Gson().toJson(tips)+"}");
			}
		} catch (Exception e) {
			_logger.info(e.getMessage());
			res.getWriter().println("{status:\"failed\"}");
		}
	}
}
