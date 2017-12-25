package com.ourway.erpbasedata.erpguestvoucher;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import net.sf.json.JSONObject;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/*<p>方法 DownloadFileAction : <p>
*<p>说明:客户样张管理-下载文件</p>
*<pre>
*@author zhou_xtian
*@date 2017-11-09 15:28
</pre>
*/
public class DownloadFileAction implements ComponentListinerSer{

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Map<String,Object> params = JsonUtil.jsonToMap(((Image)window.getFellowIfAny("bigImage")).getContext());
        String filePath = params.get("filePath").toString();

        FileInputStream inputStream;
        try {
            File dosfile = new File(ZKConstants.SYSTEM_FILE_PATH + filePath);
            if (dosfile.exists()) {
                inputStream = new FileInputStream(dosfile);
                Filedownload.save(inputStream, filePath.split("/")[0], filePath.split("/")[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
