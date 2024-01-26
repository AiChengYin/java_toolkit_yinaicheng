package top.yinaicheng.utils.network;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * 网络检测工具类
 */
@Slf4j
public class NetworkCheckerUtil {

    /**
     * 尝试与百度服务器建立连接来判断电脑是否联网。如果能够成功连接，则说明电脑联网；如果连接超时或出现其他异常，则说明电脑未联网。
     */
    public static boolean isOffline() {
        try (Socket socket = new Socket()) {
            /*设置连接超时时间为3秒*/
            socket.connect(new InetSocketAddress("www.baidu.com", 80), 3000);
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    public static void main(String[] args) {
        boolean isOffline = isOffline();
        log.info("是否离线: {}", isOffline ? "是" : "否");
    }

}