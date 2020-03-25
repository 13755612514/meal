package com.xyc.mealoperation.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.xyc.mealoperation.entity.meal.User;
import io.netty.handler.ssl.OpenSslClientContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class OSSUtil {
    //LTAI4FcdBhPciYERZg3nTMwz
    //Zk3Be9X8pcXLsLqkSeJLMHn9BEbiD0
    /**
     * OSS节点
     */
    //@Value("${oss.endpoint}")
    private static String endPoint = "oss-cn-chengdu.aliyuncs.com";

    //@Value("${oss.accessKeyId}")
    private static String accessKeyId = "LTAI4FcdBhPciYERZg3nTMwz";

    //@Value("${oss.accessKeySecret}")
    private static String accessKeySecret = "Zk3Be9X8pcXLsLqkSeJLMHn9BEbiD0";

    /**
     * 视频域名
     */
    //@Value("${oss.image.domain}")
    private static String videoDomain = "http://mealxyc.oss-cn-chengdu.aliyuncs.com/";


    private static String bucketName = "mealxyc";
    private static OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
//    [ private static OSSClient ossClient = new OSSClientB OSSClient(endPoint, accessKeyId, accessKeySecret);


    /**
     * 上传文件,返回url
     * @param userId
     * @param stream
     * @return
     */
    public static String saveVideo(Long userId, MultipartFile stream) throws IOException {
        return save(userId,stream,".mp4");
    }
    private static String save(Long userId, MultipartFile file, String suffix) throws IOException {
        Reader in = new InputStreamReader(file.getInputStream());
        BufferedReader a = new BufferedReader(in);
        String str = a.lines().collect(Collectors.joining());
        String md5 = EncryptUtil.getInstance().MD5(str).replaceAll("\\+", "_").replaceAll("/","_").replaceAll("\\\\", "-");
        String key = "video/user" + userId + "/" + md5 + suffix;
        boolean exist = ossClient.doesObjectExist(bucketName, key);
        if (!exist) {
            // 上传文件
            ossClient.putObject(bucketName, key, file.getInputStream());
        }
        in.close();
        a.close();
        // 返回的url
        String url = videoDomain + key;
        return url;
    }

//    /**
//     *
//     * @param userId
//     * @param stream
//     * @return
//     */
//    public static String saveMovie(Long userId, InputStream stream){
//        return save(userId,stream,".mp4");
//    }
//    public static String save(Long userId, InputStream stream, String suffix) {
//        Reader in = new InputStreamReader(stream);
//        BufferedReader a = new BufferedReader(in);
//        InputStream input = stream;
//        String str = a.lines().collect(Collectors.joining());
//        EncryptUtil instance = EncryptUtil.getInstance();
//        String md5 = instance.MD5(str).replaceAll("\\+", "_").replaceAll("/","_").replaceAll("\\\\", "-");
//        Date d = new Date();
//        DateFormat format = new SimpleDateFormat("yyyyMMddhh");
//        String timeStr = format.format(d);
//        String key = "movie/user" + userId + "/" + md5 + timeStr + suffix;
//        boolean exist = ossClient.doesObjectExist(bucketName, key);
//        if (!exist) {
//            // 上传文件
//            ossClient.putObject(bucketName, key, input);
//            try {
//                in.close();
//                a.close();
//                input.close();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        // 返回的url
//        String url = videoDomain + key;
//        return url;
//    }
}
