package com.Servlet;


import java.io.IOException;
import java.lang.reflect.Method;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

//���๹��Servlet�Ķ�������������ﵽͨ������servlet�����������ö�Ӧ����
public class BaseServlet extends HttpServlet {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1����÷�������
		String methodName = request.getParameter("method");
		Method method = null;
 
		// 2��ͨ���������ͷ�������Ҫ�Ĳ������Method����
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("���õķ�����" + methodName + "������", e);
		}
 
		// 3��ͨ��Method������÷���
		try {
			String result = (String) method.invoke(this, request, response);
			if (result != null && result.trim().length() > 0) {// ������ص�result��Ϊ��
				int index = result.indexOf(":");// ��õ�һ��ð�ŵ�λ��
				if (index == -1) {// ���û��ð�ţ���ʹ��ת��
					request.getRequestDispatcher(result).forward(request, response);
				} else {// �����ð��
					String start = result.substring(0, index);// ��ȡǰ׺
					String path = result.substring(index + 1);// ��ȡ·��
					if (start.equalsIgnoreCase("f")) {// ǰ׺Ϊf��ʾʹ��ת��
						request.getRequestDispatcher(path).forward(request, response);
					} else if (start.equalsIgnoreCase("r")) {// ǰ׺Ϊr��ʾʹ���ض���
						response.sendRedirect(request.getContextPath() + path);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
