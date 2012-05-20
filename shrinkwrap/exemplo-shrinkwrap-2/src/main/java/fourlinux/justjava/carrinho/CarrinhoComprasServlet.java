package fourlinux.justjava.carrinho;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fourlinux.justjava.dominio.CarrinhoCompras;

@WebServlet(urlPatterns={"/compras"})
public class CarrinhoComprasServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CarrinhoCompras compras = new CarrinhoCompras();
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Carrinho de Compras</title></head>");
		out.println("<body>");
		out.println("<h2>Listando Carrinho de Compras: " + compras.getItens().size() + " itens</h2>");
		out.println("</body");
		out.println("</html>");
	}
}
