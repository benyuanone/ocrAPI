package com.ourway.base.zk.component;

import org.zkoss.zul.Button;
import org.zkoss.zul.Paging;
import org.zkoss.zul.ext.Paginal;

/**
 * <p>
 * 分页栏目
 * </p>
 *
 * @author Jack Zhou
 * @version $Id: Paginal.java,v 0.1 Oct 31, 2010 5:11:33 PM Jack Exp $
 */
public class PaginalCus extends Paging implements Paginal {
    /**    */
    private static final long serialVersionUID = 9204620132740656785L;

//	public void onCreate(CreateEvent event) {
//		System.out.println(this.getUuid());
//		Component comp = this.getFellowIfAny(this.getUuid()+"-psize");
////		Select sel = (Select)comp;
//		Listbox ls = new Listbox();
////		ls.setParent(this);
//		
//	}

    // 是否允许子组件
    @Override
    public boolean isChildable() {
        return true;
    }


    @SuppressWarnings("unused")
    private Button autoFirstButton() {
        Button b = (Button) getFirstChild();
        if (b == null) {
            b = new Button();
            b.applyProperties();
            b.setParent(this);
        }
        return b;
    }
}
