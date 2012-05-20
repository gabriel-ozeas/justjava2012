package fourlinux.justjava;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "leilao" })
public class LeilaoServlet extends HttpServlet {
	@Inject
	private GerenciadorLeilao leilao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		mostrarLances(req, resp);
	}

	private void mostrarLances(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String itemId =  request.getParameter("item-id");
		if (itemId != null) {
			request.setAttribute("historico", leilao.getHistorico(itemId));
		}
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/item-historico.jsp");
		view.forward(request, response);
	}
}
