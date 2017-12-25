package com.ourway.base.zk.component;

import com.ourway.base.zk.utils.BaseMessagebox;
import org.zkoss.mesg.Messages;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;


/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseMessageboxDlg extends Window {
    /**
     * What buttons are allowed.
     */
    private BaseMessagebox.Button[] _buttons;
    /**
     * Which button is pressed.
     */
    private BaseMessagebox.Button _result;
    /**
     * The event listener.
     */
    private EventListener<BaseMessagebox.ClickEvent> _listener;

    public void onOK() throws Exception {
        if (contains(BaseMessagebox.Button.OK))
            endModal(BaseMessagebox.Button.OK);
        else if (contains(BaseMessagebox.Button.YES))
            endModal(BaseMessagebox.Button.YES);
        else if (contains(BaseMessagebox.Button.RETRY))
            endModal(BaseMessagebox.Button.RETRY);
    }

    public void onCancel() throws Exception {
        if (_buttons.length == 1 && _buttons[0] == BaseMessagebox.Button.OK)
            endModal(BaseMessagebox.Button.OK);
        else if (contains(BaseMessagebox.Button.CANCEL))
            endModal(BaseMessagebox.Button.CANCEL);
        else if (contains(BaseMessagebox.Button.NO))
            endModal(BaseMessagebox.Button.NO);
        else if (contains(BaseMessagebox.Button.ABORT))
            endModal(BaseMessagebox.Button.ABORT);
    }

    private boolean contains(BaseMessagebox.Button button) {
        for (int j = 0; j < _buttons.length; ++j)
            if (_buttons[j] == button)
                return true;
        return false;
    }

    /**
     * Sets what buttons are allowed.
     */
    public void setButtons(BaseMessagebox.Button[] buttons, String[] btnLabels) {
        _buttons = buttons;

        final Component parent = getFellowIfAny("buttons");
        if (parent != null && parent.getFirstChild() == null) {
            //Backward compatible to ZK 5
            //We check if any child since user's old template might create them
            final String sclass = (String) parent.getAttribute("button.sclass");
            for (int j = 0; j < _buttons.length; ++j) {
                final BaseMessageboxDlg.Button mbtn = new BaseMessageboxDlg.Button();
                mbtn.setButton(_buttons[j], btnLabels != null && j < btnLabels.length ? btnLabels[j] : null);
                if(_buttons[j].name().equals("OK")||_buttons[j].name().equals("YES"))
                  mbtn.setSclass("z-ok-button z-ok");
                if(_buttons[j].name().equals("CANCEL")||_buttons[j].name().equals("NO"))
                    mbtn.setSclass("z-cancer-button z-cancerbutton");
                mbtn.setStyle("margin:5px;");
                mbtn.setAutodisable("self");
                parent.appendChild(mbtn);
            }
        }
    }

    /**
     * Sets the event listener.
     *
     * @param listener the event listener. If null, no invocation at all.
     * @since 3.0.4
     */
    public void setEventListener(EventListener<BaseMessagebox.ClickEvent> listener) {
        _listener = listener;
    }

    /**
     * Sets the focus.
     *
     * @param button the button to gain the focus. If 0, the default one
     *               (i.e., the first one) is assumed.
     * @since 3.0.0
     */
    public void setFocus(BaseMessagebox.Button button) {
        if (button != null) {
            final BaseMessageboxDlg.Button btn = (BaseMessageboxDlg.Button) getFellowIfAny("btn" + button.id);
            if (btn != null)
                btn.focus();
        }
    }

    /**
     * Called only internally.
     */
    public void endModal(BaseMessagebox.Button button) throws Exception {
        _result = button;
        if (_listener != null) {
            final BaseMessagebox.ClickEvent evt = new BaseMessagebox.ClickEvent(button.event, this, button);
            _listener.onEvent(evt);
            if (!evt.isPropagatable())
                return; //no more processing
        }
        detach();
    }

    /**
     * Returns the result which is the button being pressed.
     */
    public BaseMessagebox.Button getResult() {
        return _result;
    }

    //Override//
    public void onClose() {
        if (_listener != null) {
            final BaseMessagebox.ClickEvent evt = new BaseMessagebox.ClickEvent(Events.ON_CLOSE, this, null);
            try {
                _listener.onEvent(evt);
                if (!evt.isPropagatable())
                    return; //no more processing
            } catch (Exception ex) {
                throw UiException.Aide.wrap(ex);
            }
        }
        super.onClose();
    }

    /**
     * Represents a button on the message box.
     *
     * @since 3.0.4
     */
    public static class Button extends BaseButton {
        private BaseMessagebox.Button _button;

        /**
         * Sets the label's information and label.
         */
        public void setButton(BaseMessagebox.Button button, String label) {
            _button = button;
            setLabel(label != null ? label : Messages.get(_button.label));
            setId("btn" + _button.id);
            if (label != null && label.length() > 7) //dirty trick (since there is a default width)
                setWidth("auto");
        }

        /**
         * Sets the label's information with a default label.
         */
        public void setButton(BaseMessagebox.Button button) {
            setButton(button, null);
        }

        public void onClick() throws Exception {
            ((BaseMessageboxDlg) getSpaceOwner()).endModal(_button);
        }

        protected String getDefaultMold(Class klass) {
            return super.getDefaultMold(org.zkoss.zul.Button.class);
        }

        /**
         * @deprecated As of release 6.0.0, buttons are created in Java
         */
        public void setIdentity(int button) {
            switch (button) {
                case BaseMessagebox.YES:
                    setButton(BaseMessagebox.Button.YES);
                    break;
                case BaseMessagebox.NO:
                    setButton(BaseMessagebox.Button.NO);
                    break;
                case BaseMessagebox.RETRY:
                    setButton(BaseMessagebox.Button.RETRY);
                    break;
                case BaseMessagebox.ABORT:
                    setButton(BaseMessagebox.Button.ABORT);
                    break;
                case BaseMessagebox.IGNORE:
                    setButton(BaseMessagebox.Button.IGNORE);
                    break;
                case BaseMessagebox.CANCEL:
                    setButton(BaseMessagebox.Button.CANCEL);
                    break;
                default:
                    setButton(BaseMessagebox.Button.OK);
                    break;
            }
        }
    }
}
