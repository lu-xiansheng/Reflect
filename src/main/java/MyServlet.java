import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *servlet
 * Created by lz on 2017/3/31.
 */
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//利用反射机制，将表单提交的内容与Bean一一对应
        UserBean bean = (UserBean)ReflectTool.initBean(req, "UserBean");
        System.out.println(bean.getUserName()+" "+bean.getPassWord());
    }
}
