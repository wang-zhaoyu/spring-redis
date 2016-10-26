import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:wanghzoayu
 * @Description:
 * @Date:Create in 2016/10/18 15:55
 * @Modified By:
 */
public class RedisJava {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("classpath*:conf/spring-context.xml");
            System.out.printf("qbm-redis-server: 加载信息");
            context.start();
            while (true) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("qbm-redis-server: 启动失败");
        } finally {
            if(null!=context){
                context.destroy();
            }
        }
    }
}