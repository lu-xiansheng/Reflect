import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**用来反射form表单，传来的数据映射到Bean。
 * Created by lz on 2017/3/31.
 */
public class ReflectTool {

    public static Object initBean(HttpServletRequest request, String className) {
        Object obj = null;
        try {
            //根据类名来得到一个Class对象
            Class<?> cls =  Class.forName(className);
            //根据Class创建实例
            obj = cls.newInstance();
            //获取该类所有成员变量
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                //获取成员变量的名字
                String fieldName = field.getName();
                //成员变量的类型
                Class<?> fCls = field.getType();
                //获取set方法
                Method method = cls.getDeclaredMethod("set"+fieldName.substring(0,1)
                        .toUpperCase()+fieldName.substring(1), fCls);
                //set方法的参数，从前端传回来的，对应<input name 字段。
                String value = request.getParameter(fieldName);
                //传入参数执行set方法
                method.invoke(obj, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
