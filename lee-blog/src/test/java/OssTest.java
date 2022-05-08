import com.google.gson.Gson;
import com.lee.LeeBlogApplication;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest(classes = LeeBlogApplication.class)
public class OssTest {
    private String accessKey = "rBSlSabudzpIizMzhrFJZ2kd_ba5e-XV_EV_sF9R";
    private String secretKey = "yqSjggCVACE7Uv_RYEzuNaSud13HWNAqt23JOG3d";
    private String bucket = "lys-space";

    @Test
    public void testOss() {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = "your access key";
//        String secretKey = "your secret key";
//        String bucket = "sg-blog";

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "C:\\Users\\Lee\\Desktop\\新建文件夹\\1.jpg";

        try {
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);


            InputStream inputStream = new FileInputStream("C:\\Users\\Lee\\Desktop\\新建文件夹\\1.png");
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }

    }
}
