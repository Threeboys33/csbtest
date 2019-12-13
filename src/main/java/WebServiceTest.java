import com.alibaba.csb.ws.sdk.AxisCallWrapper;
import com.alibaba.csb.ws.sdk.WSParams;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import com.alibaba.csb.ws.sdk.WSClientSDK;
import javax.xml.namespace.QName;
import java.rmi.RemoteException;

/**
 * <p>
 *
 * @author 33
 * @version 1.0.0
 */
public class WebServiceTest {

    public static void soap() throws RemoteException {
        //设置服务调用的安全信息
        String apiName = "Third-api-getPiMasterInfo";  //要调用的服务名称
        String apiVersion = "1.0.0";  //要调用的服务版本
        String ak = "6a9e2ef7f05648b39157204da7b5a301"; //订购服务的accessKey
        String sk = "asi1HHSYTPfbVZK33qpM1fXLclw="; //订购服务的secrectKey
        WSParams params = WSParams.create().accessKey(ak).secretKey(sk).api(apiName).version(apiVersion).nonce(true);
        Service service = new Service();// 首先，构造封装Call对象
        Call call = AxisCallWrapper.createCallWrapper(service, params);
        //或者使用WSParams进行参数设置
        // 然后，使用封装Call对象进行方法调用
        call.setTargetEndpointAddress("http://123.138.75.146:9082/Third-api-getPiMasterInfo/1.0.0/ws2ws");
        call.setOperationName(new QName("http://cxf.webservice.nhis.zebone.com/", "getPiMasterInfo"));
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        call.addParameter("param", // 设置要传递的参数
                org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
        Object[] args = { "<req><codePi>190021785</codePi></req>" };
        // Object[] args = { "param" };
        Object ret = call.invoke(args);
        System.out.println("ret=" + ret);
    }

    public static void main(String[] args) throws RemoteException {
        soap();
    }

}
