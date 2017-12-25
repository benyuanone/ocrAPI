package com.ourway.base.zk.utils;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import org.zkoss.zul.Filedownload;

import java.io.*;
import java.sql.*;

public class DBUtil {
    public static void main(String[] args) {
        connect("2c9113ab5c57c914015c57f704480000");
    }


    public static void connect(String owid) {
        Connection Conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/owboss1?characterEncoding=utf8";
            String jdbcUsername = "root";
            String jdbcPassword = "123456";
            Conn = DriverManager.getConnection(jdbcUrl, jdbcUsername,
                    jdbcPassword);
            String timeStamp = DateUtil.getTimeStamp();//时间戳 用作生成文件名使用
            oneExp(Conn, owid, timeStamp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                Conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出单条数据
     *
     * @param conn
     * @param owid
     */
    public static void oneExp(Connection conn, String owid, String timeStamp) {
        String sql1 = "SELECT * FROM ourway_sys_page WHERE OWID= '" + owid + "'";
        String sql2 = "SELECT * FROM ourway_sys_layout WHERE PAGE_REF_OWID= '" + owid + "'";
        String sql3 = "SELECT * FROM ourway_sys_page_component WHERE PAGE_REF_OWID= '" + owid + "'";
        String fileName = timeStamp + ".txt";

        try {
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();
            Statement stmt4 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()) {
                String pageNo = judgeEmpty(rs.getString("PAGE_NO"));
                String pageName = judgeEmpty(rs.getString("PAGE_NAME"));
                String pageCa = judgeEmpty(rs.getString("PAGE_CA"));
                String pageMenu = rs.getString("PAGE_MENU");//int
                String pageTemplate = judgeEmpty(rs.getString("PAGE_TEMPLATE"));
                String pageStatus = rs.getString("PAGE_STATUS");//int
                String pageReport = rs.getString("PAGE_REPORT");//int
                String memo = judgeEmpty(rs.getString("MEMO"));
                String pageCustomer = rs.getString("PAGE_CUSTOMER");//int
                String pageInitType = rs.getString("PAGE_INIT_TYPE");//int
                String pageInit = judgeEmpty(rs.getString("PAGE_INIT"));
                String pageParams = judgeEmpty(rs.getString("PAGE_PARAMS"));
                String pageType = rs.getString("PAGE_TYPE");//int
                String pagePath = judgeEmpty(rs.getString("PAGE_PATH"));
                String pageTypeName = judgeEmpty(rs.getString("PAGE_TYPE_NAME"));

                writeContent("DELETE FROM ourway_sys_page WHERE OWID = '" + owid + "'; \r\n\r\n", fileName, true);//先删除
                writeContent("DELETE FROM ourway_sys_page_component WHERE PAGE_REF_OWID = '" + owid + "'; \r\n\r\n", fileName, true);//先删除
                writeContent("DELETE FROM ourway_sys_layout WHERE PAGE_REF_OWID = '" + owid + "'; \r\n\r\n", fileName, true);//先删除


                /**page**/
                writeContent("INSERT INTO ourway_sys_page (OWID,PAGE_NO,PAGE_NAME,PAGE_CA,PAGE_MENU,PAGE_TEMPLATE,PAGE_STATUS,PAGE_REPORT,MEMO,PAGE_CUSTOMER,PAGE_INIT_TYPE,PAGE_INIT,PAGE_PARAMS,PAGE_TYPE,PAGE_PATH,PAGE_TYPE_NAME)" +
                        " VALUE( '" + owid + "', " + pageNo + "," + pageName + "," + pageCa + "," + pageMenu + "," + pageTemplate + "," + pageStatus + "," + pageReport + "," + memo + "," + pageCustomer + "," + pageInitType + "," + pageInit + "," + pageParams + "," + pageType + "," + pagePath + "," + pageTypeName + ");", fileName, true);//后插入

                /**pageComponent**/
                ResultSet pageComponentRs = stmt3.executeQuery(sql3);
                while (pageComponentRs.next()) {
                    String componentId = judgeEmpty(pageComponentRs.getString("OWID"));
                    String pageRefOwid = judgeEmpty(pageComponentRs.getString("PAGE_REF_OWID"));
                    String kjOrder = pageComponentRs.getString("KJ_ORDER");//int
                    String kjName = judgeEmpty(pageComponentRs.getString("KJ_NAME"));
                    String kjLabelid = judgeEmpty(pageComponentRs.getString("KJ_LABELID"));
                    String kjAttribute = judgeEmpty(pageComponentRs.getString("KJ_ATTRIBUTE"));
                    String kjType = pageComponentRs.getString("KJ_TYPE");//int
                    String kjClass = judgeEmpty(pageComponentRs.getString("KJ_CLASS"));
                    String kjDataType = pageComponentRs.getString("KJ_DATA_TYPE");//int
                    String kjJavaType = judgeEmpty(pageComponentRs.getString("KJ_JAVA_TYPE"));
                    String kjFormat = pageComponentRs.getString("KJ_FORMAT");//int
                    String kjReportParam = pageComponentRs.getString("KJ_REPORT_PARAM");//int
                    String kjReportField = pageComponentRs.getString("KJ_REPORT_FIELD");//int
                    String kjDatasource = pageComponentRs.getString("KJ_DATASOURCE");//int
                    String kjInterface = judgeEmpty(pageComponentRs.getString("KJ_INTERFACE"));
                    String kjDefaultData = judgeEmpty(pageComponentRs.getString("KJ_DEFAULT_DATA"));
                    String kjTranslate = judgeEmpty(pageComponentRs.getString("KJ_TRANSLATE"));
                    String kjHidden = pageComponentRs.getString("KJ_HIDDEN");//int
                    String kjConstraint = judgeEmpty(pageComponentRs.getString("KJ_CONSTRAINT"));
                    String kjConstraintKey = judgeEmpty(pageComponentRs.getString("KJ_CONSTRAINT_KEY"));
                    String kjIndex = pageComponentRs.getString("KJ_INDEX");//int
                    String kjInitData = judgeEmpty(pageComponentRs.getString("KJ_INIT_DATA"));
                    String kjFilterType = pageComponentRs.getString("KJ_FILTER_TYPE");//int
                    String kjAttributeDisplay = judgeEmpty(pageComponentRs.getString("KJ_ATTRIBUTE_DISPLAY"));
                    String kjClassImpl = judgeEmpty(pageComponentRs.getString("KJ_CLASS_IMPL"));
                    String kjQueryAttribute = judgeEmpty(pageComponentRs.getString("KJ_QUERY_ATTRIBUTE"));
                    String kjKzsx = judgeEmpty(pageComponentRs.getString("KJ_KZSX"));
                    String kjPre = judgeEmpty(pageComponentRs.getString("KJ_PRE"));
                    String kjAfter = judgeEmpty(pageComponentRs.getString("KJ_AFTER"));
                    String kjDefaultcss = judgeEmpty(pageComponentRs.getString("KJ_DEFAULTCSS"));
                    writeContent("INSERT INTO ourway_sys_page_component (OWID,PAGE_REF_OWID,KJ_ORDER,KJ_NAME,KJ_LABELID,KJ_ATTRIBUTE,KJ_TYPE,KJ_CLASS,KJ_DATA_TYPE,KJ_JAVA_TYPE,KJ_FORMAT,KJ_REPORT_PARAM,KJ_REPORT_FIELD,KJ_DATASOURCE,KJ_INTERFACE,KJ_DEFAULT_DATA,KJ_TRANSLATE,KJ_HIDDEN,KJ_CONSTRAINT,KJ_CONSTRAINT_KEY,KJ_INDEX,KJ_INIT_DATA,KJ_FILTER_TYPE,KJ_ATTRIBUTE_DISPLAY,KJ_CLASS_IMPL,KJ_QUERY_ATTRIBUTE,KJ_KZSX,KJ_PRE,KJ_AFTER,KJ_DEFAULTCSS)" +
                            " VALUE( " + componentId + ", " + pageRefOwid + "," + kjOrder + "," + kjName + "," + kjLabelid + "," + kjAttribute + "," + kjType + "," + kjClass + "," + kjDataType + "," + kjJavaType + "," + kjFormat + "," + kjReportParam + "," + kjReportField + "," + kjDatasource + "," + kjInterface + "," + kjDefaultData + "," +
                            kjTranslate + "," + kjHidden + "," + kjConstraint + "," + kjConstraintKey + "," + kjIndex + "," + kjInitData + "," + kjFilterType + "," + kjAttributeDisplay + "," + kjClassImpl + "," + kjQueryAttribute + "," + kjKzsx + "," + kjPre + "," + kjAfter + "," +
                            kjDefaultcss + ");\r\n\r\n", fileName, true);//后插入

                }
                /**layout**/
                ResultSet layoutRs = stmt2.executeQuery(sql2);
                while (layoutRs.next()) {
                    String layoutId = judgeEmpty(layoutRs.getString("OWID"));
                    String pageRefOwid = judgeEmpty(layoutRs.getString("PAGE_REF_OWID"));
                    String orderNo = layoutRs.getString("ORDER_NO");//int
                    String controlId = judgeEmpty(layoutRs.getString("CONTROL_ID"));
                    String controlParentId = judgeEmpty(layoutRs.getString("CONTROL_PARENT_ID"));
                    String controlPath = judgeEmpty(layoutRs.getString("CONTROL_PATH"));
                    String controlWidth = layoutRs.getString("CONTROL_WIDTH");//int
                    String controlHeight = layoutRs.getString("CONTROL_HEIGHT");//int
                    String controlCss = judgeEmpty(layoutRs.getString("CONTROL_CSS"));
                    String controlType = layoutRs.getString("CONTROL_TYPE");//int
                    String controlClass = judgeEmpty(layoutRs.getString("CONTROL_CLASS"));
                    String controlSizebycontent = layoutRs.getString("CONTROL_SIZEBYCONTENT");//int
                    String controlCheck = judgeEmpty(layoutRs.getString("CONTROL_CHECK"));
                    String controlInt = judgeEmpty(layoutRs.getString("CONTROL_INT"));
                    String controlDetailInt = judgeEmpty(layoutRs.getString("CONTROL_DETAIL_INT"));
                    String controlDbInt = judgeEmpty(layoutRs.getString("CONTROL_DB_INT"));
                    String controlIsdb = layoutRs.getString("CONTROL_ISDB");//int
                    String controlIntGrid = judgeEmpty(layoutRs.getString("CONTROL_INT_GRID"));
                    String controlLoad = layoutRs.getString("CONTROL_LOAD");//int
                    String controlIsshow = layoutRs.getString("CONTROL_ISSHOW");//int
                    String controlLabel = judgeEmpty(layoutRs.getString("CONTROL_LABEL"));
                    String controlSclass = judgeEmpty(layoutRs.getString("CONTROL_SCLASS"));
                    String controlPagesize = layoutRs.getString("CONTROL_PAGESIZE");//int
                    String controlSplitpage = layoutRs.getString("CONTROL_SPLITPAGE");//int
                    String controlDbclickEvent = judgeEmpty(layoutRs.getString("CONTROL_DBCLICK_EVENT"));
                    String controlDbclickEventParam = judgeEmpty(layoutRs.getString("CONTROL_DBCLICK_EVENT_PARAM"));
                    String controlRowCss = judgeEmpty(layoutRs.getString("CONTROL_ROW_CSS"));

                    writeContent("INSERT INTO ourway_sys_layout (OWID,PAGE_REF_OWID,ORDER_NO,CONTROL_ID,CONTROL_PARENT_ID,CONTROL_PATH,CONTROL_WIDTH,CONTROL_HEIGHT,CONTROL_CSS,CONTROL_TYPE,CONTROL_CLASS,CONTROL_SIZEBYCONTENT,CONTROL_CHECK,CONTROL_INT,CONTROL_DETAIL_INT,CONTROL_DB_INT,CONTROL_ISDB,CONTROL_INT_GRID,CONTROL_LOAD,CONTROL_ISSHOW,CONTROL_LABEL,CONTROL_SCLASS,CONTROL_PAGESIZE,CONTROL_SPLITPAGE,CONTROL_DBCLICK_EVENT,CONTROL_DBCLICK_EVENT_PARAM,CONTROL_ROW_CSS)" +
                            " VALUE( " + layoutId + ", " + pageRefOwid + "," + orderNo + "," + controlId + "," + controlParentId + "," + controlPath + "," + controlWidth + "," + controlHeight + "," + controlCss + "," + controlType + "," + controlClass + "," + controlSizebycontent + "," + controlCheck + "," + controlInt + "," + controlDetailInt + "," + controlDbInt + "," + controlIsdb + "," + controlIntGrid + "," + controlLoad + ","
                            + controlIsshow + "," + controlLabel + "," + controlSclass + "," + controlPagesize + "," + controlSplitpage + "," + controlDbclickEvent + "," + controlDbclickEventParam + "," + controlRowCss + ");\r\n\r\n", fileName, true);//后插入


                    /**layoutComponent**/
                    String sql4 = "SELECT * FROM ourway_sys_layout_component WHERE LAYOUT_REF_OWID= " + layoutId + "";
                    ResultSet layoutComponentRs = stmt4.executeQuery(sql4);
                    while (layoutComponentRs.next()) {
                        String layoutComponentId = judgeEmpty(layoutComponentRs.getString("OWID"));
                        String layoutRefOwid = judgeEmpty(layoutComponentRs.getString("LAYOUT_REF_OWID"));
                        String pageRefOwid1 = judgeEmpty(layoutComponentRs.getString("PAGE_REF_OWID"));
                        String compId = judgeEmpty(layoutComponentRs.getString("COMP_ID"));
                        String compOrder = layoutComponentRs.getString("COMP_ORDER");//int
                        String compStartRow = layoutComponentRs.getString("COMP_START_ROW");//int
                        String compStartCol = layoutComponentRs.getString("COMP_START_COL");//int
                        String compCols = layoutComponentRs.getString("COMP_COLS");//int
                        String compRows = layoutComponentRs.getString("COMP_ROWS");//int
                        String compDataOrder = layoutComponentRs.getString("COMP_DATA_ORDER");//int
                        String compIsorder = layoutComponentRs.getString("COMP_ISORDER");//int
                        String compEditable = layoutComponentRs.getString("COMP_EDITABLE");//int
                        String compColor = judgeEmpty(layoutComponentRs.getString("COMP_COLOR"));
                        String compPosition = judgeEmpty(layoutComponentRs.getString("COMP_POSITION"));
                        String compHbox = layoutComponentRs.getString("COMP_HBOX");//int
                        String compVbox = layoutComponentRs.getString("COMP_VBOX");//int
                        String compLabel = layoutComponentRs.getString("COMP_LABEL");//int
                        String compCss = judgeEmpty(layoutComponentRs.getString("COMP_CSS"));
                        String compLength = layoutComponentRs.getString("COMP_LENGTH");//int
                        String compWidth = layoutComponentRs.getString("COMP_WIDTH");//int
                        String compInit = layoutComponentRs.getString("COMP_INIT");//int
                        String eventType = layoutComponentRs.getString("EVENT_TYPE");//int
                        String eventData = layoutComponentRs.getString("EVENT_DATA");//int
                        String eventForward = layoutComponentRs.getString("EVENT_FORWARD");//int
                        String eventFormula = judgeEmpty(layoutComponentRs.getString("EVENT_FORMULA"));
                        String eventColor = judgeEmpty(layoutComponentRs.getString("EVENT_COLOR"));
                        String eventInt = judgeEmpty(layoutComponentRs.getString("EVENT_INT"));
                        String eventBackType = layoutComponentRs.getString("EVENT_BACK_TYPE");//int
                        String eventBackContent = judgeEmpty(layoutComponentRs.getString("EVENT_BACK_CONTENT"));
                        String kjConstraint = judgeEmpty(layoutComponentRs.getString("KJ_CONSTRAINT"));
                        String kjConstraintKey = judgeEmpty(layoutComponentRs.getString("KJ_CONSTRAINT_KEY"));
                        String compGroup = layoutComponentRs.getString("COMP_GROUP");//int
                        String compFontCss = judgeEmpty(layoutComponentRs.getString("COMP_FONT_CSS"));
                        String eventDataContent = judgeEmpty(layoutComponentRs.getString("EVENT_DATA_CONTENT"));
                        String compRowsnum = layoutComponentRs.getString("COMP_ROWSNUM");//int
                        String compTjlx = layoutComponentRs.getString("COMP_TJLX");//int
                        String compTjwb = judgeEmpty(layoutComponentRs.getString("COMP_TJWB"));
                        String columCss = judgeEmpty(layoutComponentRs.getString("COLUM_CSS"));
                        String columType = layoutComponentRs.getString("COLUM_TYPE");//int
                        writeContent("DELETE FROM ourway_sys_layout_component WHERE OWID = " + layoutComponentId + "; \r\n\r\n", fileName, true);//先删除
                        writeContent("\r\nINSERT INTO ourway_sys_layout_component (OWID,LAYOUT_REF_OWID,PAGE_REF_OWID,COMP_ID,COMP_ORDER,COMP_START_ROW,COMP_START_COL,COMP_COLS,COMP_ROWS,COMP_DATA_ORDER,COMP_ISORDER,COMP_EDITABLE,COMP_COLOR,COMP_POSITION,COMP_HBOX,COMP_VBOX,COMP_LABEL,COMP_CSS,COMP_LENGTH,COMP_WIDTH,COMP_INIT,EVENT_TYPE,EVENT_DATA,EVENT_FORWARD,EVENT_FORMULA,EVENT_COLOR,EVENT_INT,EVENT_BACK_TYPE,EVENT_BACK_CONTENT,KJ_CONSTRAINT,KJ_CONSTRAINT_KEY,COMP_GROUP,COMP_FONT_CSS,EVENT_DATA_CONTENT,COMP_ROWSNUM,COMP_TJLX,COMP_TJWB,COLUM_CSS,COLUM_TYPE)" +
                                " VALUE( " + layoutComponentId + "," + layoutRefOwid + "," + pageRefOwid1 + "," + compId + "," + compOrder + "," + compStartRow + "," + compStartCol + "," + compCols + "," + compRows + "," + compDataOrder + "," + compIsorder + "," + compEditable + "," + compColor + "," + compPosition + "," + compHbox + "," + compVbox + "," + compLabel + "," + compCss + "," + compLength + "," + compWidth + "," + compInit + "," + eventType + "," + eventData + "," + eventForward + "," + eventFormula + "," + eventColor + "," + eventInt + "," + eventBackType + "," +
                                eventBackContent + "," + kjConstraint + "," + kjConstraintKey + "," + compGroup + "," + compFontCss + "," + eventDataContent + "," + compRowsnum + "," + compTjlx + "," + compTjwb + "," + columCss + "," + columType + ");\r\n\r\n", fileName, true);//后插入
                    }
                }
            }
            rs.close();
            stmt1.close();
            FileInputStream inputStream;
            File dosfile = new File(ZKConstants.SQL_PATH + fileName);
            if (dosfile.exists()) {
                inputStream = new FileInputStream(dosfile);
                Filedownload.save(inputStream, "text/plain", DateUtil.getTimeStamp() + ".txt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 写入内容到文件
     *
     * @param
     * @param filename
     * @return
     */
    public static boolean writeContent(String c,
                                       String filename, boolean isAppend) {
        File f = new File(ZKConstants.SQL_PATH);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(ZKConstants.SQL_PATH
                    + File.separator + filename, isAppend);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(c);
            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String judgeEmpty(String s) {
        if (TextUtils.isEmpty(s))
            return null;
        return "'" + s + "'";
    }

    /**
     * 从文件读取内容
     *
     * @param filename
     * @return
     */
    public static String readText(String filename) {
        String content = "";
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String str = "";
                String newline = "";
                while ((str = br.readLine()) != null) {
                    content += newline + str;
                    newline = "\n";
                }
                br.close();
                fr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
