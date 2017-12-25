package com.ourway.base.zk.service;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import org.zkoss.zul.Row;


/**
 * <p>接口 ComponentListinerSer.java : <p>
 * <p>说明：用户解决双击表格中的某一行数据</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/23 9:22
 * </pre>
 */
public interface GridRowDbclickListinerSer {

    /**
     * <p>方法:doAction TODO </p>
     * <ul>
     * <li> @param window 当前窗口</li>
     * <li> @param event 双击事件</li>
     * <li> @param grid 当前表格</li>
     * <li> @param rowData 当前行数据</li>
     * <li> @param row 选中的行</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/4 13:56  </li>
     * </ul>
     */
    void doAction(BaseWindow window, BaseGrid grid,Row row);

}
