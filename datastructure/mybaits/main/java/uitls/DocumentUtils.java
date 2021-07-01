package uitls;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @ClassName DocumentUtils
 * @Description
 * @Author za-yaowei
 * @Date 2021/6/21 11:31
 * @Version 1.0
 */
public class DocumentUtils {

    /**
     * 根据字节流读取xml文件，活的document对象。
     * @param inputStream
     * @return
     */
    public static Document readDocument(InputStream inputStream){

        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
