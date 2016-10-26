import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:wangzhaoyu
 * @Description:
 * @Date:Create in 2016/10/20 17:29
 * @Modified By:
 */
public class RedisStart {
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